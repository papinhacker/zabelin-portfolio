package zabelin.portfolio.ui.selenium.common.annotations;

import zabelin.portfolio.ui.selenium.common.env.EnvConstants;
import zabelin.portfolio.ui.selenium.common.model.LoginPageData;
import zabelin.portfolio.ui.selenium.common.model.Preconditions;

import java.lang.reflect.Method;

public class PreconditionAnnotationParser extends ResourceAnnotationParser {

    public static String parseUrl(Method method) {
        String url = null;
        if (method.getDeclaringClass().isAnnotationPresent(BaseState.class)) {
            BaseState baseState = method.getDeclaringClass().getAnnotation(BaseState.class);
            if (!baseState.page().isEmpty())
                url = getPageUrl(baseState.page());
        }
        if (method.isAnnotationPresent(Precondition.class)) {
            Precondition precondition = method.getAnnotation(Precondition.class);
            if (!precondition.page().isEmpty())
                url = getPageUrl(precondition.page());
        }
        return url;
    }

    private static LoginPageData getLoginData(String user) {
        switch (user) {
            //WM Site
            case Preconditions.WMSite.User.BASIC_USER:
                return new LoginPageData(EnvConstants.BASIC_USER_LOGIN);
            case Preconditions.WMSite.User.EMPTY_USER:
                return new LoginPageData(EnvConstants.EMPTY_USER_LOGIN);
            case Preconditions.WMSite.User.CHANGE_DATA_USER:
                return new LoginPageData(EnvConstants.CHANGE_DATA_USER_LOGIN);
            case Preconditions.WMSite.User.LISTING_OWNER:
                return new LoginPageData(EnvConstants.LISTING_OWNER_USER_LOGIN);
            case Preconditions.WMSite.User.TRASH_LISTING_OWNER:
                return new LoginPageData(EnvConstants.TRASH_LISTING_OWNER_USER_LOGIN);
            case Preconditions.WMSite.User.BRAND_OWNER:
                return new LoginPageData(EnvConstants.BRAND_OWNER_USER_LOGIN);
            case Preconditions.WMSite.User.OPS_MANAGER:
                return new LoginPageData(EnvConstants.SUPER_USER_LOGIN);
            case Preconditions.WMSite.User.MODERATOR:
                return new LoginPageData(EnvConstants.MODERATOR_USER_LOGIN);
            case Preconditions.WMSite.User.ONBOARDING_ADMIN:
                return new LoginPageData(EnvConstants.ONBOARDING_USER_LOGIN);
            case Preconditions.WMSite.User.SALES_MANAGER:
                return new LoginPageData(EnvConstants.SALES_MANAGER_USER_LOGIN);
            case Preconditions.WMSite.User.TAXES_ADMIN:
                return new LoginPageData(EnvConstants.TAXES_USER_LOGIN);
            case Preconditions.WMSite.User.ORGANIZATION_ADMIN:
                return new LoginPageData(EnvConstants.ORGANIZATION_ADMIN_LOGIN, EnvConstants.ORGANIZATION_ADMIN_PASSWORD);
            case Preconditions.WMSite.User.ORGANIZATION_MANAGER:
                return new LoginPageData(EnvConstants.ORGANIZATION_MANAGER_LOGIN, EnvConstants.ORGANIZATION_MANAGER_PASSWORD);

            //Logistics
            case Preconditions.Logistics.User.MULTI_ORG_OWNER_USER:
                return new LoginPageData(EnvConstants.MULTI_ORG_OWNER_NAME);
            case Preconditions.Logistics.User.MULTI_ORG_ADMIN_USER:
                return new LoginPageData(EnvConstants.MULTI_ORG_ADMIN_NAME);
            case Preconditions.Logistics.User.MULTI_ORG_DISPATCHER_USER:
                return new LoginPageData(EnvConstants.MULTI_ORG_DISPATCHER_NAME);
            case Preconditions.Logistics.User.ORG_MULTI_PICKUP_ADMIN_USER:
                return new LoginPageData(EnvConstants.ORG_MULTI_PICKUP_ADMIN_NAME);
            case Preconditions.Logistics.User.ORG_MULTI_PICKUP_DISPATCHER_USER:
                return new LoginPageData(EnvConstants.ORG_MULTI_PICKUP_DISPATCHER_NAME);
            case Preconditions.Logistics.User.ORG_SINGLE_PICKUP_OWNER_USER:
                return new LoginPageData(EnvConstants.ORG_WITH_SINGLE_PICKUP_OWNER_NAME);
            case Preconditions.Logistics.User.ORG_SINGLE_DELIVERY_OWNER_USER:
                return new LoginPageData(EnvConstants.ORG_WITH_SINGLE_DELIVERY_OWNER_NAME);
            case Preconditions.Logistics.User.ORG_SINGLE_DELIVERY_ADMIN_USER:
                return new LoginPageData(EnvConstants.ORG_WITH_SINGLE_DELIVERY_ADMIN_NAME);
            case Preconditions.Logistics.User.ORG_SINGLE_DELIVERY_DRIVER_USER:
                return new LoginPageData(EnvConstants.ORG_WITH_SINGLE_DELIVERY_DRIVER1_NAME);
            case Preconditions.Logistics.User.OPS_MANAGER_USER:
                return new LoginPageData(EnvConstants.LOGISTIC_OPS_MANAGER_LOGIN);

            //WM Store
            case Preconditions.WMStore.User.BASE_USER:
                return new LoginPageData(EnvConstants.WM_STORE_BASE_USER_NAME);
            case Preconditions.WMStore.User.STORE_OWNER:
                return new LoginPageData(EnvConstants.WM_STORE_STORE_OWNER_NAME);
            case Preconditions.WMStore.User.BRAND_OWNER:
                return new LoginPageData(EnvConstants.WM_STORE_BRAND_OWNER_NAME);

            default:
                return null;
        }
    }

    private static String getPageUrl(String page) {
        if (page.isEmpty())
            return null;
        switch (page) {
            case Preconditions.WMSite.Page.HOME:
                return EnvConstants.HOME_PAGE;
            case Preconditions.WMSite.Page.LOGIN_PAGE:
                return EnvConstants.LOGIN_PAGE;
            default:
                return page;
        }
    }
}