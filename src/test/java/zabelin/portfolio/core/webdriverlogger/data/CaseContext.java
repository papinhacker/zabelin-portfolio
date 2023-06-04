//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package zabelin.portfolio.core.webdriverlogger.data;

public class CaseContext {
    private String sessionId;
    private String caseId;
    private String caseDescription;

    public CaseContext() {
        this.sessionId = "null";
        this.caseId = "null";
        this.caseDescription = "null";
    }

    public CaseContext(String sessionId, String caseId, String caseDescription) {
        this.sessionId = sessionId;
        this.caseId = caseId;
        this.caseDescription = caseDescription;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseDescription() {
        return this.caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }
}
