package zabelin.portfolio.ui.selenium.site.frontend.common_components.images;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteWidgetContainer;

public class ImagesRow extends SiteWidgetContainer {

    public ImagesRow(WebDriver driver, String base) {
        super(driver, base);
    }

    private ImagesRow(WebDriver driver, String base, int index) {
        super(driver, base, index);
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/

    private String images = CONTAINER_BASE + "//img";

    /* **************************************
     *************** Methods ****************
     ***************************************/

    public int getImagesCount() {
        return getCount(images, short_timeout);
    }

    // да да, я знаю, что тут класса не хватает, но я что-то вообще перемудрил с этим демо проектом.
    public ImagesRow get(int index) {
        return new ImagesRow(driver, CONTAINER_BASE, index);
    }

    public boolean isImagesVisible() {
        return isAllVisible(images);
    }
}
