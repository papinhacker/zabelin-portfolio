package zabelin.portfolio.ui.selenium.site.frontend.common_components.navigation;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteHelper;

public class Navigation extends SiteHelper {

    private static final String BASE = "//div[@id='content']/ul";
    private final boolean isMobile;

    /* **************************************
     *************** Locators ***************
     ***************************************/
    private final String menuLinks = BASE + "//a[text()]";
    private final String specificMenuLinks = menuLinks + "[text()='%s']";
    // example
    private String menu;

    public Navigation(WebDriver driver) {
        super(driver);
        isMobile = isMobileView(1024);
        initPage();
    }

    /* **************************************
     *************** Methods ****************
     ***************************************/


    // example

    /**
     * Initialize page locators
     *
     * @throws ExceptionInInitializerError
     */
    private void initPage() throws ExceptionInInitializerError {
        if (isMobile) {
            menu = "//div[contains(@class, 'modal hamburger-menu_')]//div[@data-testid='slide-modal']"; // hamburger menu
        } else {
            menu = BASE; // header navigation
        }
    }

    /**
     * Click on menu link
     *
     * @param menuLink
     */
    public void clickMenuLink(String menuLink) {
        try {
            String ml = menuLink;
            String ll = getAllText(menuLinks, 3).stream()
                    .map(l -> l.replaceAll("(?i)(new$|beta)", "").replaceAll("Currently have an active order", ""))
                    .filter(l -> l.toLowerCase().contains(ml.replaceAll("(?i)(new$|beta)", "").toLowerCase()))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Links list doesn't contain '" + ml + "' link"));
            click(String.format(specificMenuLinks, ll));
            waitForNProgressEnding();
            waitForNProgressEnding();
        } catch (Exception ex) {
            throw new RuntimeException("Can't click '" + menuLink + "' navigation link. Reason is: " + ex.getMessage());
        }
    }
}
