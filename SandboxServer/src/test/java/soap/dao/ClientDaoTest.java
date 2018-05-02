package soap.dao;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
import soap.entity.Client;
import soap.util.ClientUtil;

import java.util.List;

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
public class ClientDaoTest {

    @Configuration
    @Import(MySQLDBTestingConfig.class)
    static class Config {}

    @Autowired
    private ClientDAO clientDAO;

    @Test
    public void createClientPositive() {
        Client client = ClientUtil.createSingleClientWithoutAccount();

        Long clientId = clientDAO.create(client);
        Client result = clientDAO.read(clientId);

        Assert.assertEquals(clientId, result.getId());
        Assert.assertEquals(client.getName(), result.getName());
        Assert.assertEquals(client.getSurname(), result.getSurname());
        Assert.assertEquals(client.getPhone(), result.getPhone());
        Assert.assertEquals(client.getEmail(), result.getEmail());
    }

    @Test
    public void updateClientPositive() {
        Client client = ClientUtil.createSingleClientWithoutAccount();

        Long clientId = clientDAO.create(client);
        Client updateData = clientDAO.read(clientId);

        updateData.setName("Viktor");
        updateData.setSurname("Leontiev");

        clientDAO.update(updateData);

        Client result = clientDAO.read(clientId);

        Assert.assertEquals(updateData.getId(), result.getId());
        Assert.assertEquals(updateData.getName(), result.getName());
        Assert.assertEquals(updateData.getSurname(), result.getSurname());
        Assert.assertEquals(updateData.getPhone(), result.getPhone());
        Assert.assertEquals(updateData.getEmail(), result.getEmail());
    }

    @Test
    @Ignore /** Пока что не реализован метод удаления клиента */
    public void deleteClientPositive() {
        Client client = ClientUtil.createSingleClientWithoutAccount();
        Long clientId = clientDAO.create(client);

        Client deleteData = clientDAO.read(clientId);
        clientDAO.delete(deleteData);

        Assert.assertNull(clientDAO.read(deleteData.getId()));
    }

    @Test
    public void findPaginationClientsPositive() {
        List<Client> clients = ClientUtil.createClientsList();
        for (Client c : clients) {
            clientDAO.create(c);
        }

        List<Client> result = clientDAO.findClientsPart(0, 2);
        Assert.assertNotNull(result);
    }
}
