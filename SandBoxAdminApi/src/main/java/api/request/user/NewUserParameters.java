package api.request.user;

import javax.validation.constraints.NotNull;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public class NewUserParameters {

    @NotNull
    private String login;

    @NotNull
    private String password;

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
