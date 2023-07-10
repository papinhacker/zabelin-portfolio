package zabelin.portfolio.core.common;

import zabelin.portfolio.core.util.TextUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class EnvConstants {


    protected static final String PRODUCTION_ENV = "prod";
    public static final String PATH = getPath();
    public static final String ENV = getEnvironment();
    public static final Properties properties;

    public EnvConstants() {
    }

    protected static String getEnvironment() {
        String env = "prod";
        String mavenEnv = System.getProperty("menv");
        if (TextUtil.isNotNullNotEmpty(mavenEnv, true)) {
            env = mavenEnv;
        }

        Log.info("Running test in environment: " + env + "\n");
        return env;
    }

    protected static String getPath() {
        String mpath = System.getProperty("mpath");
        return TextUtil.isNotNullNotEmpty(mpath, true) ? mpath : "/src/main/resources/PropertyFiles/";
    }

    protected static String getProperty(String key) {
        return properties.getProperty(key);
    }

    protected static Properties getPropertiesForEnv(String env) {
        Properties properties = null;

        try {
            properties = new Properties();
            FileReader reader = new FileReader(System.getProperty("user.dir") + String.format(PATH + "%s.properties", env));
            properties.load(reader);
            File custom = new File(System.getProperty("user.dir") + PATH + "custom.properties");
            String val;
            if (custom.exists()) {
                Properties customPros = new Properties();
                reader = new FileReader(System.getProperty("user.dir") + PATH + "custom.properties");
                customPros.load(reader);
                Enumeration<String> enums = (Enumeration<String>) customPros.propertyNames();

                while (enums.hasMoreElements()) {
                    val = (String) enums.nextElement();
                    String value = customPros.getProperty(val);
                    properties.setProperty(val, value);
                }
            }

            Enumeration<String> enums = (Enumeration<String>) properties.propertyNames();

            while (enums.hasMoreElements()) {
                String key = (String) enums.nextElement();
                val = System.getProperty("P_" + key, "_UNDEF_");
                if (!val.equals("_UNDEF_")) {
                    properties.setProperty(key, val);
                }
            }
        } catch (IOException var8) {
        }

        return properties;
    }

    static {
        properties = getPropertiesForEnv(ENV);
    }
}
