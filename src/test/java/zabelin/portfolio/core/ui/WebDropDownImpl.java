package zabelin.portfolio.core.ui;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import zabelin.portfolio.core.common.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class WebDropDownImpl extends WebElementImpl {
    public WebDropDownImpl(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public WebDropDownImpl waitAndClickDropDownElementByIndex(int index, int seconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.visibilityOf(this.WebElement));
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            ((WebElement) options.get(index)).click();
            return this;
        } catch (Exception var5) {
            String name = var5.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndClickDropDownElementByIndex");
                Log.error("Error: There was a problem clicking on the element with index " + index + "from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var5.getMessage().toString());
            throw var5;
        }
    }

    public List<String> getOptionsTextFromDropDown() throws Exception {
        try {
            List<String> optionsText = new ArrayList();
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            Iterator var3 = options.iterator();

            while (var3.hasNext()) {
                WebElement option = (WebElement) var3.next();
                optionsText.add(option.getText());
            }

            return optionsText;
        } catch (Exception var5) {
            Log.error("Method: getOptionsTextFromDropDown");
            Log.error("Exception:" + var5.getMessage().toString());
            throw var5;
        }
    }

    public WebDropDownImpl clickDropDownElementByIndex(int index) throws Exception {
        try {
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            ((WebElement) options.get(index)).click();
            return this;
        } catch (Exception var4) {
            String name = var4.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: clickDropDownElementByIndex");
                Log.error("Error: There was a problem clicking on the element with index " + index + "from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var4.getMessage().toString());
            throw var4;
        }
    }

    public WebDropDownImpl waitAndClickDropDownElementByText(String text, int seconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            Select dropDown = new Select(this.WebElement);
            dropDown.selectByVisibleText(text);
            return this;
        } catch (Exception var5) {
            String name = var5.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Error: There was a problem clicking on the element " + text + "from the drop down " + name + ".It may not be available");
            }

            Log.error("Method: waitAndClickDropDownElementByText");
            Log.error("Exception:" + var5.getMessage().toString());
            throw var5;
        }
    }

    public WebDropDownImpl clickDropDownElementByText(String text) throws Exception {
        try {
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            Iterator var6 = options.iterator();

            while (var6.hasNext()) {
                WebElement option = (WebElement) var6.next();
                if (text.equals(option.getText())) {
                    option.click();
                }
            }

            return this;
        } catch (Exception var5) {
            String name = var5.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: clickDropDownElementByText");
                Log.error("There was a problem clicking on the element " + text + "from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var5.getMessage().toString());
            throw var5;
        }
    }

    public String getDropDownSelectedText() {
        String text = null;
        String value = this.WebElement.getAttribute("value");
        List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
        Iterator var4 = options.iterator();

        while (var4.hasNext()) {
            WebElement option = (WebElement) var4.next();
            if (value.equals(option.getAttribute("value"))) {
                text = option.getText();
            }
        }

        return text;
    }

    public WebDropDownImpl waitAndClickDropDownElementByValue(String option, int seconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            Iterator var5 = options.iterator();

            while (var5.hasNext()) {
                WebElement optionElement = (WebElement) var5.next();
                if (option.equals(optionElement.getAttribute("value"))) {
                    optionElement.click();
                    break;
                }
            }

            return this;
        } catch (Exception var7) {
            String name = var7.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndClickDropDownElementByValue");
                Log.error("There was a problem clicking on the element " + option + "from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var7.getMessage().toString());
            throw var7;
        }
    }

    public WebDropDownImpl clickDropDownElementByValue(String option) throws Exception {
        try {
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            Iterator var6 = options.iterator();

            while (var6.hasNext()) {
                WebElement optionElement = (WebElement) var6.next();
                if (option.equals(optionElement.getAttribute("value"))) {
                    optionElement.click();
                    break;
                }
            }

            return this;
        } catch (Exception var5) {
            String name = var5.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndClickDropDownElementByValue");
                Log.error("There was a problem clicking on the element " + option + "from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var5.getMessage().toString());
            throw var5;
        }
    }

    public int waitAndCountkDropDownElements(int seconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            return options.size();
        } catch (Exception var4) {
            String name = var4.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndClickDropDownElementByValue");
                Log.error("There was a problem finding the element from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var4.getMessage().toString());
            throw var4;
        }
    }

    public List<WebElement> waitAndGetElementFromDropdown(int seconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            List<WebElement> options = this.WebElement.findElements(By.tagName("option"));
            return options;
        } catch (Exception var4) {
            String name = var4.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndGetElementFromDropdown");
                Log.error("There was a problem finding the element from the drop down " + name + ".It may not be available");
            }

            Log.error("Exception:" + var4.getMessage().toString());
            throw var4;
        }
    }

    public String waitAndGetRandomDifferentValueDropDown(int seconds) throws Exception {
        int numAux = 0;

        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            Select select = new Select(this.WebElement);
            String selectedOption = select.getFirstSelectedOption().getText();
            List<WebElement> dropDownValues = this.WebElement.findElements(By.tagName("option"));
            int tableSize = dropDownValues.size();
            Object[] optionsArray = this.generateValuesList(dropDownValues, tableSize, new String[tableSize]);
            optionsArray = ArrayUtils.removeElement(optionsArray, selectedOption);
            numAux = optionsArray.length;
            Random random = new Random();
            int randomNum = random.nextInt(numAux - 0) + 0;

            for (int attemps = 0; (optionsArray[randomNum].toString().contains("Select") || optionsArray[randomNum].toString().equals("")) && attemps < 100; ++attemps) {
                randomNum = random.nextInt(numAux - 0) + 0;
            }

            Iterator var12 = dropDownValues.iterator();

            while (var12.hasNext()) {
                WebElement dropDownValue = (WebElement) var12.next();
                if (optionsArray[randomNum].equals(dropDownValue.getText())) {
                    dropDownValue.click();
                    break;
                }
            }

            return optionsArray[randomNum].toString();
        } catch (Exception var14) {
            String name = var14.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndGetRandomValueDropDown");
                Log.error("There was a problem finding the random element from the drop down " + name);
            }

            Log.error("Exception:" + var14.getMessage().toString());
            throw var14;
        }
    }

    public String waitAndGetRandomValueDropDown(int seconds) throws Exception {
        int numAux = 0;

        try {
            WebDriverWait wait = new WebDriverWait(this.driver, (long) seconds);
            wait.until(ExpectedConditions.elementToBeClickable(this.WebElement));
            List<WebElement> dropDownValues = this.WebElement.findElements(By.cssSelector("option:not([style]):not([data-placeholder])"));
            int tableSize = dropDownValues.size();
            String[] optionsArray = new String[tableSize];
            optionsArray = this.generateValuesList(dropDownValues, tableSize, optionsArray);
            numAux = optionsArray.length;
            Random random = new Random();

            int randomNum;
            for (randomNum = random.nextInt(numAux - 0) + 0; optionsArray[randomNum].contains("Select") || optionsArray[randomNum].equals(""); randomNum = random.nextInt(numAux - 0) + 0) {
            }

            Iterator var9 = dropDownValues.iterator();

            while (var9.hasNext()) {
                WebElement dropDownValue = (WebElement) var9.next();
                if (optionsArray[randomNum].equals(dropDownValue.getText())) {
                    dropDownValue.click();
                    break;
                }
            }

            return optionsArray[randomNum];
        } catch (Exception var11) {
            String name = var11.getMessage().split("\n")[0];
            if (name.contains("no such element")) {
                Log.error("Method: waitAndGetRandomValueDropDown");
                Log.error("There was a problem finding the random element from the drop down " + name);
            }

            Log.error("Exception:" + var11.getMessage().toString());
            throw var11;
        }
    }

    private String[] generateValuesList(List<WebElement> dropDownValues, int tableSize, String[] optionsArray) {
        for (int i = 0; i <= tableSize - 1; ++i) {
            try {
                optionsArray[i] = ((WebElement) dropDownValues.get(i)).getText();
            } catch (Exception var6) {
            }
        }

        return optionsArray;
    }
}
