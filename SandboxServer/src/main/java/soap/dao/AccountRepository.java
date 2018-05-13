package soap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import soap.entity.Account;

/**
 * Created by EGBoldyr on 12.05.18.
 */

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByLogin(String login);

}
