package soap.service;

import soap.entity.Account;

/**
 * Created by EGBoldyr on 15.03.18.
 */

public interface AccountService {

    void create(Account account);

    Account read(Long id);

    void update(Account account);

    void delete(Account account);

    Account getAccountByLogin(String login);

}
