package soap.service.impl;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soap.dao.ClientDAO;
import soap.entity.Client;
import soap.service.ClientService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 */

@Service
@CacheConfig(cacheNames = {"clientsCache"})
public class ClientServiceImpl implements ClientService {

    private Logger log;

    @Autowired
    private ClientDAO dao;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(ClientServiceImpl.class);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void create(Client client) {
        if (client == null) {
            log.error("Client can't be NULL.");
            return;
        }
        client.setId(dao.create(client));
    }

    @Override
    public Client read(Long id) {
        if (id != null) {
            return dao.read(id);
        }
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(Client client) {
        if (client != null) {
            log.info("Client updating... ");
            dao.update(client);
            log.info("Client updating... SUCCESS.");
        } else {
            log.error("Client not be NULL. Updating FAIL.");
        }
    }

    @Override
    public void delete(Client client) {

    }

    @Override
    @Cacheable
    public Client[] findClientsPart(Integer from, Integer count) {
        List<Client> list = dao.findClientsPart(from, count);
        Client[] clients = new Client[list.size()];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = list.get(i);
        }
        return clients;
    }
}
