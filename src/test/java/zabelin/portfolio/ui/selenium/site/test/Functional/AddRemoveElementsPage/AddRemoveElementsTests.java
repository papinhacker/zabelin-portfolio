package zabelin.portfolio.ui.selenium.site.test.Functional.AddRemoveElementsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.ui.selenium.common.annotations.Precondition;
import zabelin.portfolio.ui.selenium.common.model.Preconditions;
import zabelin.portfolio.ui.selenium.common.state.BaseSiteState;
import zabelin.portfolio.ui.selenium.site.frontend.pages.add_remove_elements_page.AddRemoveElementsPage;


public class AddRemoveElementsTests extends BaseSiteState {

    @Test(description = "C4568 add elements")
    @Precondition(page = Preconditions.Site.Page.ADD_REMOVE_ELEMENTS_PAGE)
    public void testAddElements() throws Exception {

        Log.step("1. Go to Add/Remove Elements page");
        AddRemoveElementsPage addRemoveElementsPage = new AddRemoveElementsPage(driver);
        int count = addRemoveElementsPage.clickAddElementButtonXTimes();

        Assert.assertEquals(count, addRemoveElementsPage.getDeleteButtonsCount(), "Incorrect number of items has been created");

        for (int i = 0; i < count; i++) {
            addRemoveElementsPage.clickDeleteButton();
        }

        Assert.assertFalse(addRemoveElementsPage.isDeleteButtonVisible(), "Not all 'delete' buttons has been removed");
    }

}
