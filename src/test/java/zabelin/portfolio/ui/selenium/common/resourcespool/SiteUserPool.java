package zabelin.portfolio.ui.selenium.common.resourcespool;

import zabelin.portfolio.core.common.Log;

public class SiteUserPool extends CommonResourcesPool {

    public SiteUserPool() {
        Log.debug("SiteUserPool started initializing");
        parseUsers();
        Log.debug("SiteUserPool initializing has been finished");
    }

    private void parseUsers() {
        // тут добавляем нужных юзеров в юзерпул
    }

}
