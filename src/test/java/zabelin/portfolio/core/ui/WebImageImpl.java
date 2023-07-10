package zabelin.portfolio.core.ui;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import zabelin.portfolio.core.common.Log;

public class WebImageImpl extends WebElementImpl {
    public WebImageImpl(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getImageURL() throws Exception {
        try {
            return this.WebElement.getAttribute("src");
        } catch (Exception var2) {
            Log.error("Method: getImageURL()");
            Log.error("Error: There was a problem getting the 'src' attribute for the WebImage");
            Log.error("Exception: " + var2.getMessage());
            throw var2;
        }
    }

    public String getImageURL(int timeout) throws Exception {
        this.waitForVisibility(timeout);
        return this.getImageURL();
    }

    public boolean isImageLoaded() throws Exception {
        try {
            Object result = ((JavascriptExecutor) this.driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", new Object[]{this.WebElement});
            boolean loaded = false;
            if (result instanceof Boolean) {
                loaded = (Boolean) result;
            }

            return loaded;
        } catch (Exception var3) {
            Log.error("Method: isImageLoaded");
            Log.error("There was a problem to determinate if the image is loaded");
            Log.error("Exception: " + var3.getMessage());
            throw var3;
        }
    }

    public boolean isImageLoaded(int timeout) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) timeout);
            wait.until((driverObject) -> {
                try {
                    return this.isImageLoaded();
                } catch (Exception var3) {
                    return false;
                }
            });
            return true;
        } catch (Exception var3) {
            return false;
        }
    }
}
