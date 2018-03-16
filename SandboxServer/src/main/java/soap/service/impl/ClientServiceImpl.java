package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ClientServiceImpl implements ClientService {

    private Logger log;

    @Autowired
    private ClientDAO dao;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(ClientServiceImpl.class);
    }

    @Override
    public void create(Client client) {
        if (client == null) {
            log.error("Client can't be NULL.");
            return;
        }
        client.setId(dao.create(client));
    }

    @Override
    public Client read(Long id) {
        return null;
    }

    @Override
    public void update(Client client) {

    }

    @Override
    public void delete(Client client) {

    }

    @Override
    public List<Client> allClients() {
        return null;
    }
}