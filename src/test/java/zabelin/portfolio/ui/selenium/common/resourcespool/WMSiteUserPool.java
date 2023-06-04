package zabelin.portfolio.ui.selenium.common.resourcespool;

import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.ui.selenium.common.env.EnvConstants;
import zabelin.portfolio.ui.selenium.common.model.Preconditions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static zabelin.portfolio.ui.selenium.common.state.BaseSiteState.isMobileBrowser;

public class WMSiteUserPool extends CommonResourcesPool {

    public WMSiteUserPool() {
        Log.debug("WMSiteUserPool started initializing");
        parsePhoneUsers();
        Log.debug("WMSiteUserPool initializing has been finished");
    }

    private void parsePhoneUsers() {
        //desktop users set
//        Map<Blockable, String> phoneDesktopCollection = new HashMap<>();
//        List<String> logins = Arrays.asList(EnvConstants.DESKTOP_PHONE_USERS_LOGIN.split("\\|"));
//        List<String> emails = Arrays
//                .stream(logins.toArray())
//                .map((s) -> s + EnvConstants.DESKTOP_PHONE_USERS_EMAIL_POSTFIX)
//                .collect(Collectors.toList());
//        for (int i = 0; i < logins.size(); i++)
//            phoneDesktopCollection.put(
//                    null, // откоментить строку снизу
////                    new User(logins.get(i), EnvConstants.DEFAULT_PASSWORD, emails.get(i)),
//                    null);
//        resourcesCollection.put(Preconditions.Site.User.ORDERING + " desktop", phoneDesktopCollection);
//
//        //mobile users set
//        Map<Blockable, String> phoneMobileCollection = new HashMap<>();
//        logins = Arrays.asList(EnvConstants.MOBILE_PHONE_USERS_LOGIN.split("\\|"));
//        emails = Arrays
//                .stream(logins.toArray())
//                .map((s) -> s + EnvConstants.MOBILE_PHONE_USERS_EMAIL_POSTFIX)
//                .collect(Collectors.toList());
//        for (int i = 0; i < logins.size(); i++)
//            phoneMobileCollection.put(
//                    null, // откоментить строку снизу
////                    new User(logins.get(i), EnvConstants.DEFAULT_PASSWORD, emails.get(i)),
//                    null);
//        resourcesCollection.put(Preconditions.Site.User.ORDERING + " mobile", phoneMobileCollection);
    }

//    public User getUser(String userRole, String testIdentity) throws InterruptedException {
//        if (userRole.equals(Preconditions.Site.User.ORDERING)) {
//            userRole = userRole + (isMobileBrowser() ? " mobile" : " desktop");
//        }
//        return (User) getObject(userRole, testIdentity);
//    }

}
