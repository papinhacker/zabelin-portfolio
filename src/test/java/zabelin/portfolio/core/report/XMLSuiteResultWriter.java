//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.report;

import org.testng.*;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.collections.Maps;
import org.testng.collections.Sets;
import org.testng.internal.ConstructorOrMethod;
import org.testng.internal.Utils;
import org.testng.reporters.XMLReporter;
import org.testng.reporters.XMLReporterConfig;
import org.testng.reporters.XMLStringBuffer;
import org.testng.util.Strings;
import zabelin.portfolio.core.util.CommonUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class XMLSuiteResultWriter {
    protected XMLReporterConfig config;
    protected final int STACKTRACE_SHORT = 1;
    protected final int STACKTRACE_FULL = 2;

    public XMLSuiteResultWriter(XMLReporterConfig config) {
        this.config = config;
    }

    public void writeSuiteResult(XMLStringBuffer xmlBuffer, ISuiteResult suiteResult) {
        if (3 != this.config.getFileFragmentationLevel()) {
            this.writeAllToBuffer(xmlBuffer, suiteResult);
        } else {
            String parentDir = this.config.getOutputDirectory() + File.separatorChar + suiteResult.getTestContext().getSuite().getName();
            File file = this.referenceSuiteResult(xmlBuffer, parentDir, suiteResult);
            XMLStringBuffer suiteXmlBuffer = new XMLStringBuffer();
            this.writeAllToBuffer(suiteXmlBuffer, suiteResult);
            Utils.writeUtf8File(file.getAbsoluteFile().getParent(), file.getName(), suiteXmlBuffer.toXML());
        }

    }

    protected void writeAllToBuffer(XMLStringBuffer xmlBuffer, ISuiteResult suiteResult) {
        xmlBuffer.push("test", this.getSuiteResultAttributes(suiteResult));
        Set<ITestResult> testResults = Sets.newHashSet();
        ITestContext testContext = suiteResult.getTestContext();
        this.addAllTestResults(testResults, testContext.getPassedTests());
        this.addAllTestResults(testResults, testContext.getFailedTests());
        this.addAllTestResults(testResults, testContext.getSkippedTests());
        this.addAllTestResults(testResults, testContext.getPassedConfigurations());
        this.addAllTestResults(testResults, testContext.getSkippedConfigurations());
        this.addAllTestResults(testResults, testContext.getFailedConfigurations());
        this.addAllTestResults(testResults, testContext.getFailedButWithinSuccessPercentageTests());
        this.addTestResults(xmlBuffer, testResults);
        xmlBuffer.pop();
    }

    protected void addAllTestResults(Set<ITestResult> testResults, IResultMap resultMap) {
        if (resultMap != null) {
            List<ITestResult> allResults = new ArrayList();
            allResults.addAll(resultMap.getAllResults());
            Collections.sort(new ArrayList(allResults), new Comparator<ITestResult>() {
                public int compare(ITestResult o1, ITestResult o2) {
                    return (int) (o1.getStartMillis() - o2.getStartMillis());
                }
            });
            testResults.addAll(allResults);
        }

    }

    protected File referenceSuiteResult(XMLStringBuffer xmlBuffer, String parentDir, ISuiteResult suiteResult) {
        Properties attrs = new Properties();
        String suiteResultName = suiteResult.getTestContext().getName() + ".xml";
        attrs.setProperty("url", suiteResultName);
        xmlBuffer.addEmptyElement("test", attrs);
        return new File(parentDir + File.separatorChar + suiteResultName);
    }

    protected Properties getSuiteResultAttributes(ISuiteResult suiteResult) {
        Properties attributes = new Properties();
        ITestContext tc = suiteResult.getTestContext();
        attributes.setProperty("name", tc.getName());
        XMLReporter.addDurationAttributes(this.config, attributes, tc.getStartDate(), tc.getEndDate());
        return attributes;
    }

    protected void addTestResults(XMLStringBuffer xmlBuffer, Set<ITestResult> testResults) {
        Map<String, List<ITestResult>> testsGroupedByClass = this.buildTestClassGroups(testResults);
        Iterator var4 = testsGroupedByClass.entrySet().iterator();

        while (var4.hasNext()) {
            Map.Entry<String, List<ITestResult>> result = (Map.Entry) var4.next();
            Properties attributes = new Properties();
            String className = (String) result.getKey();
            if (this.config.isSplitClassAndPackageNames()) {
                int dot = className.lastIndexOf(46);
                attributes.setProperty("name", dot > -1 ? className.substring(dot + 1, className.length()) : className);
                attributes.setProperty("package", dot > -1 ? className.substring(0, dot) : "[default]");
            } else {
                attributes.setProperty("name", className);
            }

            xmlBuffer.push("class", attributes);
            List<ITestResult> sortedResults = (List) result.getValue();
            Collections.sort(sortedResults);
            Iterator var9 = sortedResults.iterator();

            while (var9.hasNext()) {
                ITestResult testResult = (ITestResult) var9.next();
                this.addTestResult(xmlBuffer, testResult);
            }

            xmlBuffer.pop();
        }

    }

    protected Map<String, List<ITestResult>> buildTestClassGroups(Set<ITestResult> testResults) {
        Map<String, List<ITestResult>> map = Maps.newHashMap();

        ITestResult result;
        List list;
        for (Iterator var3 = testResults.iterator(); var3.hasNext(); list.add(result)) {
            result = (ITestResult) var3.next();
            String className = result.getTestClass().getName();
            list = (List) map.get(className);
            if (list == null) {
                list = Lists.newArrayList();
                map.put(className, list);
            }
        }

        return map;
    }

    protected void addTestResult(XMLStringBuffer xmlBuffer, ITestResult testResult) {
        Properties attribs = this.getTestResultAttributes(testResult);
        String status = this.getStatusString(testResult);
        attribs.setProperty("status", status);
        xmlBuffer.push("test-method", attribs);
        this.addTestMethodParams(xmlBuffer, testResult);
        this.addTestResultException(xmlBuffer, testResult);
        this.addTestResultOutput(xmlBuffer, testResult);
        if (this.config.isGenerateTestResultAttributes()) {
            this.addTestResultAttributes(xmlBuffer, testResult);
        }

        xmlBuffer.pop();
    }

    protected String getStatusString(ITestResult testResult) {
        int status = testResult.getStatus();
        switch (status) {
            case 1:
                return "PASS";
            case 2:
                return this.isAssertionFailed(testResult) ? "ASSERT_FAIL" : "FAIL";
            case 3:
                return "SKIP";
            case 4:
                return "SUCCESS_PERCENTAGE_FAILURE";
            default:
                return null;
        }
    }

    protected boolean isAssertionFailed(ITestResult arg0) {
        return arg0.getThrowable().toString().contains("java.lang.AssertionError");
    }

    protected Properties getTestResultAttributes(ITestResult testResult) {
        Properties attributes = new Properties();
        if (!testResult.getMethod().isTest()) {
            attributes.setProperty("is-config", "true");
        }

        attributes.setProperty("name", testResult.getMethod().getMethodName());
        String testInstanceName = testResult.getTestName();
        if (null != testInstanceName) {
            attributes.setProperty("test-instance-name", testInstanceName);
        }

        String description = testResult.getMethod().getDescription();
        if (!Utils.isStringEmpty(description)) {
            attributes.setProperty("description", description);
        }

        attributes.setProperty("signature", this.removeClassName(testResult.getMethod().toString()));
        SimpleDateFormat format = new SimpleDateFormat(this.config.getTimestampFormat());
        String startTime = format.format(testResult.getStartMillis());
        String endTime = format.format(testResult.getEndMillis());
        attributes.setProperty("started-at", startTime);
        attributes.setProperty("finished-at", endTime);
        long duration = testResult.getEndMillis() - testResult.getStartMillis();
        String strDuration = Long.toString(duration);
        attributes.setProperty("duration-ms", strDuration);
        String dependsOnStr;
        if (this.config.isGenerateGroupsAttribute()) {
            dependsOnStr = Utils.arrayToString(testResult.getMethod().getGroups());
            if (!Utils.isStringEmpty(dependsOnStr)) {
                attributes.setProperty("groups", dependsOnStr);
            }
        }

        if (this.config.isGenerateDependsOnMethods()) {
            dependsOnStr = Utils.arrayToString(testResult.getMethod().getMethodsDependedUpon());
            if (!Utils.isStringEmpty(dependsOnStr)) {
                attributes.setProperty("depends-on-methods", dependsOnStr);
            }
        }

        if (this.config.isGenerateDependsOnGroups()) {
            dependsOnStr = Utils.arrayToString(testResult.getMethod().getGroupsDependedUpon());
            if (!Utils.isStringEmpty(dependsOnStr)) {
                attributes.setProperty("depends-on-groups", dependsOnStr);
            }
        }

        ConstructorOrMethod cm = testResult.getMethod().getConstructorOrMethod();
        if (cm.getMethod() != null) {
            Test testAnnotation = (Test) cm.getMethod().getAnnotation(Test.class);
            if (testAnnotation != null) {
                String dataProvider = testAnnotation.dataProvider();
                if (!Strings.isNullOrEmpty(dataProvider)) {
                    attributes.setProperty("data-provider", dataProvider);
                }
            }
        }

        return attributes;
    }

    protected String removeClassName(String methodSignature) {
        int firstParanthesisPos = methodSignature.indexOf("(");
        int dotAferClassPos = methodSignature.substring(0, firstParanthesisPos).lastIndexOf(".");
        return methodSignature.substring(dotAferClassPos + 1, methodSignature.length());
    }

    public void addTestMethodParams(XMLStringBuffer xmlBuffer, ITestResult testResult) {
        Object[] parameters = testResult.getParameters();
        if (parameters != null && parameters.length > 0) {
            xmlBuffer.push("params");

            for (int i = 0; i < parameters.length; ++i) {
                this.addParameter(xmlBuffer, parameters[i], i);
            }

            xmlBuffer.pop();
        }

    }

    protected void addParameter(XMLStringBuffer xmlBuffer, Object parameter, int i) {
        Properties attrs = new Properties();
        attrs.setProperty("index", String.valueOf(i));
        xmlBuffer.push("param", attrs);
        if (parameter == null) {
            Properties valueAttrs = new Properties();
            valueAttrs.setProperty("is-null", "true");
            xmlBuffer.addEmptyElement("value", valueAttrs);
        } else {
            xmlBuffer.push("value");
            xmlBuffer.addCDATA(parameter.toString());
            xmlBuffer.pop();
        }

        xmlBuffer.pop();
    }

    protected void addTestResultException(XMLStringBuffer xmlBuffer, ITestResult testResult) {
        Throwable exception = testResult.getThrowable();
        if (exception != null) {
            Properties exceptionAttrs = new Properties();
            exceptionAttrs.setProperty("class", exception.getClass().getName());
            xmlBuffer.push("exception", exceptionAttrs);
            if (!Utils.isStringEmpty(exception.getMessage())) {
                xmlBuffer.push("message");
                xmlBuffer.addCDATA(CommonUtil.filterInvalidChars(exception.getMessage()));
                xmlBuffer.pop();
            }

            if ((this.config.getStackTraceOutput().getLevel() & 1) == 1) {
                xmlBuffer.push("short-stacktrace");
                xmlBuffer.addCDATA(CommonUtil.filterInvalidChars(Utils.shortStackTrace(exception, false)));
                xmlBuffer.pop();
            }

            if ((this.config.getStackTraceOutput().getLevel() & 2) == 2) {
                xmlBuffer.push("full-stacktrace");
                xmlBuffer.addCDATA(CommonUtil.filterInvalidChars(Utils.longStackTrace(exception, false)));
                xmlBuffer.pop();
            }

            xmlBuffer.pop();
        }

    }

    protected void addTestResultOutput(XMLStringBuffer xmlBuffer, ITestResult testResult) {
        xmlBuffer.push("reporter-output");
        List<String> output = Reporter.getOutput(testResult);
        Iterator var4 = output.iterator();

        while (var4.hasNext()) {
            String line = (String) var4.next();
            if (line != null) {
                xmlBuffer.push("line");
                xmlBuffer.addCDATA(CommonUtil.filterInvalidChars(line));
                xmlBuffer.pop();
            }
        }

        xmlBuffer.pop();
    }

    protected void addTestResultAttributes(XMLStringBuffer xmlBuffer, ITestResult testResult) {
        if (testResult.getAttributeNames() != null && testResult.getAttributeNames().size() > 0) {
            xmlBuffer.push("attributes");
            Iterator var3 = testResult.getAttributeNames().iterator();

            while (var3.hasNext()) {
                String attrName = (String) var3.next();
                if (attrName != null) {
                    Object attrValue = testResult.getAttribute(attrName);
                    Properties attributeAttrs = new Properties();
                    attributeAttrs.setProperty("name", attrName);
                    if (attrValue == null) {
                        attributeAttrs.setProperty("is-null", "true");
                        xmlBuffer.addEmptyElement("attribute", attributeAttrs);
                    } else {
                        xmlBuffer.push("attribute", attributeAttrs);
                        xmlBuffer.addCDATA(attrValue.toString());
                        xmlBuffer.pop();
                    }
                }
            }

            xmlBuffer.pop();
        }

    }
}
