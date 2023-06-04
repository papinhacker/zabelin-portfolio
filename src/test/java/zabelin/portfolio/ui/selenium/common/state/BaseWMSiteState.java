package zabelin.portfolio.ui.selenium.common.state;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.core.ui.BrowserLocalStorage;
import zabelin.portfolio.ui.selenium.common.annotations.PreconditionAnnotationParser;
import zabelin.portfolio.ui.selenium.common.env.EnvConstants;
import zabelin.portfolio.ui.selenium.common.model.LoginPageData;
import zabelin.portfolio.ui.selenium.common.resourcespool.WMSiteUserPool;
import zabelin.portfolio.ui.selenium.common.utils.UITestsHelper;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;


public class BaseWMSiteState extends UITestsHelper {

    private volatile static WMSiteUserPool usersPool = new WMSiteUserPool();
    protected String testIdentity;
    protected LoginPageData userData;
    private int step = 0;

    @BeforeMethod
    protected void executePreconditions(Method method) throws Exception {
        testIdentity = PreconditionAnnotationParser.getTestIdentity(method);
//        PreconditionAnnotationParser.parseResourceAnnotation(method, testIdentity, businessPool);
        try {
//            userData = PreconditionAnnotationParser.parseUser(method);
            String url = PreconditionAnnotationParser.parseUrl(method);
//            Location location = PreconditionAnnotationParser.parseLocation(method);
            //String flag = PreconditionAnnotationParser.parseFeatureFlag(method);
//            String specialUser = "";
//            if (method.getDeclaringClass().isAnnotationPresent(BaseState.class)) {
//                specialUser = method.getDeclaringClass().getAnnotation(BaseState.class).user();
//            }
//            if (method.isAnnotationPresent(Precondition.class)) {
//                specialUser = method.getAnnotation(Precondition.class).user();
//            }
//            if (specialUser.equals(Preconditions.WMSite.User.NEW_USER))
//                createNewVerifiedUser(userData);
//            if (specialUser.equals(Preconditions.WMSite.User.ORDERING))
//                userData = getUser(Preconditions.WMSite.User.ORDERING).getLoginPageData();
//            if (flag != null) {
//                setFeatureFlag(flag, method.getAnnotation(Precondition.class).flagValue());
//            }
            driver.get(EnvConstants.BASE_PAGE);
//            CommonHelper.setLocationCookies(driver, location); //workaround to get right positioning at start
//            driver.manage().addCookie(new Cookie("wm.maxConfirmedAge.v2", "21")); //workaround to bypass global age gate
            BrowserLocalStorage bls = new BrowserLocalStorage(driver);
            if (isMobileBrowser())
                bls.setItemInLocalStorage( //workaround to branch banner closing for mobile view
                        "BRANCH_WEBSDK_KEYjourneyDismissals",
                        "{\"614a44e451e9d8000c548e30\":{\"view_id\":\"968964537547361282\",\"dismiss_time\":" + (System.currentTimeMillis() - 1000L) + "}, " +
                                "\"60ea4c1e5163be000fe0b4bc\":{\"view_id\":\"944287524089737217\",\"dismiss_time\":" + (System.currentTimeMillis() - 1000L) + "}}");
//            WebElementsHelper.closeCookiesMessage(driver);
            bls.setItemInLocalStorage("terms-2020-10-01-privacy-2020-10-01", "true"); // close cookie message
            bls.setItemInLocalStorage("wm.favoritesTooltip", "true"); // close favorite us message in listing details
            bls.setItemInLocalStorage("wm.multi_menu_tooltip", "3");
            bls.setItemInLocalStorage("_wm_hide_brands_nearby_tooltip", "true");
//            bls.setItemInLocalStorage("_wm_hide_listing_potency_sort_tooltip", "true"); // close new sort message
            //workaround for local isolated runs of admin part
            if (EnvConstants.BASE_PAGE != null && EnvConstants.BASE_PAGE.contains("host")) {
                driver.manage().addCookie(new Cookie("_wm_admin_new_listing_admin_opt-in", "true"));
            }
            if (userData != null) {
//                UI way
//                launchURL(driver, EnvConstants.LOGIN_PAGE);
//                new LogInSignUpPage(driver).logIn(userData);
//
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

    protected static void launchURL(WebDriver driver, String url) {
        Log.action("Opening url: " + url);
        driver.get(url);
    }

    public BaseWMSiteState resizeBrowserWindow(int width, int height) throws Exception {
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().window().setPosition(new Point(0, 0));
        return this;
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
