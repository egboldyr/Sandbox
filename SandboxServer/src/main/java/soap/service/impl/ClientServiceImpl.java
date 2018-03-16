package soap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soap.dao.ClientDAO;
import soap.entity.Client;
import soap.service.ClientService;

import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO dao;

    @Override
    public void create(Client client) {

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
