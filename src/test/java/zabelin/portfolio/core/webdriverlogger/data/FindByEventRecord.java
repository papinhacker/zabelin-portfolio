package zabelin.portfolio.core.webdriverlogger.data;

public class FindByEventRecord {
    public String locator;
    public String url;

    public FindByEventRecord(String locator, String url) {
        this.locator = locator;
        this.url = url;
    }
}
