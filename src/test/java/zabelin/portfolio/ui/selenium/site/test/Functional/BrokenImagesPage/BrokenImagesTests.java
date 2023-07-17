package zabelin.portfolio.ui.selenium.site.test.Functional.BrokenImagesPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.ui.selenium.common.annotations.Precondition;
import zabelin.portfolio.ui.selenium.common.model.Preconditions;
import zabelin.portfolio.ui.selenium.common.state.BaseSiteState;
import zabelin.portfolio.ui.selenium.site.frontend.constants.NavigationConstants;
import zabelin.portfolio.ui.selenium.site.frontend.pages.broken_images_page.BrokenImagesPage;
import zabelin.portfolio.ui.selenium.site.frontend.pages.home_page.HomePage;

public class BrokenImagesTests extends BaseSiteState {

    private BrokenImagesPage navigateToBrokenImagesPage() throws Exception {
        Log.step("1. Go to Broken Images page");
        new HomePage(driver).navigation.clickMenuLink(NavigationConstants.MenuLinks.BROKEN_IMAGES);
        return new BrokenImagesPage(driver);
    }

    @Test(description = "C34212354362 images on this page 1")
    public void testImagesOnThisPage1() throws Exception {
        BrokenImagesPage brokenImagesPage = navigateToBrokenImagesPage();

        // verify all images are visible
        Assert.assertTrue(brokenImagesPage.isImagesAreVisible(), "Images are not visible");
    }

    @Test(description = "C3421232 images on this page 2")
    @Precondition(page = Preconditions.Site.Page.BROKEN_IMAGES_PAGE)
    public void testImagesOnThisPage2() throws Exception {

        Log.step("1. Go to Broken Images page");

        // verify all images are visible
        Assert.assertTrue(new BrokenImagesPage(driver).isImagesAreVisible(),
                "Images are not visible");

    }

    @Test(description = "C3421231 images on this page 3")
    @Precondition(page = Preconditions.Site.Page.BROKEN_IMAGES_PAGE)
    public void testImagesOnThisPage3() throws Exception {

        Log.step("1. Go to Broken Images page");

        // verify that all images are not broken
        Assert.assertTrue(new BrokenImagesPage(driver).isAllImagesNotBroken(),
                "some images are broken");

    }
}
