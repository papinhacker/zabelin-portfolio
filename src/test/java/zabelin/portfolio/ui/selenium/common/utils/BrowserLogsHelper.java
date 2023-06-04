package zabelin.portfolio.ui.selenium.common.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class BrowserLogsHelper {

    /**
     * Gets all the browser logs by the specified log type.
     * All the log types are placed in LogType class as a static members.
     *
     * @param driver
     * @param logType (can be taken from LogType)
     * @return List<LogEntry>
     * @throws Exception
     */
    public static List<LogEntry> getLogs(WebDriver driver, String logType) {
        return driver.manage().logs().get(logType).getAll();
    }

    /**
     * Gets all the browser logs by the specified log type.
     * All the log types are placed in LogType class as a static members.
     * All the log levels are placed in Level class as a static members.
     *
     * @param driver
     * @param logType (can be taken from LogType)
     * @param level   of logging (could be not only browser, but client as well)
     * @return List<LogEntry>
     * @throws Exception
     */
    public static List<LogEntry> getLogs(WebDriver driver, String logType, Level level) {
        List<LogEntry> logEntries = new ArrayList<>();
        driver.manage().logs().get(logType).getAll().stream().forEach(logEntry -> {
            if (logEntry.getLevel().equals(level)) logEntries.add(logEntry);
        });
        return logEntries;
    }

    /**
     * Gets all the browser logs by the specified log type and after the specified time moment.
     * All the log types are placed in LogType class as a static members.
     * All the log levels are placed in Level class as a static members.
     *
     * @param driver
     * @param logType        (can be taken from LogType)
     * @param level          of logging (could be not only browser, but client as well)
     * @param timestampAfter (all the entries will be after that specified time)
     * @return List<LogEntry>
     * @throws Exception
     */
    public static List<LogEntry> getLogsAfter(WebDriver driver, String logType, Level level, long timestampAfter) {
        List<LogEntry> logEntries = new ArrayList<>();
        driver.manage().logs().get(logType).getAll().stream()
                .filter(logEntry -> logEntry.getTimestamp() >= timestampAfter)
                .forEach(logEntry -> {
                    if (logEntry.getLevel().equals(level)) logEntries.add(logEntry);
                });

        return logEntries;
    }

}
