//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.webdriverlogger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import zabelin.portfolio.core.common.Listener;
import zabelin.portfolio.core.common.Log;
import zabelin.portfolio.core.webdriverlogger.data.FindByEventRecord;
import zabelin.portfolio.core.webdriverlogger.data.XPathUsageData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebDriverLogger extends AbstractWebDriverEventListener {
    private static Map<String, List<FindByEventRecord>> webDriverFindByLog = new ConcurrentHashMap();

    public WebDriverLogger() {
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        if (Listener.getTestCaseContext() != null) {
            String sessionId = Listener.getTestCaseContext().getSessionId();
            if (!sessionId.equals("null")) {
                String locator = by.toString();
                String url = driver.getCurrentUrl();
                Object findByEventRecords;
                if (webDriverFindByLog.containsKey(sessionId)) {
                    findByEventRecords = (List) webDriverFindByLog.get(sessionId);
                } else {
                    findByEventRecords = new ArrayList();
                    webDriverFindByLog.put(sessionId, (List<FindByEventRecord>) findByEventRecords);
                }

                ((List) findByEventRecords).add(new FindByEventRecord(locator, url));
            }
        }
    }

    public static synchronized Map<String, XPathUsageData> generateWebDriverLog() {
        Map<String, XPathUsageData> webDriverLog = new HashMap();

        try {
            webDriverFindByLog.forEach((sessionId, findByEventRecords) -> {
                findByEventRecords.forEach((findByEventRecord) -> {
                    XPathUsageData xPathUsageData;
                    if (webDriverLog.containsKey(findByEventRecord.locator)) {
                        xPathUsageData = (XPathUsageData) webDriverLog.get(findByEventRecord.locator);
                    } else {
                        xPathUsageData = new XPathUsageData();
                        xPathUsageData.fullPath = findByEventRecord.locator.substring(findByEventRecord.locator.indexOf(" ") + 1);
                        xPathUsageData.pathType = findByEventRecord.locator.substring(3, findByEventRecord.locator.indexOf(":"));
                        webDriverLog.put(findByEventRecord.locator, xPathUsageData);
                    }

                    xPathUsageData.urls.add(findByEventRecord.url);
                    xPathUsageData.tests.add(Listener.allSessions.get(sessionId));
                });
            });
            webDriverLog.forEach((s, xPathUsageData) -> {
                xPathUsageData.count = xPathUsageData.tests.size();
            });
        } catch (Exception var2) {
            Log.warn("Unable to generate WebDriver log report");
            var2.printStackTrace();
        }

        return webDriverLog;
    }
}
