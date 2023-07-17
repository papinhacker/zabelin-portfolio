package zabelin.portfolio.ui.selenium.site.frontend.pages.broken_images_page;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteHelper;
import zabelin.portfolio.ui.selenium.site.frontend.common_components.images.ImageCard;

public class BrokenImagesPage extends SiteHelper {

    public ImageCard images = new ImageCard(driver, "//div[@class='example' and ./h3[text()='Broken Images']]//img");

    public BrokenImagesPage(WebDriver driver) {
        super(driver);
        pageLoading();
    }

    /* **************************************
     *************** Methods ****************
     ***************************************/

    public void pageLoading() {
        try {
            if (!isVisible(images.CONTAINER_BASE, short_timeout))
                throw new Exception();
        } catch (Exception ex) {
            throw new AssertionError("Broken Images Page was not loaded");
        }
    }

    public boolean isImagesAreVisible() {
        return images.isImagesVisible();
//        int count = images.getImagesCount();
//        for (int i = 0; i < count; i++) {
//            if (!images.isVisible(images.get(i).CONTAINER_BASE, short_timeout))
//                return false;
//        }
//        return true;
    }

    /**
     * Return true if all images not broken
     * @return
     */
    public boolean isAllImagesNotBroken() throws Exception{
        try {
            for (int i = 0; i < images.getImagesCount(); i++) {
                if (!images.get(i).checkIsImageNotBroken()) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            throw new Exception("Can't check are images are broken. Reason is: " + ex.getMessage());
        }
    }
}
