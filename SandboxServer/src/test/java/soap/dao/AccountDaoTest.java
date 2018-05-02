package soap.dao;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import soap.config.MySQLDBTestingConfig;
import soap.entity.Account;
import soap.entity.Client;
import soap.util.AccountUtil;
import soap.util.ClientUtil;

/**
 * Created by EGBoldyr on 02.05.18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@EntityScan("soap.entity")
@ComponentScan(basePackages = {"soap.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountDaoTest {

    @Configuration
    @Import(MySQLDBTestingConfig.class)
    static class Config {}

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void testCreateAccountWithoutClientPositive() {
        Account account = AccountUtil.createSingleAccountWithoutClient();
        accountDAO.create(account);

        Account result = accountDAO.getByLogin(account.getLogin());

        Assert.assertEquals(account.getLogin(), result.getLogin());
        Assert.assertEquals(account.getPassword(), result.getPassword());
        Assert.assertNull(result.getClient());
    }

    @Test
    public void testCreateAccountWithClientPositive() {
        Client client = ClientUtil.createSingleClientWithoutAccount();
        clientDAO.create(client);

        Account account = AccountUtil.createSingleAccountWithClient(client);
        accountDAO.create(account);

        Account result = accountDAO.getByLogin(account.getLogin());

        Assert.assertEquals(account.getLogin(), result.getLogin());
        Assert.assertEquals(account.getPassword(), result.getPassword());
        Assert.assertEquals(account.getClient(), client);
    }

    @Test
    public void testGetAccountByLoginPositive() {
        Account account = AccountUtil.createSingleAccountWithoutClient();
        Long id = accountDAO.create(account);

        Account result = accountDAO.getByLogin(account.getLogin());

        Assert.assertEquals(id, result.getId());
        Assert.assertEquals(account.getLogin(), result.getLogin());
        Assert.assertEquals(account.getPassword(), result.getPassword());
        Assert.assertNull(account.getClient());
    }
}
