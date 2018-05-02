package soap.util;

import soap.entity.Account;
import soap.entity.Client;

/**
 * Created by EGBoldyr on 02.05.18.
 */

public class AccountUtil {

    public static Account createSingleAccountWithoutClient() {
        Account account = new Account();
        account.setLogin("IIvanov");
        account.setPassword("qwert-11");
        return account;
    }

    public static Account createSingleAccountWithClient(Client client) {
        Account account = new Account();
        account.setLogin(client.getName().charAt(0) + client.getSurname());
        account.setPassword("qwert-11");
        account.setClient(client);
        return account;
    }
}
