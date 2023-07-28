package zabelin.portfolio.api.jdi.common;

import com.epam.http.response.RestResponse;
import org.testng.Assert;
import zabelin.portfolio.api.jdi.models.Message;

public class JDIAssertedResponse {

    public final RestResponse response;

    public JDIAssertedResponse(RestResponse response) {
        this.response = response;
    }

    public <T> T as(String jsonPath, Class<T> clazz) {
        return response.getRaResponse().then().extract().body().jsonPath().getObject(jsonPath, clazz);
    }

    public JDIAssertedResponse hasMessage(String expected) {
        Message message = as("info", Message.class);
        Assert.assertEquals(message.getMessage(), expected);
        return this;
    }

    public JDIAssertedResponse hasStatusCode(int expected) {
        int statusCode = response.getStatus().code;
        Assert.assertEquals(statusCode, expected);
        return this;
    }
}
