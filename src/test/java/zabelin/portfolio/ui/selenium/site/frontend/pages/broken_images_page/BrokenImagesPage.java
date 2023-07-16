package zabelin.portfolio.ui.selenium.site.frontend.pages.broken_images_page;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteHelper;
import zabelin.portfolio.ui.selenium.site.frontend.common_components.images.ImagesRow;

public class BrokenImagesPage extends SiteHelper {

    private final String imagesRow = "//div[@class='example' and ./h3[text()='Broken Images']]";
    public ImagesRow images = new ImagesRow(driver, imagesRow);

    public BrokenImagesPage(WebDriver driver) {
        super(driver);
        pageLoading();
    }

    /* **************************************
     *************** Methods ****************
     ***************************************/

    public void pageLoading() {
        try {
            if (!isVisible(imagesRow, short_timeout))
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
}
