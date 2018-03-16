package soap.dao;

import soap.entity.Account;

/**
 * Created by EGBoldyr on 15.03.18.
 */

public interface AccountDAO {

    Long create(Account account);

    Account read(Long id);

    boolean update(Account account);

    boolean delete(Account account);

    Account getByLogin(String login);
}
