package zabelin.portfolio.core.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import zabelin.portfolio.core.common.Log;

public class WebRadioImpl extends WebElementImpl {
    public WebRadioImpl(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public boolean isSelected() throws Exception {
        try {
            return this.WebElement.isSelected();
        } catch (Exception var2) {
            Log.error("Method: isSelected()");
            Log.error("Error: There was a problem determining if the WebRadio is checked");
            Log.error("Exception: " + var2.getMessage());
            throw var2;
        }
    }

    public boolean isSelected(int timeout) throws Exception {
        try {
            this.waitForVisibility(timeout);
            return this.WebElement.isSelected();
        } catch (Exception var3) {
            Log.error("Method: isSelected()");
            Log.error("Error: There was a problem determining if the WebRadio is checked");
            Log.error("Exception: " + var3.getMessage());
            throw var3;
        }
    }
}
