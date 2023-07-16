package zabelin.portfolio.core.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import zabelin.portfolio.core.common.Log;

public class WebTinyMCEImpl extends WebElementImpl {
    public WebTinyMCEImpl(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getText() throws Exception {
        try {
            return (String) ((JavascriptExecutor) this.driver).executeScript("return tinyMCE.activeEditor.getContent()", new Object[0]);
        } catch (Exception var2) {
            Log.error("Method: getText()");
            Log.error("Error: There was a problem getting text from the WebTinyMCE");
            Log.error("Exception: " + var2.getMessage());
            throw var2;
        }
    }

    public String getText(int timeout) throws Exception {
        this.waitForVisibility(timeout);
        return this.getText();
    }

    public WebTinyMCEImpl clearAndFill(String text) throws Exception {
        try {
            ((JavascriptExecutor) this.driver).executeScript(String.format("tinyMCE.activeEditor.setContent('%s')", text));
            return this;
        } catch (Exception var3) {
            Log.error("Method: clearAndFill()");
            Log.error(String.format("Error: There was a problem sending text '%s' to the WebTinyMCE", text));
            Log.error("Exception: " + var3.getMessage());
            throw var3;
        }
    }

    public WebTinyMCEImpl clearAndFill(String text, int timeout) throws Exception {
        this.waitForVisibility(timeout);
        return this.clearAndFill(text);
    }
}
