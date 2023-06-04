package zabelin.portfolio.ui.selenium.common.utils;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import zabelin.portfolio.core.common.EnvironmentConfigurator;
import zabelin.portfolio.core.common.Log;

import static zabelin.portfolio.core.ui.DriverFactory.IMPLICIT_WAIT_TIME_OUT;

public abstract class UITestsHelper extends EnvironmentConfigurator {

    protected int long_timeout = 90;
    protected int timeout = 30;
    protected int short_timeout = 3;

    public static boolean isMobileBrowser() {
        String mbrowser = System.getProperty("mbrowser");
        if (mbrowser == null)
            return false;
        mbrowser = mbrowser.toLowerCase();
        return mbrowser.contains("mobile")
                || mbrowser.contains("android_browser")
                || mbrowser.contains("ios_browser");
    }

    @BeforeMethod(dependsOnMethods = {"configuration"})
    public void initUITestsConstants() {
        short_timeout = IMPLICIT_WAIT_TIME_OUT / 10;
        timeout = IMPLICIT_WAIT_TIME_OUT;
        long_timeout = IMPLICIT_WAIT_TIME_OUT * 3;
        BrowserTabsHelper.initMainTab(driver);
    }

    @AfterMethod(alwaysRun = true)
    @Override
    protected void environmentConfiguratorAfterMethod() {
        BrowserTabsHelper.WINDOW_HANDLES.remove(driver);
        if (this.evalAfterMethod && !isSingleMode()) {
            try {
                this.driver.quit();
            } catch (Exception e) {
                Log.error("Driver 'quit' method failed");
            } finally {
                this.driver = null;
                this.context = null;
                this.steps = null;
                this.softAssert = null;
            }
        }
    }

    public void duplicateBrowserTab() throws InterruptedException {
        BrowserTabsHelper.duplicateBrowserTab(driver, timeout);
    }

    public void duplicateBrowserTab(int timeout) throws InterruptedException {
        BrowserTabsHelper.duplicateBrowserTab(driver, timeout);
    }

    public void createNewBrowserTab(String url, int timeout) throws InterruptedException {
        BrowserTabsHelper.createNewBrowserTab(driver, url, timeout);
    }

    public void createNewBrowserTab(String url) throws InterruptedException {
        BrowserTabsHelper.createNewBrowserTab(driver, url, timeout);
    }

    public void createNewBrowserTab() throws InterruptedException {
        BrowserTabsHelper.createNewBrowserTab(driver, timeout);
    }

    public boolean switchToWindow(int windowIndex, int timeout) throws Exception {
        BrowserTabsHelper.switchToWindow(driver, windowIndex, timeout);
        return true;
    }

    public boolean switchToWindow(int windowIndex) throws Exception {
        BrowserTabsHelper.switchToWindow(driver, windowIndex, timeout);
        return true;
    }

    public void closeCurrentTab() {
        BrowserTabsHelper.closeCurrentTab(driver);
    }

    public void removeTabIndex(int windowIndex) {
        BrowserTabsHelper.removeTabIndex(driver, windowIndex);
    }

}
