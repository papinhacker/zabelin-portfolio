//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.severotek.core.common;

import com.saucelabs.saucerest.SauceREST;
import com.severotek.core.ui.DriverFactory;
import com.severotek.core.ui.MobileAppInfoHolder;
import com.severotek.core.ui.TestSteps;
import com.severotek.core.webdriverlogger.WebDriverLogger;

import java.lang.reflect.Method;

import org.apache.commons.chain.impl.ContextBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EnvironmentConfigurator extends AssertVerification {
    protected ContextBase context;
    protected TestSteps steps;
    protected WebDriver webDriver = null;
    protected WebDriver driver = null;
    protected int driverPort = 0;
    protected boolean evalAfterClass = true;
    protected boolean evalAfterMethod = true;
    protected boolean evalBeforeMethod = true;
    protected boolean evalBeforeClass = true;
    protected static WebDriver staticWebDriver = null;
    protected static WebDriver staticDriver = null;
    protected boolean driverRecreatedBeforeMethod = false;

    public EnvironmentConfigurator() {
    }

    @BeforeMethod
    @Parameters({"browser", "platform", "host", "environment", "propsFile", "build", "timeout", "customCaps"})
    protected void configuration(@Optional("") String browser, @Optional("") String platform, @Optional("") String host, @Optional("") String environment, @Optional("") String propsFile, @Optional("") String build, @Optional("") String timeout, @Optional("") String customCaps) throws Exception {
        if (this.evalBeforeMethod) {
            if (isSingleMode()) {
                if (!this.isConnectionOpen()) {
                    this.createDriver(browser, platform, host, environment, propsFile, build, timeout, customCaps);
                    this.driverRecreatedBeforeMethod = true;
                } else {
                    this.driver = staticDriver;
                    this.webDriver = staticWebDriver;
                    this.driverRecreatedBeforeMethod = false;
                }
            } else if (this.webDriver == null || this.webDriver instanceof RemoteWebDriver && ((RemoteWebDriver) this.webDriver).getSessionId() == null) {
                this.createDriver(browser, platform, host, environment, propsFile, build, timeout, customCaps);
            }

        }
    }

    @BeforeClass
    @Parameters({"browser", "platform", "host", "environment", "propsFile", "build", "timeout", "customCaps", "recreate"})
    protected void configurationClass(@Optional("") String browser, @Optional("") String platform, @Optional("") String host, @Optional("") String environment, @Optional("") String propsFile, @Optional("") String build, @Optional("") String timeout, @Optional("") String customCaps, @Optional("") String recreate) throws Exception {
        if (this.evalBeforeClass) {
            if (recreate.equals("true") && this.isConnectionOpen()) {
                staticDriver.quit();
            }

            if (isSingleMode() && !this.isConnectionOpen()) {
                this.createDriver(browser, platform, host, environment, propsFile, build, timeout, customCaps);
                this.driverRecreatedBeforeMethod = true;
            }

        }
    }

    @BeforeMethod(
            dependsOnMethods = {"configuration"}
    )
    public void beforeMethodSauceLab(Method method) {
        if (this.evalBeforeMethod) {
            try {
                if (DriverFactory.isSauceLabHost() && !isSingleMode()) {
                    Test test = (Test) method.getAnnotation(Test.class);
                    if (test == null) {
                        return;
                    }

                    String testCaseNum = test.description().split(" ")[0];
                    SauceREST souceRest = MobileAppInfoHolder.getSouceRest();
                    String res = souceRest.getFullJobs(200);
                    JSONArray arr = new JSONArray(res);

                    for (int i = 0; i < arr.length(); ++i) {
                        JSONObject jsonObject = arr.getJSONObject(i);
                        if (jsonObject.getString("status").equals("in progress")) {
                            JSONArray tags = jsonObject.getJSONArray("tags");
                            if (tags.length() == 1 && tags.getString(0).equals(testCaseNum)) {
                                Log.always("SAUCE LAB TEST URL: https://saucelabs.com/jobs/" + jsonObject.getString("id"));
                                Log.always(" ");
                                return;
                            }
                        }
                    }
                }
            } catch (Exception var10) {
                Log.error("Exception in beforeMethodSauceLab was thrown: " + var10.getMessage());
            }

        }
    }

    protected void createDriver(String browser, String platform, String host, String environment, String propsFile, String build, String timeout, String customCaps) throws Exception {
        browser = getSystemProperty("mbrowser", browser);
        platform = getSystemProperty("mplatform", platform);
        host = getSystemProperty("mhost", host);
        environment = getSystemProperty("menv", environment);
        propsFile = getSystemProperty("mpropsFile", propsFile);
        customCaps = getSystemProperty("mcustomCaps", customCaps);
        build = getSystemProperty("mbuild", build);
        timeout = getSystemProperty("mtimeout", timeout);
        System.setProperty("menv", environment);
        if (propsFile.isEmpty()) {
            throw new Exception("Property file should be specified");
        } else {
            this.driverPort = PortProber.findFreePort();
            this.webDriver = DriverFactory.createDriver(propsFile, platform, browser, host, timeout, build, customCaps, this.driverPort);
            if (Listener.getTestCaseContext() != null) {
                EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(this.webDriver);
                eventFiringWebDriver.register(new WebDriverLogger());
                this.driver = eventFiringWebDriver;
            } else {
                this.driver = this.webDriver;
            }

            staticDriver = this.driver;
            staticWebDriver = this.webDriver;
            this.context = new ContextBase();
            this.context.put("driver", this.driver);
            this.steps = new TestSteps(this.context);
        }

    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    protected static String getSystemProperty(String propertyName, String defaultValue) {
        return System.getProperty(propertyName) != null && !System.getProperty(propertyName).isEmpty() ? System.getProperty(propertyName) : defaultValue;
    }

    @AfterMethod(
            alwaysRun = true
    )
    protected void environmentConfiguratorAfterMethod() {
        if (this.evalAfterMethod) {
            if (!isSingleMode()) {
                this.driver.quit();
                this.driver = null;
                this.webDriver = null;
                this.context = null;
                this.steps = null;
                this.softAssert = null;
            }

        }
    }

    public static boolean isSingleMode() {
        String useSingleMode = getSystemProperty("singleMode", "false");
        return useSingleMode.equals("true");
    }

    public boolean isConnectionOpen() {
        if (staticWebDriver != null && staticWebDriver instanceof RemoteWebDriver) {
            try {
                return ((RemoteWebDriver) staticWebDriver).getSessionId() != null;
            } catch (Exception var2) {
            }
        }

        return false;
    }

    protected boolean isEvalAfterClass() {
        return this.evalAfterClass;
    }

    protected void setEvalAfterClass(boolean evalAfterClass) {
        this.evalAfterClass = evalAfterClass;
    }

    protected boolean isEvalAfterMethod() {
        return this.evalAfterMethod;
    }

    protected void setEvalAfterMethod(boolean evalAfterMethod) {
        this.evalAfterMethod = evalAfterMethod;
    }

    protected boolean isEvalBeforeMethod() {
        return this.evalBeforeMethod;
    }

    protected void setEvalBeforeMethod(boolean evalBeforeMethod) {
        this.evalBeforeMethod = evalBeforeMethod;
    }

    protected boolean isEvalBeforeClass() {
        return this.evalBeforeClass;
    }

    protected void setEvalBeforeClass(boolean evalBeforeClass) {
        this.evalBeforeClass = evalBeforeClass;
    }

    protected void disableAllCheck() {
        this.evalAfterMethod = false;
        this.evalBeforeMethod = false;
        this.evalAfterClass = false;
        this.evalBeforeClass = false;
    }

    protected void enableAllCheck() {
        this.evalAfterMethod = true;
        this.evalBeforeMethod = true;
        this.evalAfterClass = true;
        this.evalBeforeClass = true;
    }
}
