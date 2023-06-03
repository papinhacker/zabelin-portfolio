//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.severotek.core.ui;

import com.severotek.core.common.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Quotes;

public class WebTableImpl extends WebElementImpl {
    private String XPATH_SPAN = ".//span";
    private String XPATH_I = ".//i";
    private String XPATH_INPUT = ".//input";
    private String XPATH_TH = ".//th";
    private String XPATH_SPECIFIC_TEXT_TH = ".//th[normalize-space(.)=%s]";
    private String XPATH_SPECIFIC_LINK_TEXT_TD = ".//td//a[normalize-space(.)=%s]";
    private String XPATH_SPECIFIC_TEXT_TD = ".//td[normalize-space(.)=%s]";
    private String XPATH_SPECIFIC_TEXT_CONTAINING_TD = ".//td[contains(normalize-space(.), %s)]";
    private String XPATH_SPECIFIC_INDEX_TD = ".//td[%s]";
    private String XPATH_SPECIFIC_TEXT_TBODY_TR = ".//tr[not(ancestor::thead) and contains(.,%s)]";
    private String XPATH_SPECIFIC_TEXT_AND_COLUMN_INDEX_TBODY_TR = ".//tr[not(ancestor::thead) and td[%s and .=%s]]";
    private String XPATH_SPECIFIC_TEXT_FIRST_TD_ANY_TD_SPAN_IN_THIS_ROW = ".//tr[td[1]/descendant-or-self::*[normalize-space(.)=%s]]//td//span";
    private String XPATH_SPECIFIC_TEXT_FIRST_TD_SPECIFIC_COLUMN_SPAN = ".//tr[td[1]/descendant-or-self::*[normalize-space(.)=%s]]//td[%s]//span";
    private String XPATH_SPECIFIC_TEXT_FIRST_TD_ANY_TD_I_IN_THIS_ROW = ".//tr[td[1]/descendant-or-self::*[normalize-space(.)=%s]]//td//i";
    private String XPATH_SPECIFIC_TEXT_FIRST_TD_SPECIFIC_COLUMN_I = ".//tr[td[1]/descendant-or-self::*[normalize-space(.)=%s]]//td[%s]//i";
    private String XPATH_FIND_ROW_BY_CELL_TEXT = ".//tr[td[1]/descendant-or-self::*[normalize-space(.)=%s]]";
    private String CSS_TBODY_SPECIFIC_ROW_TD = "tbody>tr:nth-child(%s)>td";
    private String XPATH_TBODY_SPECIFIC_ROW_TD_SPECIFIC_TEXT_LINK = ".//tr[%s]/td//a[.=%s]";

    public WebTableImpl(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public int getRowCount() throws Exception {
        try {
            return this.WebElement.findElements(By.tagName("tr")).size();
        } catch (NoSuchElementException var2) {
            Log.error(var2.getMessage());
            throw var2;
        }
    }

    public int getColumnCount() throws Exception {
        try {
            List<WebElement> tableRows = this.WebElement.findElements(By.tagName("tr"));
            WebElement headerRow = (WebElement)tableRows.get(0);
            List<WebElement> tableCols = headerRow.findElements(By.tagName("th"));
            return tableCols.size();
        } catch (NoSuchElementException var4) {
            Log.error(var4.toString());
            throw var4;
        }
    }

    public WebElement getCellWebElement(int rowIdx, int colIdx) throws Exception {
        try {
            List<WebElement> tableRows = this.WebElement.findElements(By.tagName("tr"));
            List<WebElement> tableCols = ((WebElement)tableRows.get(rowIdx)).findElements(By.tagName("td"));
            return (WebElement)tableCols.get(colIdx);
        } catch (NoSuchElementException var5) {
            Log.error(var5.toString());
            throw var5;
        }
    }

    public String getCellData(int rowIdx, int colIdx) throws Exception {
        try {
            return this.getCellWebElement(rowIdx, colIdx).getText();
        } catch (NoSuchElementException var4) {
            Log.error(var4.toString());
            throw var4;
        }
    }

    public String waitAndGetCellData(int rowIdx, int colIdx, int seconds) throws Exception {
        try {
            this.waitForVisibility(seconds);
            return this.getCellData(rowIdx, colIdx);
        } catch (NoSuchElementException var5) {
            Log.error(var5.toString());
            throw var5;
        }
    }

    public ArrayList<String> getAllColumnData(int columnIndex) throws Exception {
        try {
            ArrayList<String> element = new ArrayList();
            List<WebElement> columnData = this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_INDEX_TD, columnIndex + 1)));
            Iterator var4 = columnData.iterator();

            while(var4.hasNext()) {
                WebElement e = (WebElement)var4.next();
                element.add(e.getText());
            }

