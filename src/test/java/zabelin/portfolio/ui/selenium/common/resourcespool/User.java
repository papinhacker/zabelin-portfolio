package zabelin.portfolio.ui.selenium.common.resourcespool;

public class User implements Blockable {

    //    private LoginPageData loginPageData;
//    private String jwt;
//    private List<BasicHeader> headers;
//
//    public User(String userName, String password) {
//        this.loginPageData = new LoginPageData(userName, password);
//    }
//
//    public User(String userName, String password, String email) {
//        this.loginPageData = new LoginPageData(userName, password, email);
//    }
//
//    public User(LoginPageData loginPageData) {
//        this.loginPageData = loginPageData;
//    }
//
//    public String jwt() {
//        if (jwt == null) {
//            this.jwt = getJWT(loginPageData.getUserName(), loginPageData.getPassword());
//        }
//
//        return jwt;
//    }
//
//    public void setJwt(String jwt) {
//        this.jwt = jwt;
//    }
//
//    public List<BasicHeader> headers() {
//        if (headers == null)
//            this.headers = HeadersHelper.prepareHeaders(jwt());
//
//        return headers;
//    }
//
//    public void setHeaders(List<BasicHeader> headers) {
//        this.headers = headers;
//    }
//
//    public LoginPageData getLoginPageData() {
//        return loginPageData;
//    }
//
//    public void setLoginPageData(LoginPageData loginPageData) {
//        this.loginPageData = loginPageData;
//    }
//
    @Override
    public String getIdentity() {
        return "placeholder"; //loginPageData.getUserName();
    }
}
