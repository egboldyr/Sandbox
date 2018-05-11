package rest.dao;

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
import rest.domain.User;
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
@ComponentScan(basePackages = {"rest.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {

    @Configuration
    @Import(DbConfig.class)
    static class Config {}

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testCreateNewAppUserPositive() {
        userDAO.deleteAll(); userDAO.flush();

        User user = userDAO.saveAndFlush(UserTestUtil.createBasicAppUser());
        User response = userDAO.getOne(user.getUserId());

        Assert.assertEquals(user.getUserId(), response.getUserId());
        Assert.assertEquals(user.getLogin(), response.getLogin());
        Assert.assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    public void testFindAppUserByLoginPositive() {
        userDAO.deleteAll(); userDAO.flush();

        userDAO.save(UserTestUtil.createBasicAppUsersList());

        Assert.assertNotNull(userDAO.findByLogin("BasicUser1"));
        Assert.assertNotNull(userDAO.findByLogin("BasicUser2"));
        Assert.assertNotNull(userDAO.findByLogin("BasicUser3"));
    }

    @Test
    public void testUpdateAppUserPositive() {
        userDAO.deleteAll(); userDAO.flush();

        User user = userDAO.saveAndFlush(UserTestUtil.createBasicAppUser());
        user.setPassword("NewPassword");
        userDAO.saveAndFlush(user);

        User response = userDAO.getOne(user.getUserId());
        Assert.assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    public void testDeleteAppUserPositive() {
        userDAO.deleteAll(); userDAO.flush();

        User user = userDAO.saveAndFlush(UserTestUtil.createBasicAppUser());
        userDAO.delete(user);
        Assert.assertNull(userDAO.findOne(user.getUserId()));
    }
}
