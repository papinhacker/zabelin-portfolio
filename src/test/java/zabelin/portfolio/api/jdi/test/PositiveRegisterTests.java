package zabelin.portfolio.api.jdi.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import zabelin.portfolio.api.jdi.models.user.User;
import zabelin.portfolio.api.jdi.services.UserService;

import java.util.concurrent.ThreadLocalRandom;

import static com.epam.http.requests.ServiceInit.init;

public class PositiveRegisterTests {

    private UserService userService;
    private User randomUser;

    @BeforeClass
    public void initServices() {
        userService = init(UserService.class);
    }

    @BeforeMethod
    public void initRandomUser() {
        randomUser = new User("login" + ThreadLocalRandom.current().nextInt(10), "pass" + ThreadLocalRandom.current().nextInt(10));
    }

    @Test(description = "C1337 the worst api test")
    public void positiveRegisterTest() {
        User user = userService.registerNewUser(randomUser)
                .hasStatusCode(201)
                .hasMessage("User created")
                .as("register_data", User.class);

        Assert.assertNotNull(user.getPass(), "There is no password");
        Assert.assertNotEquals(randomUser.getPass(), user.getPass(), "Password wasn't changed");

    }
}
