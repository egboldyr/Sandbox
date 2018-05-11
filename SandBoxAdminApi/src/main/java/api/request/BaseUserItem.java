package api.request;

import javax.validation.constraints.NotNull;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public class BaseUserItem implements BaseUser {

    @NotNull
    private String login;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }
}
