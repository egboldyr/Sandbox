package rest.service;

import api.dto.UserDTO;
import api.request.BaseUserItem;
import api.request.user.UserParameters;
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
import rest.config.DbConfig;
import rest.dao.UserDAO;
import rest.domain.User;
import rest.exeption.AdministrationDataException;
import rest.util.UserTestUtil;

/**
 * Created by EGBoldyr on 11.05.18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@EntityScan("rest.domain")
@ComponentScan(basePackages = {"rest.dao", "rest.service", "rest.util"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdministrationServiceTest {

    @Configuration
    @Import(DbConfig.class)
    static class Config {}

    @Autowired
    private AdministrationService service;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void createAppUserPositive() {
        UserParameters parameters = UserTestUtil.createBasicAppUserParameters();
        UserDTO response = service.createAppUser(parameters);

        User checkUser = userDAO.findOne(response.getId());

        Assert.assertEquals(parameters.getLogin(), checkUser.getLogin());
        Assert.assertEquals(parameters.getPassword(), checkUser.getPassword());
    }

    @Test(expected = AdministrationDataException.class)
    public void createAppUserDuplicatedLoginNegative() {
        service.createAppUser(UserTestUtil.createBasicAppUserWithCustomLoginParameters("CustomUser"));
        service.createAppUser(UserTestUtil.createBasicAppUserWithCustomLoginParameters("CustomUser"));
    }

    @Test
    public void deleteAppUserPositive() {
        UserDTO user = service.createAppUser(UserTestUtil.createBasicAppUserWithCustomLoginParameters("UserForDelete"));
        service.deleteAppUser(UserTestUtil.createBaseUserItemParameters(user.getLogin()));

        Assert.assertNull(userDAO.findByLogin(user.getLogin()));
    }

}
