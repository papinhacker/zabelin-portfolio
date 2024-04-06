package zabelin.portfolio.ui.selenium.site.frontend.common_components.images;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteWidgetContainer;

public class ImageCard extends SiteWidgetContainer {

    public ImageCard(WebDriver driver, String base) {
        super(driver, base);
    }

    private ImageCard(WebDriver driver, String base, int index) {
        super(driver, base, index);
    }


    /* **************************************
     *************** Locators ***************
     ***************************************/

    /* **************************************
     *************** Methods ****************
     ***************************************/

    public int getImagesCount() {
        return getCount(CONTAINER_BASE, short_timeout);
    }

    public ImageCard get(int index) {
        return new ImageCard(driver, CONTAINER_BASE, index);
    }

    public boolean isImagesVisible() {
        return isAllVisible(CONTAINER_BASE);
    }

    public boolean checkIsImageNotBroken() throws Exception {
        try {
            return checkImageIsNotBroken(CONTAINER_BASE);
        } catch (Exception ex) {
            throw new RuntimeException("Can't check image is broken. Reason is: " + ex.getMessage());
        }
    }
}
