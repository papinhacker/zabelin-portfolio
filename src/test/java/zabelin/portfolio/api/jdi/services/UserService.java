package zabelin.portfolio.api.jdi.services;

import com.epam.http.annotations.*;
import com.epam.http.requests.RestMethod;
import zabelin.portfolio.api.jdi.common.JDIAssertedResponse;
import zabelin.portfolio.api.jdi.models.user.User;
import zabelin.portfolio.ui.selenium.common.env.EnvConstants;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain(EnvConstants.BASE_PAGE_API)
public class UserService {

    @POST("/signup")
    @ContentType(JSON)
    private RestMethod registerUser;

    @GET("/user")
    @ContentType(JSON)
    private RestMethod getUser;

    @PUT("/user")
    @ContentType(JSON)
    private RestMethod updatePasswordUser;

    @DELETE("/user")
    @ContentType(JSON)
    private RestMethod deleteUser;

    public JDIAssertedResponse registerNewUser(User user) {
        return new JDIAssertedResponse(registerUser.body(user).call());
    }
}
