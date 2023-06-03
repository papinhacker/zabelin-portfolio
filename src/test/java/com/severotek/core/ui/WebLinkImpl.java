//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.severotek.core.ui;

import com.severotek.core.common.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebLinkImpl extends WebElementImpl {
    public WebLinkImpl(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getHRef() throws Exception {
        try {
            return this.WebElement.getAttribute("href");
        } catch (Exception var2) {
            Log.error("Method: getHRef()");
            Log.error("Error: There was a problem getting the 'href' attribute for the WebLink");
            Log.error("Exception: " + var2.getMessage());
            throw var2;
        }
    }

    public String getHRef(int timeout) throws Exception {
        this.waitForVisibility(timeout);
        return this.getHRef();
    }
}
