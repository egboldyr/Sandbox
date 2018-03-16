package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.entity.Client;
import soap.jaxws.ClientWebService;
import soap.service.ClientService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@Component
@WebService(serviceName = "ClientService", portName = "ClientPort", endpointInterface = "soap.jaxws.ClientWebService")
public class ClientWebServiceImpl implements ClientWebService {

    private Logger log;

    @Autowired
    private ClientService clientService;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(ClientWebServiceImpl.class);
        log.info("Initialize - Client service...");
    }

    @Override
    public boolean create(Client client) {
        log.info("Start create account service [NEW CLIENT( NAME = " + client.getName() + ", " + client.getSurname() + " )]");
        clientService.create(client);
        if (client.getId() == null) {
            return false;
        }
        log.info("Create account [NEW CLIENT( NAME = " + client.getName() + ", " + client.getSurname() + " )]");
        return true;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(Client client) {
        return false;
    }
}