            return element;
        } catch (Exception var6) {
            Log.error("There was a issue getting data ");
            Log.error(var6.getMessage());
            throw var6;
        }
    }

    public ArrayList<String> getAllRowData(int rowIndex) throws Exception {
        try {
            List<WebElement> rowData = this.WebElement.findElements(By.cssSelector(String.format(this.CSS_TBODY_SPECIFIC_ROW_TD, rowIndex + 1)));
            return this.getRowContent(rowData);
        } catch (Exception var3) {
            Log.error("There was a issue getting Row data ");
            Log.error(var3.getMessage());
            throw var3;
        }
    }

    public ArrayList<String> getHeaderData() throws Exception {
        try {
            List<WebElement> rowData = this.WebElement.findElements(By.xpath(this.XPATH_TH));
            return this.getRowContent(rowData);
        } catch (Exception var2) {
            Log.error("There was a issue getting header data ");
            Log.error(var2.getMessage());
            throw var2;
        }
    }

    public WebElement clickElementInHeaderByText(String headerText) throws Exception {
        try {
            List<WebElement> header = this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_TEXT_TH, Quotes.escape(headerText))));
            if (header.size() > 0) {
                ((WebElement)header.get(0)).click();
                return (WebElement)header.get(0);
            } else {
                return null;
            }
        } catch (Exception var3) {
            Log.error("There was a problem clicking an element on the table");
            Log.error(var3.getMessage());
            throw var3;
        }
    }

    public WebElement clickElementInHeaderByTextCaseInsensitive(String headerText) throws Exception {
        try {
            List<WebElement> header = this.WebElement.findElements(By.tagName("th"));
            WebElement we = null;

            for(int i = 0; i < header.size(); ++i) {
                if (((WebElement)header.get(i)).getText().equalsIgnoreCase(headerText)) {
                    we = (WebElement)header.get(i);
                    we.click();
                    break;
                }
            }

            return we;
        } catch (Exception var5) {
            Log.error("There was a problem clicking an element on the table");
            Log.error(var5.getMessage());
            throw var5;
        }
    }

    public String getSortModeForTableColumnByName(String columnName) throws Exception {
        try {
            WebElement headerWe = this.WebElement.findElement(By.xpath(String.format(this.XPATH_SPECIFIC_TEXT_TH, Quotes.escape(columnName))));
            return headerWe.getAttribute("class");
        } catch (Exception var3) {
            Log.error("There was a problem getting sort mode for a column of the table");
            Log.error(var3.getMessage());
            throw var3;
        }
    }

    public WebElement clickElementInRowByLinkText(String cellLinkText) throws Exception {
        try {
            List<WebElement> cells = this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_LINK_TEXT_TD, Quotes.escape(cellLinkText))));
            WebElement we = null;
            if (cells.size() > 0) {
                we = (WebElement)cells.get(0);
                ((WebElement)cells.get(0)).click();
            }

            return we;
        } catch (Exception var4) {
            Log.error("There was a problem clicking a link in the table");
            Log.error(var4.getMessage());
            throw var4;
        }
    }

    public WebElement clickElementInRowByLinkText(int rowIndex, String linkText) throws Exception {
        try {
            List<WebElement> rowCells = this.WebElement.findElements(By.xpath(String.format(this.XPATH_TBODY_SPECIFIC_ROW_TD_SPECIFIC_TEXT_LINK, rowIndex + 1, Quotes.escape(linkText))));
            WebElement we = null;
            if (rowCells.size() > 0) {
                we = (WebElement)rowCells.get(0);
                ((WebElement)rowCells.get(0)).click();
            }

            return we;
        } catch (Exception var5) {
            Log.error("There was a problem clicking a link in the table");
            Log.error(var5.getMessage());
            throw var5;
        }
    }

    public WebElement clickElementInTableByLinkTextCaseInsensitive(String cellLinkText) throws Exception {
        try {
            List<WebElement> cells = this.WebElement.findElements(By.cssSelector("td>a"));
            WebElement we = null;

            for(int i = 0; i < cells.size(); ++i) {
                if (((WebElement)cells.get(i)).getText().equalsIgnoreCase(cellLinkText)) {
                    we = (WebElement)cells.get(i);
                    we.click();
                    break;
                }
            }

            return we;
        } catch (Exception var5) {
            Log.error("There was a problem clicking a link in the table");
            Log.error(var5.getMessage());
            throw var5;
        }
    }

    public WebTableImpl clickTbodyRowsByText(String text) throws Exception {
        return this.clickTbodyRowsByText(text, -1);
    }

    public WebTableImpl clickTbodyRowsByText(String text, int column) throws Exception {
        String xpathExpression = column < 0 ? String.format(this.XPATH_SPECIFIC_TEXT_TBODY_TR, Quotes.escape(text)) : String.format(this.XPATH_SPECIFIC_TEXT_AND_COLUMN_INDEX_TBODY_TR, column + 1, Quotes.escape(text));
        List<WebElement> elements = this.WebElement.findElements(By.xpath(xpathExpression));
        Iterator var5 = elements.iterator();

        while(var5.hasNext()) {
            WebElement elem = (WebElement)var5.next();
            elem.click();
        }

        return this;
    }

    public WebElement clickElementInTableByText(String cellText) throws Exception {
        try {
            List<WebElement> cells = this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_TEXT_TD, Quotes.escape(cellText))));
            if (cells.size() > 0) {
                ((WebElement)cells.get(0)).click();
                return (WebElement)cells.get(0);
            } else {
                return null;
            }
        } catch (Exception var3) {
            Log.error(var3.getMessage());
            throw var3;
        }
    }

    public WebElement waitAndClickElementInTableByText(String cellText, int seconds) throws Exception {
        try {
            this.waitForVisibility(seconds);
            return this.clickElementInTableByText(cellText);
        } catch (Exception var4) {
            Log.error(var4.getMessage());
            throw var4;
        }
    }

    public WebElement waitAndClickElementInTableByTextCaseInsensitive(String cellText, int seconds) throws Exception {
        try {
            this.waitForVisibility(seconds);
            List<WebElement> cells = this.WebElement.findElements(By.tagName("td"));
            WebElement we = null;

            for(int i = 0; i < cells.size(); ++i) {
                if (((WebElement)cells.get(i)).getText().equalsIgnoreCase(cellText)) {
                    we = (WebElement)cells.get(i);
                    we.click();
                    break;
                }
            }

            return we;
        } catch (Exception var6) {
            Log.error(var6.getMessage());
            throw var6;
        }
    }

    public ArrayList<String> getRowContent(List<WebElement> row) throws Exception {
        try {
            ArrayList<String> cellsContent = new ArrayList();
            Iterator var3 = row.iterator();

            while(var3.hasNext()) {
                WebElement cell = (WebElement)var3.next();
                cellsContent.add(cell.getText());
            }

            return cellsContent;
        } catch (Exception var5) {
            Log.error("There was a problem retrieving row content " + var5.getMessage());
            throw var5;
        }
    }

    public WebElement waitAndClickInTableByIndex(int rowIdx, int colIdx, int seconds) throws Exception {
        try {
            this.waitForVisibility(seconds);
            WebElement cell = this.getCellWebElement(rowIdx, colIdx);
            if (cell != null) {
                cell.click();
            }

            return cell;
        } catch (NoSuchElementException var5) {
            Log.error("There was a problem clicking row content " + var5.getMessage());
            throw var5;
        }
    }

    public WebElement waitClearAndFillTextBoxInTableByIndex(String text, int rowIdx, int colIdx, int seconds) throws Exception {
        try {
            this.waitForVisibility(seconds);
            WebElement cell = this.getCellWebElement(rowIdx, colIdx);
            cell = cell.findElement(By.xpath(this.XPATH_INPUT));
            cell.clear();
            cell.sendKeys(new CharSequence[]{text});
            return cell;
        } catch (Exception var6) {
            Log.error("Method: waitClearAndFillTextBoxInTableByIndex()");
            Log.error("Error: Fail at trying to insert: '" + text + "' at WebTableElement row: '" + rowIdx + "' and column: '" + colIdx + "'");
            Log.error("Exception: " + var6.getMessage());
            throw var6;
        }
    }

    public boolean isAnyTableCellContainsText(String text) throws Exception {
        try {
            List<WebElement> cells = this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_TEXT_CONTAINING_TD, Quotes.escape(text))));
            return cells.size() > 0;
        } catch (Exception var3) {
            Log.error("Method: isAnyTableCellContainsText()");
            Log.error("Exception: " + var3.getMessage());
            throw var3;
        }
    }

    public boolean isAnyTableCellContainsTextCaseInsensitive(String text) throws Exception {
        try {
            String textToFind = text.toLowerCase();
            List<WebElement> cells = this.WebElement.findElements(By.tagName("td"));
            Iterator var4 = cells.iterator();

            WebElement cell;
            do {
                if (!var4.hasNext()) {
                    return false;
                }

                cell = (WebElement)var4.next();
            } while(!cell.getText().toLowerCase().contains(textToFind));

            return true;
        } catch (Exception var6) {
            Log.error("Method: isAnyTableCellContainsText()");
            Log.error("Exception: " + var6.getMessage());
            throw var6;
        }
    }

    public WebElement getFirstWebElementCellContainingText(String text) throws Exception {
        try {
            List<WebElement> cells = this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_TEXT_CONTAINING_TD, Quotes.escape(text))));
            return (WebElement)cells.get(0);
        } catch (Exception var3) {
            Log.error("Method: getFirstWebElementCellContainingText()");
            Log.error("Exception: " + var3.getMessage());
            throw var3;
        }
    }

    public List<WebElement> getListWebElementCellsContainingText(String text) throws Exception {
        try {
            return this.WebElement.findElements(By.xpath(String.format(this.XPATH_SPECIFIC_TEXT_CONTAINING_TD, Quotes.escape(text))));
        } catch (Exception var3) {
            Log.error("Method: getListWebElementCellsContainingText()");
            Log.error("Exception: " + var3.getMessage());
            throw var3;
        }
    }
}
