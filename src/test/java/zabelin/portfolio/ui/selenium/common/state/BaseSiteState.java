package zabelin.portfolio.ui.selenium.common.state;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.ui.selenium.common.annotations.PreconditionAnnotationParser;
import zabelin.portfolio.ui.selenium.common.env.EnvConstants;
import zabelin.portfolio.ui.selenium.common.model.LoginPageData;
import zabelin.portfolio.ui.selenium.common.resourcespool.SiteUserPool;
import zabelin.portfolio.ui.selenium.common.utils.UITestsHelper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;


public class BaseSiteState extends UITestsHelper {

    private static final SiteUserPool usersPool = new SiteUserPool();
    protected String testIdentity;
    protected LoginPageData userData;
    private int step = 0;

    protected static void launchURL(WebDriver driver, String url) {
        Log.action("Opening url: " + url);
        driver.get(url);
    }

    public static byte[] getByteArrayFile(String inputURL) throws Exception {
        URL url = new URL(inputURL);
        BufferedInputStream bis = new BufferedInputStream(url.openConnection().getInputStream());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] chunk = new byte[1024];
        int read;
        do {
            read = bis.read(chunk);
            if (read >= 0) {
                bos.write(chunk, 0, read);
            }
        } while (read >= 0);

        byte[] output = bos.toByteArray();
        bos.close();
        bis.close();
        return output;
    }

    public static String md5Hash(byte[] message) throws Exception {
        String md5 = "";
        if (null == message)
            return null;

        MessageDigest digest = MessageDigest.getInstance("MD5"); //Create MessageDigest object for MD5
        digest.update(message); //Update input string in message digest
        md5 = new BigInteger(1, digest.digest()).toString(16); //Converts message digest value in base 16 (hex)

        return md5;
    }

    public static String getMD5FromLink(String inputURL) throws Exception {
        return md5Hash(getByteArrayFile(inputURL));
    }

    @BeforeMethod
    protected void executePreconditions(Method method) throws Exception {
        testIdentity = PreconditionAnnotationParser.getTestIdentity(method);
        try {
            String url = PreconditionAnnotationParser.parseUrl(method);
            driver.get(EnvConstants.BASE_PAGE);
            //workaround for something
            if (EnvConstants.BASE_PAGE != null && EnvConstants.BASE_PAGE.contains("host")) {
                driver.manage().addCookie(new Cookie("some_precondition_cookie", "true"));
            }
            if (userData != null) {

//                API way
//                CommonHelper.logInAPI(driver, userData);
            }
            if (url != null) {
                launchURL(driver, url);
            } else {
                driver.navigate().refresh();
            }
        } catch (Exception ex) {
            Log.error("Before method was failed: " + ex.getMessage());
            throw new SkipException(ex.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void releaseOccupiedResources() {
        try {
            Log.debug(usersPool.release(testIdentity)
                    ? String.format("All users have been released by \"%s\"", testIdentity)
                    : String.format("No users have been released by \"%s\"", testIdentity));
        } catch (Exception e) {
            Log.error("Unable to release blockable objects");
        }
    }

    public BaseSiteState resizeBrowserWindow(int width, int height) throws Exception {
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().window().setPosition(new Point(0, 0));
        return this;
    }

    protected String getTestId() {
        return testIdentity.split("\\s")[0];
    }

    protected void preconditionStep(String message) {
        Log.step("Precondition. " + message);
    }

    protected void nextStep(String message) {
        Log.step(++step + ". " + message);
    }
}
