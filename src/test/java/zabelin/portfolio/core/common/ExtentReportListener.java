package zabelin.portfolio.core.common;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import zabelin.portfolio.core.util.CommonUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ExtentReportListener implements ITestListener {

    private static ObjectMapper mapper = new ObjectMapper();

    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
        ExtentTestManager.startTest(result.getMethod().getDescription());

    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");


        RemoteWebDriver driver = (RemoteWebDriver) ((EnvironmentConfigurator) result.getInstance()).getWebDriver();
        //ExtentTestManager.getTest().log(Status.DEBUG, "Debug info: "+json);
        //ExtentTestManager.getTest().log(Status.INFO, "Driver capabilities: "+driver.getCapabilities().toString());

        // execution log
        List<String> output = Reporter.getOutput(Reporter.getCurrentTestResult());
        //shrink to last result
        output = output.subList(output.lastIndexOf("************************              Started                *************************")+1, output.size());

        String filteredOutput = output.stream()
                .filter(s -> !s.contains("[info]"))
                .collect(Collectors.joining("<br>"));

        ExtentTestManager.getTest().log(Status.DEBUG, filteredOutput);
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");

        RemoteWebDriver driver = (RemoteWebDriver) ((EnvironmentConfigurator) result.getInstance()).getWebDriver();

        //ExtentTestManager.getTest().log(Status.INFO, "Driver capabilities: "+driver.getCapabilities().toString());
        // execution log
        List<String> output = Reporter.getOutput();

        String filteredOutput = output.stream()
                .filter(s -> !s.contains("[info]"))
                .collect(Collectors.joining("<br>"));

        //ExtentTestManager.getTest().log(Status.DEBUG, filteredOutput);
        ExtentTestManager.getTest().log(Status.SKIP, CommonUtil.filterInvalidChars(result.getThrowable().getMessage()));
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }


    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        System.out.println(result.getMethod().getDescription() + " failed!");

        RemoteWebDriver driver = (RemoteWebDriver) ((EnvironmentConfigurator) result.getInstance()).getWebDriver();

        String status = null;
        switch (result.getStatus()) {
            case 1:
                status = "Pass";
                break;
            case 2:
                status = "Failed";
                break;
            case 3:
                status = "Skipped";
        }

        // debug info
        long duration = result.getEndMillis() - result.getStartMillis();
        TestInfo info = new TestInfo(result.getTestClass().getName(), result.getMethod().getMethodName(), result.getMethod().getDescription(), status, !result.getMethod().isTest(), duration, System.currentTimeMillis());
        String json = null;
        try {
            json = mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //ExtentTestManager.getTest().log(Status.DEBUG, "Debug info: "+json);
        //ExtentTestManager.getTest().log(Status.INFO, "Driver capabilities: "+driver.getCapabilities().toString());

        // execution log
        List<String> output = Reporter.getOutput(Reporter.getCurrentTestResult());

        output = output.subList(output.lastIndexOf("************************              Started                *************************")+1, output.size());

        String filteredOutput = output.stream()
                .filter(s -> !s.contains("[info]"))
                .collect(Collectors.joining("<br>"));

        ExtentTestManager.getTest().log(Status.DEBUG, filteredOutput);

        // Failing reason
        ExtentTestManager.getTest().log(Status.ERROR, CommonUtil.filterInvalidChars(result.getThrowable().getMessage()));

        // Screenshot
        String targetLocation = null;
        String testClassName = result.getInstanceName();
        String randomStr = RandomStringUtils.randomAlphabetic(5);
        String testMethodName = result.getName().toString().trim();
        String screenShotName = testMethodName + randomStr + ".png";
        String fileSeparator = System.getProperty("file.separator");
        String reportsPath = System.getProperty("user.dir") + fileSeparator + "TestReport" + fileSeparator
                + "screenshots";
        System.out.println("Screen shots reports path - " + reportsPath);
        try {
            File file = new File(reportsPath + fileSeparator + testClassName); // Set
            // screenshots
            // folder
            if (!file.exists()) {
                if (file.mkdirs()) {
                    System.out.println("Directory: " + file.getAbsolutePath() + " is created!");
                } else {
                    System.out.println("Failed to create directory: " + file.getAbsolutePath());
                }

            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            targetLocation = reportsPath + fileSeparator + testClassName + fileSeparator + screenShotName;// define
            // location
            File targetFile = new File(targetLocation);
            System.out.println("Screen shot file location - " + screenshotFile.getAbsolutePath());
            System.out.println("Target File location - " + targetFile.getAbsolutePath());
            FileHandler.copy(screenshotFile, targetFile);

        } catch (FileNotFoundException e) {
            System.out.println("File not found exception occurred while taking screenshot " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An exception occurred while taking screenshot " + e.getCause());
        }

        String relativeLocation = "screenshots" + fileSeparator + testClassName + fileSeparator + screenShotName;

        // attach screenshots to report
        try {

            ExtentTestManager.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(relativeLocation).build());
        } catch (IOException e) {
            System.out.println("An exception occured while taking screenshot " + e.getCause());
        }

        // Result message
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
    }
}
