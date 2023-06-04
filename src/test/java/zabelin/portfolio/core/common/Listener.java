//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saucelabs.saucerest.SauceREST;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.Utils;
import org.testng.reporters.XMLReporterConfig;
import org.testng.reporters.XMLStringBuffer;
import org.testng.xml.XmlSuite;
import zabelin.portfolio.core.annotations.API;
import zabelin.portfolio.core.annotations.DisableTestWhen;
import zabelin.portfolio.core.annotations.Team;
import zabelin.portfolio.core.annotations.UI;
import zabelin.portfolio.core.report.XMLSuiteResultWriter;
import zabelin.portfolio.core.ui.BasePageObject;
import zabelin.portfolio.core.ui.DriverFactory;
import zabelin.portfolio.core.util.CommonUtil;
import zabelin.portfolio.core.util.DateUtil;
import zabelin.portfolio.core.util.TextUtil;
import zabelin.portfolio.core.webdriverlogger.WebDriverLogger;
import zabelin.portfolio.core.webdriverlogger.data.CaseContext;
import zabelin.portfolio.core.webdriverlogger.data.XPathUsageData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener, IReporter, IAnnotationTransformer {
    public static final String TEST_CASE_ID_PATTERN = "C\\d+";
    public static final String RESULTS_FILE_NAME = "testng-results.xml";
    public static final String DEFAULT_REPORT_OUTPUT_DIR = "test-output";
    protected static ResultGenerationMode resultGenerationMode;
    protected static boolean processFail;
    protected static boolean disableScreenshots;
    protected static int threadsCount;
    protected static String browser;
    protected static Map<String, String> sauceLabsCredentials;
    protected static SauceREST sauceRestClient;
    protected static final XMLReporterConfig reportConfig = new XMLReporterConfig();
    protected static ObjectMapper mapper = new ObjectMapper();
    protected ArrayList<ISuite> suites = new ArrayList();
    protected XMLStringBuffer rootBuffer;
    protected int testPassed = 0;
    protected int testFailed = 0;
    protected int testSkipped = 0;
    public static final ThreadLocal<CaseContext> context = new ThreadLocal();
    public static final Map<String, CaseContext> allSessions = new ConcurrentHashMap();
    static final String SHARED_MESSAGES_DEFAULT_DIR = "true";
    static final String CLIENT_WAITING = "client_waiting";
    static final String MESSAGES_FILE = "messages";
    static final String EXECUTION_FINISHED = "execf";
    static String sharedMessagesPath = "";
    protected ArrayList<TestInfo> testInfos = new ArrayList();

    public Listener() {
    }

    public void onStart(ISuite arg0) {
        Log.info("About to begin executing Suite " + arg0.getName());
        System.setProperty("suitename", arg0.getName());
        arg0.getXmlSuite().setName(arg0.getName());
        if (threadsCount > 1) {
            arg0.getXmlSuite().setParallel("tests");
            arg0.getXmlSuite().setThreadCount(threadsCount);
            arg0.getXmlSuite().setPreserveOrder("true");
        }

        this.suites.add(arg0);
    }

    public void onFinish(ISuite arg0) {
        if (resultGenerationMode == Listener.ResultGenerationMode.AFTER_SUITE) {
            this.generateReport();
        }

        this.generateWebDriverLogReport();
    }

    public void onStart(ITestContext arg0) {
        CaseContext caseContext = new CaseContext();
        context.set(caseContext);
        caseContext.setCaseDescription(arg0.getName());
        Log.startTestCase(arg0.getName());
        Log.info("About to begin executing Test " + arg0.getName());
        Pattern testIDpattern = Pattern.compile("C\\d+");
        Matcher matcher = testIDpattern.matcher(arg0.getName());
        if (matcher.find()) {
            Log.info("@@@Test Case ID: " + matcher.group(0));
            caseContext.setCaseId(matcher.group(0));
        }

    }

    public void onFinish(ITestContext arg0) {
        Log.info("Completed executing test " + arg0.getName());
        Log.endTestCase(arg0.getName());
    }

    public void onTestStart(ITestResult arg0) {
        Object currentClass = arg0.getInstance();

        try {
            RemoteWebDriver driver = (RemoteWebDriver) ((EnvironmentConfigurator) currentClass).getWebDriver();
            if (driver != null) {
                Log.info("Test started on the following configuration " + driver.getCapabilities().toString());
                arg0.setAttribute("configuration::Browser", System.getProperty("mbrowserfullname"));
                arg0.setAttribute("configuration::Platform", System.getProperty("mplatformfullname"));
                arg0.setAttribute("configuration::Environment", System.getProperty("menv"));
                this.setGenerateTestResultAttributes(true);
            }
        } catch (Exception var4) {
        }

    }

    public void onTestSuccess(ITestResult arg0) {
        this.updateSauceLabsResult(arg0, "pass");
        this.printTestResults(arg0);
    }

    public void onTestFailure(ITestResult arg0) {
        if (processFail) {
            this.updateSauceLabsResult(arg0, "fail");
            this.updateResultWithScreenshot(arg0);
        }

        this.printTestResults(arg0);
    }

    public void onTestSkipped(ITestResult arg0) {
        if (processFail) {
            this.updateSauceLabsResult(arg0, "skip");
            this.updateResultWithScreenshot(arg0);
        }

        this.printTestResults(arg0);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }

    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        Log.info("About to begin executing following method : " + this.returnMethodName(arg0.getTestMethod()));
    }

    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        try {
            String teamName = null;
            Class<?> testClass = arg1.getTestClass().getRealClass();
            if (testClass.isAnnotationPresent(Team.class)) {
                teamName = ((Team) testClass.getAnnotation(Team.class)).name();
            }

            Method mthd = arg0.getTestMethod().getConstructorOrMethod().getMethod();
            Log.info("@@@MetadataBegin");
            Log.info("@@@Package: " + testClass.getPackage().getName());
            if (mthd.isAnnotationPresent(API.class)) {
                Log.info("@@@API-ID: " + ((API) mthd.getAnnotation(API.class)).id());
                Log.info("@@@Tester: " + ((API) mthd.getAnnotation(API.class)).tester());
            }

            if (mthd.isAnnotationPresent(UI.class)) {
                Log.info("@@@UI-Tester: " + ((UI) mthd.getAnnotation(UI.class)).tester());
            }

            if (mthd.isAnnotationPresent(Team.class)) {
                teamName = ((Team) mthd.getAnnotation(Team.class)).name();
            }

            if (TextUtil.hasValue(System.getProperty("build"))) {
                Log.info("@@@Build: " + System.getProperty("build"));
            }

            if (TextUtil.hasValue(teamName)) {
                Log.info("@@@Team: " + teamName);
            }

            Log.info("@@@MetadataEnd");
            Log.info("Completed executing following method : " + this.returnMethodName(arg0.getTestMethod()));
            ITestNGMethod testMethod = arg0.getTestMethod();
            if (resultGenerationMode == Listener.ResultGenerationMode.AFTER_CLASS && testMethod.isAfterClassConfiguration() && !testMethod.hasMoreInvocation()) {
                this.generateReport();
            } else if (resultGenerationMode == Listener.ResultGenerationMode.AFTER_TEST && testMethod.isTest()) {
                this.generateReport();
            }

            if (!arg1.getMethod().isTest()) {
                this.printTestResults(arg1);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
        if (testMethod.isAnnotationPresent(DisableTestWhen.class) && Arrays.asList(((DisableTestWhen) testMethod.getAnnotation(DisableTestWhen.class)).browsers()).contains(browser)) {
            annotation.setEnabled(false);
        }

    }

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        int passed = 0;
        int failed = 0;
        int skipped = 0;
        Iterator var7 = suites.iterator();

        while (var7.hasNext()) {
            ISuite s = (ISuite) var7.next();
            Map<String, ISuiteResult> suiteResults = s.getResults();
            ITestContext testContext;
            synchronized (suiteResults) {
                for (Iterator var11 = suiteResults.values().iterator(); var11.hasNext(); skipped += testContext.getSkippedTests().size()) {
                    ISuiteResult sr = (ISuiteResult) var11.next();
                    testContext = sr.getTestContext();
                    passed += testContext.getPassedTests().size();
                    failed += testContext.getFailedTests().size();
                }
            }
        }

        this.rootBuffer = new XMLStringBuffer();
        Properties p = new Properties();
        p.put("passed", passed);
        p.put("failed", failed);
        p.put("skipped", skipped);
        p.put("total", passed + failed + skipped);
        this.rootBuffer.push("testng-results", p);
        Iterator var17 = suites.iterator();

        while (var17.hasNext()) {
            ISuite suite = (ISuite) var17.next();
            this.writeSuite(suite.getXmlSuite(), suite);
        }

        this.rootBuffer.pop();
        Utils.writeUtf8File(outputDirectory, "testng-results.xml", this.rootBuffer, (String) null);
    }

    protected void generateReport() {
        this.generateReport((List) null, this.suites, reportConfig.getOutputDirectory());
    }

    protected synchronized void generateWebDriverLogReport() {
        Map<String, XPathUsageData> webDriverLog = WebDriverLogger.generateWebDriverLog();

        try {
            FileWriter fileWriter = new FileWriter(reportConfig.getOutputDirectory() + "/locators.json");
            fileWriter.write(webDriverLog.values().toString());
            fileWriter.close();
        } catch (IOException var3) {
            Log.warn("WebDriver log report wasn't properly saved to the file " + reportConfig.getOutputDirectory() + "/locators.json");
            var3.printStackTrace();
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    protected void writeReporterOutput(XMLStringBuffer xmlBuffer) {
        xmlBuffer.push("reporter-output");
        List<String> output = Reporter.getOutput();
        Iterator var3 = output.iterator();

        while (var3.hasNext()) {
            String line = (String) var3.next();
            if (line != null) {
                xmlBuffer.push("line");
                xmlBuffer.addCDATA(CommonUtil.filterInvalidChars(line));
                xmlBuffer.pop();
            }
        }

        xmlBuffer.pop();
    }

    protected void writeSuite(XmlSuite xmlSuite, ISuite suite) {
        switch (reportConfig.getFileFragmentationLevel()) {
            case 1:
                this.writeSuiteToBuffer(this.rootBuffer, suite);
                break;
            case 2:
            case 3:
                File suiteFile = this.referenceSuite(this.rootBuffer, suite);
                this.writeSuiteToFile(suiteFile, suite);
                break;
            default:
                throw new AssertionError("Unexpected value: " + reportConfig.getFileFragmentationLevel());
        }

    }

    protected void writeSuiteToFile(File suiteFile, ISuite suite) {
        XMLStringBuffer xmlBuffer = new XMLStringBuffer();
        this.writeSuiteToBuffer(xmlBuffer, suite);
        File parentDir = suiteFile.getParentFile();
        if (parentDir.exists() || suiteFile.getParentFile().mkdirs()) {
            Utils.writeFile(parentDir.getAbsolutePath(), "testng-results.xml", xmlBuffer.toXML());
        }

    }

    protected File referenceSuite(XMLStringBuffer xmlBuffer, ISuite suite) {
        String relativePath = suite.getName() + File.separatorChar + "testng-results.xml";
        File suiteFile = new File(reportConfig.getOutputDirectory(), relativePath);
        Properties attrs = new Properties();
        attrs.setProperty("url", relativePath);
        xmlBuffer.addEmptyElement("suite", attrs);
        return suiteFile;
    }

    protected void writeSuiteToBuffer(XMLStringBuffer xmlBuffer, ISuite suite) {
        xmlBuffer.push("suite", this.getSuiteAttributes(suite));
        this.writeSuiteGroups(xmlBuffer, suite);
        Map<String, ISuiteResult> results = suite.getResults();
        XMLSuiteResultWriter suiteResultWriter = this.getSuiteResultWriter();
        Iterator var5 = results.entrySet().iterator();

        while (var5.hasNext()) {
            Map.Entry<String, ISuiteResult> result = (Map.Entry) var5.next();
            suiteResultWriter.writeSuiteResult(xmlBuffer, (ISuiteResult) result.getValue());
        }

        xmlBuffer.pop();
    }

    protected XMLSuiteResultWriter getSuiteResultWriter() {
        return new XMLSuiteResultWriter(reportConfig);
    }

    protected void writeSuiteGroups(XMLStringBuffer xmlBuffer, ISuite suite) {
        xmlBuffer.push("groups");
        Map<String, Collection<ITestNGMethod>> methodsByGroups = suite.getMethodsByGroups();
        Iterator var4 = methodsByGroups.entrySet().iterator();

        while (var4.hasNext()) {
            Map.Entry<String, Collection<ITestNGMethod>> entry = (Map.Entry) var4.next();
            Properties groupAttrs = new Properties();
            groupAttrs.setProperty("name", (String) entry.getKey());
            xmlBuffer.push("group", groupAttrs);
            Set<ITestNGMethod> groupMethods = this.getUniqueMethodSet((Collection) entry.getValue());
            Iterator var8 = groupMethods.iterator();

            while (var8.hasNext()) {
                ITestNGMethod groupMethod = (ITestNGMethod) var8.next();
                Properties methodAttrs = new Properties();
                methodAttrs.setProperty("name", groupMethod.getMethodName());
                methodAttrs.setProperty("signature", groupMethod.toString());
                methodAttrs.setProperty("class", groupMethod.getRealClass().getName());
                xmlBuffer.addEmptyElement("method", methodAttrs);
            }

            xmlBuffer.pop();
        }

        xmlBuffer.pop();
    }

    protected Properties getSuiteAttributes(ISuite suite) {
        Properties props = new Properties();
        props.setProperty("name", suite.getName());
        Map<String, ISuiteResult> results = suite.getResults();
        Date minStartDate = new Date();
        Date maxEndDate = null;
        synchronized (results) {
            Iterator var7 = results.entrySet().iterator();

            label40:
            while (true) {
                Date startDate;
                Date endDate;
                do {
                    if (!var7.hasNext()) {
                        break label40;
                    }

                    Map.Entry<String, ISuiteResult> result = (Map.Entry) var7.next();
                    ITestContext testContext = ((ISuiteResult) result.getValue()).getTestContext();
                    startDate = testContext.getStartDate();
                    endDate = testContext.getEndDate();
                    if (minStartDate.after(startDate)) {
                        minStartDate = startDate;
                    }
                } while (maxEndDate != null && !maxEndDate.before(endDate));

                maxEndDate = endDate != null ? endDate : startDate;
            }
        }

        if (maxEndDate == null) {
            maxEndDate = minStartDate;
        }

        addDurationAttributes(reportConfig, props, minStartDate, maxEndDate);
        return props;
    }

    public static void addDurationAttributes(XMLReporterConfig config, Properties attributes, Date minStartDate, Date maxEndDate) {
        SimpleDateFormat format = new SimpleDateFormat(config.getTimestampFormat());
        TimeZone utc = TimeZone.getTimeZone("UTC");
        format.setTimeZone(utc);
        String startTime = format.format(minStartDate);
        String endTime = format.format(maxEndDate);
        long duration = maxEndDate.getTime() - minStartDate.getTime();
        attributes.setProperty("started-at", startTime);
        attributes.setProperty("finished-at", endTime);
        attributes.setProperty("duration-ms", Long.toString(duration));
    }

    protected Set<ITestNGMethod> getUniqueMethodSet(Collection<ITestNGMethod> methods) {
        Set<ITestNGMethod> result = new LinkedHashSet();
        Iterator var3 = methods.iterator();

        while (var3.hasNext()) {
            ITestNGMethod method = (ITestNGMethod) var3.next();
            result.add(method);
        }

        return result;
    }

    public void setGenerateTestResultAttributes(boolean generateTestResultAttributes) {
        reportConfig.setGenerateTestResultAttributes(generateTestResultAttributes);
    }

    protected String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }

    public static CaseContext getTestCaseContext() {
        return (CaseContext) context.get();
    }

    public static Map<String, String> getSauceLabsCredentials() throws Exception {
        Map<String, String> credentials = new HashMap();
        if (!DriverFactory.isLocalHost()) {
            String host = System.getProperty("mhost");
            if (!host.matches(".+:.+@.+:\\d+")) {
                Log.debug("Host property mhost'='" + host + "' doesn't match the following format - 'user:key@host:port'\nNo SauceLabs credentials was grabbed");
                return credentials;
            }

            String[] hostSplit = host.split("(:)");
            credentials.put("username", hostSplit[0].replace("%40", "@"));
            credentials.put("port", hostSplit[2]);
            hostSplit = hostSplit[1].split("(:)|(@)");
            credentials.put("password", hostSplit[0]);
            credentials.put("host", hostSplit[1]);
        }

        return credentials;
    }

    protected void updateSauceLabsResult(ITestResult arg0, String result) {
        if (DriverFactory.isSauceLabHost() && arg0.getInstance() instanceof EnvironmentConfigurator) {
            RemoteWebDriver driver = (RemoteWebDriver) ((EnvironmentConfigurator) arg0.getInstance()).getWebDriver();
            String jobID = driver.getSessionId().toString();
            if (result.equalsIgnoreCase("pass")) {
                sauceRestClient.jobPassed(jobID);
            } else {
                sauceRestClient.jobFailed(jobID);
            }
        }

    }

    protected String getTestCaseId(ITestResult arg0) {
        String result = "UNDEFINED_TEST_CASE_ID";
        Matcher matcher = Pattern.compile("C\\d+").matcher(arg0.getTestContext().getName());
        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    protected void updateResultWithScreenshot(ITestResult arg0) {
        if (arg0.getInstance() instanceof EnvironmentConfigurator && !disableScreenshots) {
            RemoteWebDriver driver = (RemoteWebDriver) ((EnvironmentConfigurator) arg0.getInstance()).getWebDriver();

            try {
                String screenShotName = this.getTestCaseId(arg0) + "_" + RandomStringUtils.randomNumeric(6) + ".png";
                String screenShotPath = BasePageObject.takeScreenShot(driver, screenShotName);
                arg0.setAttribute("screenshot", screenShotPath);
                Log.info("Screenshot path: " + screenShotPath);
            } catch (Exception var5) {
                Log.error("Result wasn't updated with a screenshot");
            }
        }

    }

    protected void printTestResults(ITestResult result) {
        Log.info("Test Method resides in " + result.getTestClass().getName());
        if (result.getParameters().length != 0) {
            StringBuilder params = new StringBuilder();
            Object[] var3 = result.getParameters();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Object parameter = var3[var5];
                params.append(parameter.toString()).append(",");
            }

            Log.info("Test Method had the following parameters : " + params);
        }

        String status = null;
        switch (result.getStatus()) {
            case 1:
                status = "Pass";
                ++this.testPassed;
                break;
            case 2:
                status = "Failed";
                ++this.testFailed;
                break;
            case 3:
                status = "Skipped";
                ++this.testSkipped;
        }

        try {
            long duration = result.getEndMillis() - result.getStartMillis();
            TestInfo info = new TestInfo(result.getTestClass().getName(), result.getMethod().getMethodName(), result.getMethod().getDescription(), status, !result.getMethod().isTest(), duration, System.currentTimeMillis());
            String json = mapper.writeValueAsString(info);
            Log.info("Test info: " + json);
            if (result.getMethod().isTest()) {
                this.addMessage(info);
            }
        } catch (JsonProcessingException var7) {
            var7.printStackTrace();
        }

        if (status != null && status.equals("Failed")) {
            Log.error(CommonUtil.filterInvalidChars(result.getThrowable().getMessage()));
        }

    }

    protected void addMessage(TestInfo msg) {
        if (isMessageShareMode()) {
            synchronized (this.testInfos) {
                this.testInfos.add(msg);
            }

            if (needToDumpMessages()) {
                this.dumpMessages();
                this.reloadClientWaiting();
            }
        }

    }

    static boolean isMessageShareMode() {
        return !sharedMessagesPath.isEmpty();
    }

    static boolean needToDumpMessages() {
        return isFileExists("client_waiting") || !isFileExists("messages");
    }

    static boolean isFileExists(String path) {
        return (new File(sharedMessagesPath + "/" + path)).exists();
    }

    static ArrayList<TestInfo> readMessages() {
        ArrayList<TestInfo> res = new ArrayList();

        try {
            String r = FileUtils.readFileToString(new File(sharedMessagesPath + "/" + "messages"), StandardCharsets.UTF_8);
            return ((TestInfoList) mapper.readValue(r, TestInfoList.class)).getInfo();
        } catch (IOException var2) {
            var2.printStackTrace();
            return res;
        }
    }

    static void deleteAllLogFiles() {
        deleteFile("messages");
        deleteFile("execf");
        deleteFile("client_waiting");
    }

    static void deleteFile(String path) {
        if (isFileExists(path)) {
            try {
                FileUtils.forceDelete(new File(sharedMessagesPath + "/" + path));
            } catch (IOException var2) {
                var2.printStackTrace();
            }
        }

    }

    protected void dumpMessages() {
        File out = new File(sharedMessagesPath + "/" + "messages");
        synchronized (this.testInfos) {
            try {
                TestInfoList list = new TestInfoList();
                list.setInfo(this.testInfos);
                String res = mapper.writeValueAsString(list);
                FileUtils.writeStringToFile(out, res, StandardCharsets.UTF_8, false);
            } catch (IOException var6) {
                var6.printStackTrace();
            }

        }
    }

    protected void reloadClientWaiting() {
        File out = new File("client_waiting/messages");
        if (out.exists()) {
            try {
                FileUtils.forceDelete(out);
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

    }

    static {
        try {
            switch (System.getProperty("resultGenerationMode", "all")) {
                case "test":
                    resultGenerationMode = Listener.ResultGenerationMode.AFTER_TEST;
                    break;
                case "class":
                    resultGenerationMode = Listener.ResultGenerationMode.AFTER_CLASS;
                    break;
                case "suite":
                    resultGenerationMode = Listener.ResultGenerationMode.AFTER_SUITE;
                    break;
                default:
                    resultGenerationMode = Listener.ResultGenerationMode.AFTER_ALL;
            }

            processFail = Boolean.parseBoolean(System.getProperty("processFail", "true"));
            disableScreenshots = Boolean.parseBoolean(System.getProperty("disableScreenshots", "false"));
            String threadCount = System.getProperty("threadCount");
            threadsCount = StringUtils.isNotEmpty(threadCount) && threadCount.matches("\\d+") ? Integer.parseInt(threadCount) : 1;
            String mbrowser = System.getProperty("mbrowser");
            browser = StringUtils.isNotEmpty(mbrowser) ? mbrowser.toLowerCase() : "chrome";
            sauceLabsCredentials = getSauceLabsCredentials();
            if (!sauceLabsCredentials.isEmpty()) {
                sauceRestClient = new SauceREST((String) sauceLabsCredentials.get("username"), (String) sauceLabsCredentials.get("password"));
            }

            reportConfig.setGenerateTestResultAttributes(!disableScreenshots);
            reportConfig.setOutputDirectory("test-output");
            File reportDir = new File("test-output");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            sharedMessagesPath = System.getProperty("sharedMessageDirPath", "true");
            System.setProperty("suite.name", "TestResults");
            System.setProperty("current.date", DateUtil.formatCurrectDayYYYYMMDDHHMMSSTimeZone());
            System.setProperty("output.path", String.format("data-output/__Run_%s_%s", System.getProperty("suite.name"), System.getProperty("current.date")));
            File testDataOutputDir = new File(System.getProperty("output.path"));
            if (!testDataOutputDir.exists()) {
                testDataOutputDir.mkdirs();
            }

            try {
                System.setProperty("file.encoding", "UTF-8");
                Field charset = Charset.class.getDeclaredField("defaultCharset");
                charset.setAccessible(true);
                charset.set((Object) null, (Object) null);
            } catch (IllegalAccessException | NoSuchFieldException var6) {
                var6.printStackTrace();
            }
        } catch (Exception var7) {
            Log.error("Unable to initialize Listener");
            var7.printStackTrace();
        }

    }

    static enum ResultGenerationMode {
        AFTER_TEST,
        AFTER_CLASS,
        AFTER_SUITE,
        AFTER_ALL;

        private ResultGenerationMode() {
        }
    }
}
