//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.chain.Context;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.yaml.snakeyaml.Yaml;
import zabelin.portfolio.core.actionapi.util.HockeyAppApi;
import zabelin.portfolio.core.common.AssertVerification;
import zabelin.portfolio.core.common.Listener;
import zabelin.portfolio.core.common.Log;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DriverFactory extends AssertVerification {
    public static final String HOST_ENV_VAR = "mhost";
    public static final String WEB_DRIVER_KEY = "driver";
    public static final String DEFAULT_HOST_VALUE = "localhost";
    protected static final String ANDROID_BROWSER = "android";
    protected static final String IOS_BROWSER = "ios";
    protected static final String SAUCE_LABS_HOST = "saucelabs";
    protected static final String BROWSER_STACK_HOST = "browserstack";
    protected static final String APP_CAPABILITY_KEY = "app";
    protected static final String PLATFORM_VERSION_KEY = "platformVersion";
    protected static final String BROWSER_VERSION_KEY = "browserVersion";
    public static String LOCAL_DRIVER_VERSION = "latest";
    public static int IMPLICIT_WAIT_TIME_OUT = 30;
    protected static final Duration HTTP_CLIENT_CONNECTION_TIMEOUT = Duration.ofMinutes(2L);
    protected static final Duration HTTP_CLIENT_READ_TIMEOUT = Duration.ofMinutes(5L);
    public static String BUILD = "";
    public static String PLATFORM = "";
    public static String BROWSER = "";
    private static ThreadLocal<String> sessionId = new ThreadLocal();
    private static String prevPathToFile = null;
    private static String prevPlatform = null;
    private static String prevBrowser = null;
    private static String prevHost = null;
    private static String prevTimeout = null;
    private static String prevBuildVersion = null;
    private static String prevCustomCaps = null;
    private static boolean allowToRecreate = false;
    private static DesiredCapabilities capabilities = null;

    public DriverFactory() {
    }

    public static WebDriver getDriverFromContext(Context context) {
        return (WebDriver) context.get("driver");
    }

    public static WebDriver createDriver() throws Exception {
        return allowToRecreate ? createDriver(prevPathToFile, prevPlatform, prevBrowser, prevHost, prevTimeout, prevBuildVersion, prevCustomCaps, 0) : null;
    }

    public static void saveParams(String pathToFile, String platform, String browser, String host, String timeout, String buildVersion, String customCaps) {
        prevPathToFile = pathToFile;
        prevPlatform = platform;
        prevBrowser = browser;
        prevHost = host;
        prevTimeout = timeout;
        prevBuildVersion = buildVersion;
        prevCustomCaps = customCaps;
        allowToRecreate = true;
    }

    public static WebDriver createDriver(String pathToFile, String platform, String browser, String host, String timeout, String buildVersion, String customCaps, int port) throws Exception {
        prevPathToFile = pathToFile;
        prevPlatform = platform;
        prevBrowser = browser;
        prevHost = host;
        prevTimeout = timeout;
        prevBuildVersion = buildVersion;
        prevCustomCaps = customCaps;
        allowToRecreate = true;
        Map<String, Object> properties = loadPropertiesFromFile(pathToFile);
        Map<String, Object> settings = (Map) properties.get("settings");
        Map<String, Object> capas = (Map) properties.get("capabilities");
        Map<String, Object> hockeyApp = (Map) properties.get("hockeyapp");
        host = getPropertyValue(host, settings, "host", "localhost");
        if (!host.equals("localhost")) {
            platform = getPropertyValue(platform, settings, "platform", "");
            if (platform.isEmpty()) {
                throw new Exception("Platform name should be specified");
            }
        } else {
            platform = getPlatformName();
        }

        System.setProperty("mhost", host);
        browser = getPropertyValue(browser, settings, "browser", "chrome");
        buildVersion = getPropertyValue(buildVersion, settings, "build", "");
        timeout = getPropertyValue(timeout, settings, "timeout", "30");
        if (timeout != null && !timeout.isEmpty()) {
            IMPLICIT_WAIT_TIME_OUT = Integer.parseInt(timeout);
        } else {
            IMPLICIT_WAIT_TIME_OUT = 30;
        }

        BasePageObject.initConstants(IMPLICIT_WAIT_TIME_OUT);
        Log.info("Browser type requested: " + browser + "; Operating System: " + platform);
        BUILD = buildVersion;
        PLATFORM = platform;
        BROWSER = browser;
        Map<String, Object> capsFromFile = (Map) capas.get(platform);
        capsFromFile = (Map) capsFromFile.get(browser);
        DesiredCapabilities capabilities = getCapabilities(capsFromFile, platform, browser, host, customCaps, getPropVal(settings, "proxy"), buildVersion, Listener.getTestCaseContext() == null ? "-" : Listener.getTestCaseContext().getCaseId());
        DriverFactory.capabilities = capabilities;
        if (capabilities.getCapability("hockeyapp") != null) {
            hockeyApp = (Map) capabilities.getCapability("hockeyapp");
        }

        String platformVersion;
        String browserVersion;
        if (platform.contains("android") && hockeyApp != null && capabilities.getCapability("app") == null) {
            platformVersion = (String) hockeyApp.get("token");
            browserVersion = (String) hockeyApp.get("app");
            String path = (String) hockeyApp.get("path");
            String version = System.getProperty("-hkver", "");
            String id = System.getProperty("-hkid", "");
            if (!id.isEmpty()) {
                browserVersion = id;
            }

            String resPath = downloadFromHockeyApp(platformVersion, browserVersion, version, path);
            if (!resPath.isEmpty()) {
                setAppCapabilities(capabilities, (new File(resPath)).getAbsolutePath(), host);
            }

            if (capabilities.getCapability("browserName") != null) {
                capabilities.setCapability("browserName", "");
            }
        }

        try {
            LOCAL_DRIVER_VERSION = capabilities.getCapability("localDriverVersion").toString();
        } catch (NullPointerException var20) {
        }

        platformVersion = getPropVal(capsFromFile, "platformVersion");
        browserVersion = getPropVal(capsFromFile, "browserVersion");
        System.setProperty("mplatformfullname", platformVersion);
        System.setProperty("mbrowserfullname", browser + " " + browserVersion);
        return createDriver(capabilities, host, browser, port);
    }

    protected static String downloadFromHockeyApp(String token, String app, String version, String folderPath) throws Exception {
        HockeyAppApi api = new HockeyAppApi(token, "https://rink.hockeyapp.net/api/2/");
        Log.info("Checking the latest apk version from HockeyApp");
        Object o = api.getAppVersion(app);
        String jsonStr = (new JSONObject(o)).toString();
        JSONObject json = new JSONObject(jsonStr);
        JSONArray app_versions = json.getJSONArray("app_versions");
        JSONObject appVersion = app_versions.getJSONObject(0);
        Integer id = (Integer) appVersion.get("id");
        if (version.isEmpty()) {
            version = appVersion.getString("version");
        }

        String url = String.format("apps/%s/app_versions/%d?format=apk", app, id);
        String fileName = folderPath + version + ".apk";
        if (Files.notExists(Paths.get(fileName), new LinkOption[0])) {
            Log.info("Downloading: " + version + ".apk from HockeyApp");
            api.sendGet(url, fileName);
            Log.info("Download completed");
        } else {
            Log.info("Already have the latest version");
        }

        return fileName;
    }

    protected static WebDriver createDriver(DesiredCapabilities capabilities, String host, String browser, int port) throws Exception {
        Object driver = null;
        if (host.equals("localhost")) {
            Class var7;
            switch (processBrowserName(browser)) {
                case "firefox":
                    var7 = DriverFactory.class;
                    synchronized (DriverFactory.class) {
                        WebDriverManager.firefoxdriver().driverVersion(LOCAL_DRIVER_VERSION).setup();
                    }

                    driver = new FirefoxDriver(capabilities);
                    break;
                case "chrome":
                    var7 = DriverFactory.class;
                    synchronized (DriverFactory.class) {
                        WebDriverManager.chromedriver().driverVersion(LOCAL_DRIVER_VERSION).setup();
                    }

                    if (port > 0) {
                        driver = new ChromeDriver((ChromeDriverService) ((ChromeDriverService.Builder) (new ChromeDriverService.Builder()).usingPort(port)).build(), capabilities);
                    } else {
                        driver = new ChromeDriver(capabilities);
                    }
                    break;
                case "ie":
                    var7 = DriverFactory.class;
                    synchronized (DriverFactory.class) {
                        WebDriverManager.iedriver().driverVersion(LOCAL_DRIVER_VERSION).setup();
                    }

                    driver = new InternetExplorerDriver(capabilities);
                    break;
                case "edge":
                    var7 = DriverFactory.class;
                    synchronized (DriverFactory.class) {
                        WebDriverManager.edgedriver().driverVersion(LOCAL_DRIVER_VERSION).setup();
                    }

                    driver = new EdgeDriver(capabilities);
                    break;
                case "safari":
                    driver = new SafariDriver(capabilities);
                    break;
                default:
                    throw new Exception("Unsupported browser: " + browser);
            }
        }

        if (!isLocalHost()) {
            ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        }

        ((WebDriver) driver).manage().timeouts().implicitlyWait((long) IMPLICIT_WAIT_TIME_OUT, TimeUnit.SECONDS);

        try {
            String driverSession = driver.toString();
            String currentSessionId = driverSession.substring(driverSession.indexOf("(") + 1, driverSession.indexOf(")"));
            sessionId.set(currentSessionId);
            if (Listener.getTestCaseContext() != null) {
                Listener.getTestCaseContext().setSessionId(currentSessionId);
                Listener.allSessions.put(currentSessionId, Listener.getTestCaseContext());
            }
        } catch (Exception var12) {
            Log.warn("Unable to store session Id for driver - " + driver.toString());
            var12.printStackTrace();
        }

        return (WebDriver) driver;
    }

    public static boolean isSauceLabHost() {
        String host = System.getProperty("mhost");
        return host != null && host.contains("saucelabs");
    }

    public static boolean isLocalHost() {
        String host = System.getProperty("mhost");
        return host == null || host.isEmpty() || host.equals("localhost") || host.equals("127.0.0.1");
    }

    public static String getCurrentSessionId() {
        return String.valueOf(sessionId.get());
    }

    public static DesiredCapabilities getCapabilities() {
        return capabilities;
    }

    protected static DesiredCapabilities getCapabilities(Map<String, Object> capsFromFile, String platform, String browser, String host, String customCaps, String proxy, String buildVersion, String testCaseId) {
        capsFromFile = parseCaps(capsFromFile);
        DesiredCapabilities capabilities = getCapabilities(platform, browser);
        if (!StringUtils.isEmpty(proxy)) {
            Proxy proxy_caps = new Proxy();
            proxy_caps.setHttpProxy(proxy);
            proxy_caps.setSslProxy(proxy);
            capabilities.setCapability("proxy", proxy_caps);
        }

        if (host.equals("localhost")) {
            addCapsFromFile(capabilities, capsFromFile, true);
            addCustomCaps(capabilities, customCaps);
            clearRemoteArgs(capabilities);
        } else {
            capabilities.setCapability("acceptSslCert", "true");
            if (host.contains("saucelabs")) {
                addCapsFromFile(capabilities, capsFromFile, false);
                if (!isMobilePlatform(platform)) {
                    capabilities.setCapability("platform", capsFromFile.get("platformVersion"));
                }

                capabilities.setCapability("version", capsFromFile.get("browserVersion"));
                capabilities.setCapability("name", System.getProperty("suitename") + "_" + testCaseId);
                capabilities.setCapability("tags", testCaseId);
                capabilities.setCapability("build", buildVersion);
            } else if (host.contains("browserstack")) {
                addCapsFromFile(capabilities, capsFromFile, false);
                if (!isMobilePlatform(platform)) {
                    capabilities.setCapability("platform", capsFromFile.get("platformVersion"));
                }

                capabilities.setCapability("version", capsFromFile.get("browserVersion"));
                DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH:mm:ss");
                String date = df.format(new Date());
                capabilities.setCapability("name", System.getProperty("suitename") + "_" + testCaseId + "_" + date);
                capabilities.setCapability("tags", testCaseId);
                capabilities.setCapability("build", buildVersion);
            } else {
                addCapsFromFile(capabilities, capsFromFile, true);
                clearRemoteArgs(capabilities);
            }

            String app = getPropVal(capsFromFile, "app");
            if (!app.isEmpty()) {
                setAppCapabilities(capabilities, (new File(app)).getAbsolutePath(), host);
            }

            addCustomCaps(capabilities, customCaps);
        }

        return capabilities;
    }

    protected static boolean isMobilePlatform(String platform) {
        platform = platform.toLowerCase();
        return platform.contains("android") || platform.equals("iphone") || platform.equals("ipad");
    }

    protected static Map<String, Object> parseCaps(Map<String, Object> caps) {
        return (Map) caps.entrySet().stream().collect(Collectors.toMap((e) -> {
            return (String) e.getKey();
        }, (e) -> {
            return ((String) e.getKey()).equals("chromeOptions") ? parseChromeOptions(e.getValue()) : e.getValue();
        }));
    }

    protected static ChromeOptions parseChromeOptions(Object o) {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (o instanceof ArrayList) {
            ArrayList args = (ArrayList) o;
            if (args.size() > 0) {
                Object firstElem = args.get(0);
                if (firstElem instanceof String) {
                    chromeOptions.addArguments(args);
                } else {
                    args.forEach((elem) -> {
                        if (elem instanceof ArrayList) {
                            chromeOptions.addArguments((ArrayList) elem);
                        } else if (elem instanceof Map) {
                            Map<String, Object> map = (Map) elem;
                            map.forEach((k, v) -> {
                                chromeOptions.setExperimentalOption(k, v);
                            });
                        }

                    });
                }
            }
        } else if (o instanceof Map) {
            Map<String, Object> map = (Map) o;
            map.forEach((k, v) -> {
                chromeOptions.setExperimentalOption(k, v);
            });
        }

        return chromeOptions;
    }

    protected static void clearRemoteArgs(DesiredCapabilities caps) {
        caps.setCapability("platform", Platform.ANY);
        if (caps.getCapability("version") != null) {
            caps.setCapability("version", "");
        }

    }

    protected static DesiredCapabilities getCapabilities(String platform, String browser) {
        DesiredCapabilities capabilities = null;
        switch (processBrowserName(browser)) {
            case "firefox":
                FirefoxProfile myProfile = new FirefoxProfile();
                myProfile.setPreference("security.insecure_field_warning.contextual.enabled", false);
                myProfile.setAcceptUntrustedCertificates(true);
                myProfile.setAssumeUntrustedCertificateIssuer(true);
                capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("firefox_profile", myProfile);
                break;
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                break;
            case "edge":
                capabilities = DesiredCapabilities.edge();
                break;
            case "ie":
                capabilities = DesiredCapabilities.internetExplorer();
                break;
            case "safari":
                capabilities = DesiredCapabilities.safari();
                break;
            case "ios":
                if (platform.contains("iphone")) {
                    capabilities = DesiredCapabilities.iphone();
                } else if (platform.contains("ipad")) {
                    capabilities = DesiredCapabilities.ipad();
                }
                break;
            case "android":
                capabilities = DesiredCapabilities.android();
                break;
            default:
                throw new RuntimeException("Unknown platform = " + platform + " for Browser = " + browser);
        }

        Platform plat = Platform.fromString(processPlatformName(platform));
        capabilities.setPlatform(plat);
        return capabilities;
    }

    protected static void addCapsFromFile(DesiredCapabilities caps, Map capsFromFile, boolean localHost) {
        capsFromFile.forEach((k, v) -> {
            String key = String.valueOf(k);
            if (localHost) {
                if (!key.equals("browserVersion")) {
                    caps.setCapability(key, v);
                }
            } else {
                caps.setCapability(key, v);
            }

            caps.setCapability(key, v);
        });
    }

    protected static void addCustomCaps(DesiredCapabilities caps, String params) {
        if (StringUtils.isNotEmpty(params)) {
            String[] paramArr = params.split(";");
            String[] var3 = paramArr;
            int var4 = paramArr.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String param = var3[var5];
                int index = param.lastIndexOf(":");
                String key = param.substring(0, index);
                Object value = param.substring(index + 1, param.length());
                updateCapability(caps, key, value);
            }
        }

    }

    protected static Object parseStringToPrimitive(Object obj) {
        String DOUBLE_PATTERN = "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*";
        String INTEGER_PATTERN = "-?[0-9]+";
        if (obj instanceof String) {
            String str = ((String) obj).trim();
            if (str.equals("true")) {
                return true;
            }

            if (str.equals("false")) {
                return false;
            }

            if (str.matches(INTEGER_PATTERN)) {
                return Integer.valueOf(str);
            }

            if (str.matches(DOUBLE_PATTERN)) {
                return Double.valueOf(str);
            }
        }

        return obj;
    }

    protected static void updateCapability(DesiredCapabilities caps, String key, Object value) {
        String[] keys = key.split("\\.");
        if (keys.length != 0) {
            if (keys.length == 1) {
                caps.setCapability(key, parseStringToPrimitive(value));
            } else {
                Object firstCap = caps.getCapability(keys[0]);
                if (firstCap instanceof ChromeOptions) {
                    ChromeOptions opt = (ChromeOptions) firstCap;
                    Map map = (Map) opt.getExperimentalOption(keys[1]);
                    setPropVal(map, (String[]) Arrays.copyOfRange(keys, 2, keys.length), parseStringToPrimitive(value));
                } else if (firstCap instanceof Map) {
                    setPropVal((Map) firstCap, (String[]) Arrays.copyOfRange(keys, 1, keys.length), parseStringToPrimitive(value));
                }
            }

        }
    }

    protected static void setPropVal(Map map, String[] keys, Object value) {
        if (keys.length > 1) {
            for (int i = 0; i < keys.length - 1; ++i) {
                map = (Map) map.get(keys[i]);
            }

            map.put(keys[keys.length - 1], value);
        } else if (keys.length == 1) {
            map.put(keys[0], value);
        }

    }

    protected static String getPropVal(Map properties, String key) {
        Object prop = properties.get(key);
        if (prop != null) {
            return prop instanceof String ? (String) prop : String.valueOf(prop);
        } else {
            return "";
        }
    }

    protected static String getPlatformName() {
        String platform = System.getProperty("os.name").toLowerCase();
        if (platform.contains("win")) {
            platform = "windows";
        } else if (platform.contains("mac")) {
            platform = "mac";
        } else if (platform.contains("linux")) {
            platform = "linux";
        }

        return platform;
    }

    protected static Map loadPropertiesFromFile(String pathToFile) {
        Map res = null;

        try {
            FileInputStream input = new FileInputStream(pathToFile);
            Throwable var3 = null;

            try {
                Yaml yaml = new Yaml();
                res = (Map) yaml.load(input);
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (input != null) {
                    if (var3 != null) {
                        try {
                            input.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        input.close();
                    }
                }

            }
        } catch (Exception var15) {
            var15.printStackTrace();
        }

        return res;
    }

    private static void setAppCapabilities(DesiredCapabilities caps, String app, String port) {
        if (port.contains("saucelabs")) {
            try {
                String appId = app;
                File appFile = new File(app);
                if (appFile.exists()) {
                    MobileAppInfoHolder appInfoHolder = new MobileAppInfoHolder();
                    appId = appInfoHolder.checkAndUpload(appFile);
                }

                caps.setCapability("app", "sauce-storage:" + appId);
            } catch (Exception var6) {
                Log.error(var6.getMessage());
            }
        } else {
            caps.setCapability("app", app);
        }

        if (caps.getCapability("browserName") != null) {
            caps.setCapability("browserName", "");
        }

    }

    protected static String getPropertyValue(String propertyName, Map<String, Object> settings, String settingKey, String defaultValue) {
        if (propertyName != null && !propertyName.isEmpty()) {
            Log.info(String.format("Property '%s' was init from param, value: %s", settingKey, propertyName));
        } else {
            propertyName = getPropVal(settings, settingKey);
            if (propertyName == null || propertyName.isEmpty()) {
                Log.info(String.format("Property '%s' set to the default value: %s", settingKey, defaultValue));
                return defaultValue;
            }

            Log.info(String.format("Property '%s' was init from file, value: %s", settingKey, propertyName));
        }

        return propertyName;
    }

    private static String processPlatformName(String platform) {
        if (platform.toLowerCase().contains("android")) {
            return "android";
        } else {
            return !platform.toLowerCase().contains("iphone") && !platform.toLowerCase().contains("ipad") ? platform : "mac";
        }
    }

    private static String processBrowserName(String browser) {
        if (browser.toLowerCase().contains("chrome")) {
            return "chrome";
        } else if (browser.toLowerCase().contains("firefox")) {
            return "firefox";
        } else if (browser.toLowerCase().contains("android")) {
            return "android";
        } else {
            return browser.toLowerCase().contains("ios_") ? "ios" : browser;
        }
    }
}
