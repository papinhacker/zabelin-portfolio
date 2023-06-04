//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.report;

import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.collections.Sets;
import org.testng.reporters.XMLReporterConfig;
import org.testng.reporters.XMLStringBuffer;

import java.util.*;

public class XMLSuiteResultMethodAsTestWriter extends XMLSuiteResultWriter {
    public XMLSuiteResultMethodAsTestWriter(XMLReporterConfig config) {
        super(config);
    }

    protected void writeAllToBuffer(XMLStringBuffer xmlBuffer, ISuiteResult suiteResult) {
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
    }

    private Properties getTestAttributes(ITestResult testResult) {
        Properties attributes = new Properties();
        ITestContext tc = testResult.getTestContext();
        attributes.setProperty("name", testResult.getMethod().getDescription());
        long duration = testResult.getEndMillis() - testResult.getStartMillis();
        String strDuration = Long.toString(duration);
        attributes.setProperty("duration-ms", strDuration);
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

            List<ITestResult> sortedResults = (List) result.getValue();
            Collections.sort(sortedResults);
            Iterator var9 = sortedResults.iterator();

            while (var9.hasNext()) {
                ITestResult testResult = (ITestResult) var9.next();
                this.addTestResult(xmlBuffer, testResult, attributes);
            }
        }

    }

    protected void addTestResult(XMLStringBuffer xmlBuffer, ITestResult testResult, Properties classAttr) {
        if (testResult.getMethod().isTest()) {
            xmlBuffer.push("test", this.getTestAttributes(testResult));
            xmlBuffer.push("class", classAttr);
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
            xmlBuffer.pop();
            xmlBuffer.pop();
        }
    }
}
