package zabelin.portfolio.ui.selenium.site.frontend.pages.add_remove_elements_page;

import org.openqa.selenium.WebDriver;
import zabelin.portfolio.ui.selenium.common.utils.SiteHelper;

import java.util.concurrent.ThreadLocalRandom;

public class AddRemoveElementsPage extends SiteHelper {

    public AddRemoveElementsPage(WebDriver driver) {
        super(driver);
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/

    private String addElementButton = "//button[@onclick='addElement()']";
    private String deleteButton = "//button[@onclick='deleteElement()']";

    /* **************************************
     *************** Methods ****************
     ***************************************/

    /**
     * Click on 'Add element' button X times
     *
     * @param clicksCount
     * @throws Exception
     */
    public int clickAddElementButtonXTimes(Integer... clicksCount) throws Exception {
        try {
            int count;
            if (!isAddElementButtonEnabled()) {
                throw new AssertionError("'Add element' button isn't enabled");
            }
            if (clicksCount.length == 0) {
                count = ThreadLocalRandom.current().nextInt(10);
            } else {
                count = clicksCount[0];
            }
            for (int i = 0; i < count; i++) {
                click(addElementButton, short_timeout);
            }
            return count;
        } catch (Exception ex) {
            throw new RuntimeException("Can't click on card. Reason is: " + ex.getMessage());
        }
    }

    public boolean isAddElementButtonEnabled() throws Exception {
        return isEnabled(addElementButton, short_timeout) && isVisible(addElementButton, short_timeout);
    }

    public boolean isDeleteButtonVisible() throws Exception {
        return isVisible(deleteButton, short_timeout);
    }

    /**
     * Return count of 'Delete' buttons
     *
     * @return
     * @throws Exception
     */
    public int getDeleteButtonsCount() throws Exception {
        return getCount(deleteButton, short_timeout);
    }

    public void clickDeleteButton() throws Exception {
        try {
            if (!isDeleteButtonVisible()) {
                throw new AssertionError("There is no 'Delete' button");
            }
            scrollTo(deleteButton, 0);
            clickByIndex(deleteButton, 0, short_timeout);
        } catch (Exception ex) {
            throw new Exception("Can't click on the 'Delete' button. Reason is: " + ex.getMessage());
        }
    }
}
