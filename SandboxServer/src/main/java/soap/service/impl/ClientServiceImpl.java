package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soap.dao.ClientRepository;
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
    private ClientRepository repository;

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
        repository.saveAndFlush(client);
    }

    @Override
    public Client read(Long id) {
        if (id != null) {
            return repository.findOne(id);
        }
        return null;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void update(Client client) {
        if (client != null) {
            log.info("Client updating... ");
            repository.saveAndFlush(client);
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
    public Client[] findClientsPart(Integer page, Integer count) {
        List<Client> list = repository.findAll(page, count);
        Client[] clients = new Client[list.size()];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = list.get(i);
        }
        return clients;
    }
}
