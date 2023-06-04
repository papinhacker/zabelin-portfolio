package zabelin.portfolio.ui.selenium.site.frontend.common_components.footer;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteHelper;

public class Footer extends SiteHelper {

    public Footer(WebDriver driver) {
        super(driver);
        widgetLoading();
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/

    private static final String BASE = "//div[contains(text(), 'Powered by')]";

    /* **************************************
     *************** Methods ****************
     ***************************************/

    public void scrollToFooter() throws Exception {
        try {
            scrollTo(BASE, true);
        } catch (Exception ex) {
            throw new Exception("Can't scroll to footer section");
        }
    }

    private void widgetLoading() {
        try {
            if (!isVisible(BASE, short_timeout))
                throw new Exception();
        } catch (Exception ex) {
            throw new AssertionError("Footer was not loaded");
        }
    }
}
