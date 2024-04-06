package zabelin.portfolio.ui.selenium.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.ui.selenium.common.model.LoginPageData;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

public class CommonHelper {

    // тут должны быть всякие общие методы(и апишные, и, возможно, юайные, и конечно же просто логические)

    public synchronized static List<String> doLogInSessionAPI(String login, String password) throws Exception {
        return new ArrayList<>();
    }

    /**
     * Provides log in process via API, sets web driver cookies
     * returns session & token cookies
     *
     * @param driver
     * @param login
     * @param password
     * @throws Exception
     */
    public static List<String> logInAPI(WebDriver driver, String login, String password) throws Exception {
        try {
            List<String> cookies = doLogInSessionAPI(login, password);
            for (String cookie : cookies) {
                List<HttpCookie> httpCookies = HttpCookie.parse(cookie);
                HttpCookie httpCookie = httpCookies.get(0);
                Cookie driverCookie = new Cookie(
                        httpCookie.getName(), httpCookie.getValue(),
                        httpCookie.getDomain(), httpCookie.getPath(),
                        null,
                        httpCookie.getSecure(), httpCookie.isHttpOnly());
                driver.manage().addCookie(driverCookie);
            }
            return cookies;
        } catch (Exception ex) {
            throw new RuntimeException("Unable to provide log in process via API or set web driver cookies. Reason is: " + ex.getMessage());
        }
    }

    public static List<String> logInAPI(WebDriver driver, LoginPageData loginData) throws Exception {
        return logInAPI(driver, loginData.getUserName(), loginData.getPassword());
    }

    /**
     * Gets JWT token from browser session
     *
     * @return
     */
    public static String getJWTFromCookies(WebDriver driver) {
        try {
//            String cookieName =
//                    EnvConstants.ENV.equalsIgnoreCase("production") ?
//                            "_uat" :
//                            "_uat_" + EnvConstants.ENV;
//            return new JSONObject(
//                    URLDecoder.decode(
//                            driver.manage().getCookieNamed(cookieName).getValue(),
//                            StandardCharsets.UTF_8.toString())
//            ).getString("access_token");
        } catch (Exception e) {
            return "";
        }
        return null;
    }

    /**
     * Generate random email value
     *
     * @return
     */
    public static String getRandomEmail() {
        return String.format("%s@%s.%s", RandomStringUtils.randomAlphabetic(5),
                        RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(3))
                .toLowerCase();
    }

    public static boolean checkElasticSearchByName(String searchTerm, List<String> results, int expectedMatchPercents) {
        if (results == null || results.isEmpty() || searchTerm == null || searchTerm.isEmpty()) return false;
        long matchedItems = results.stream().filter(r -> r.toLowerCase().contains(searchTerm.toLowerCase())).count();
        double currentMatchPercents = (double) matchedItems / results.size() * 100;
        if (currentMatchPercents < (double) expectedMatchPercents) {
            Log.warn(String.format("Results of search are match with search query only on %s%%", currentMatchPercents));
            return false;
        }
        return true;
    }
}
