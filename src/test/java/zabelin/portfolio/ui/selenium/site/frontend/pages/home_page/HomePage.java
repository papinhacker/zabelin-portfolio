package zabelin.portfolio.ui.selenium.site.frontend.pages.home_page;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteHelper;
import zabelin.portfolio.ui.selenium.site.frontend.common_components.footer.Footer;
import zabelin.portfolio.ui.selenium.site.frontend.common_components.navigation.Navigation;

public class HomePage extends SiteHelper {

    public Navigation navigation = new Navigation(driver);
    public Footer footer = new Footer(driver);
    private final boolean isMobile;
    public HomePage(WebDriver driver) {
        super(driver);
        isMobile = isMobileView(1024);
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/



    /* **************************************
     *************** Methods ****************
     ***************************************/
}