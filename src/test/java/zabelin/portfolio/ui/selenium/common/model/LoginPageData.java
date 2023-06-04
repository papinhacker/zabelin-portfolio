package zabelin.portfolio.ui.selenium.common.model;

import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import zabelin.portfolio.ui.selenium.common.env.EnvConstants;

import java.util.Properties;

/**
 * Created by nes on 7/6/2017.
 */
public class LoginPageData extends EnvConstants {

    protected String userName;
    protected String password;
    protected String email;

    public LoginPageData() {
        this(false);
    }

    public LoginPageData(boolean setRandom) {
        if (setRandom)
            setRandomLoginPageData();
        else {
//            userName = EnvConstants.BASIC_USER_LOGIN;
//            password = EnvConstants.DEFAULT_PASSWORD;
//            email = EnvConstants.BASIC_USER_EMAIL;
        }
    }

    public LoginPageData(String userName) {
        this.userName = userName;
//        this.password = EnvConstants.DEFAULT_PASSWORD;
    }

    public LoginPageData(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginPageData(String userName, String password, String email) {
        this(userName, password);
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRandomUserNameAndPassword() {
        this.userName = RandomStringUtils.randomAlphabetic(8);
        this.password = RandomStringUtils.randomAlphabetic(8);
    }

    public void setRandomLoginPageData() {
        userName = RandomStringUtils.randomAlphabetic(10);
//        password = EnvConstants.DEFAULT_PASSWORD;
        email = userName + "@" + RandomStringUtils.randomAlphabetic(8) + ".com";
    }

    public String getEmail() {
//        if (email == null) {
//            return new GetMe().get(ApiAuthHelper.getTargetUserToken(userName, password)).getString("email");
//        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //for testing purposes
    public static void reload(String env) {
        Properties props = getPropertiesForEnv(env);
        properties.clear();
        properties.putAll(props);
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
