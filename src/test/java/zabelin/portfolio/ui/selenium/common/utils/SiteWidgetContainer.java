package zabelin.portfolio.ui.selenium.common.utils;

import org.openqa.selenium.WebDriver;

public class SiteWidgetContainer extends SiteHelper {

    public final String CONTAINER_BASE;

    public SiteWidgetContainer(WebDriver driver, String xpathContainerBase) {
        super(driver);
        CONTAINER_BASE = xpathContainerBase;
    }

    public SiteWidgetContainer(WebDriver driver, String xpathContainerBase, int index) {
        super(driver);
        CONTAINER_BASE = "(" + xpathContainerBase + ")[" + (index + 1) + "]";
    }
}

