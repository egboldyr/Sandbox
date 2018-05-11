package rest.util;

import rest.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EGBoldyr on 11.05.18.
 */

public class UserTestUtil {

    public static User createBasicAppUser() {
        User user = new User();
        user.setLogin("BasicUser");
        user.setPassword("z1234567");
        return user;
    }

    public static List<User> createBasicAppUsersList() {
        List<User> users = new ArrayList<>();

        User first = new User();
        first.setLogin("BasicUser1");
        first.setPassword("z1234567");
        users.add(first);

        User second = new User();
        second.setLogin("BasicUser2");
        second.setPassword("z1234567");
        users.add(second);

        User third = new User();
        third.setLogin("BasicUser3");
        third.setPassword("z1234567");
        users.add(third);

        return users;
    }
}
