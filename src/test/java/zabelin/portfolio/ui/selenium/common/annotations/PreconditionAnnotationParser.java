package zabelin.portfolio.ui.selenium.common.annotations;

import zabelin.portfolio.ui.selenium.common.env.EnvConstants;
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


    private static String getPageUrl(String page) {
        if (page.isEmpty())
            return null;
        switch (page) {
            case Preconditions.Site.Page.HOME:
                return EnvConstants.HOME_PAGE;
            case Preconditions.Site.Page.BROKEN_IMAGES_PAGE:
                return EnvConstants.BROKEN_IMAGES_PAGE;
            case Preconditions.Site.Page.ADD_REMOVE_ELEMENTS_PAGE:
                return EnvConstants.ADD_REMOVE_ELEMENTS_PAGE;
            default:
                return page;
        }
    }
}