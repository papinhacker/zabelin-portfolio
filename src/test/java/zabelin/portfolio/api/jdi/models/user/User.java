package zabelin.portfolio.api.jdi.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String login;
    private String pass;
    private Integer id;
    private List<String> games;

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public User(String login) {
        this.login = login;
    }
}
