package zabelin.portfolio.core.webdriverlogger.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashSet;
import java.util.Set;

public class XPathUsageData {
    private static final Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    public String fullPath = "";
    public String pathType = "";
    public int count = 0;
    public Set<String> urls = new HashSet();
    public Set<CaseContext> tests = new HashSet();

    public XPathUsageData() {
    }

    public String toString() {
        return gson.toJson(this);
    }
}
