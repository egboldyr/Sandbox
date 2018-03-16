package soap.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soap.dao.ClientDAO;
import soap.entity.Client;

import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 *
 * Data Access Object - для работы с записями клиентов
 *
 */

@Repository
public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Client client) {
        return null;
    }

    @Override
    public Client read(Long id) {
        return null;
    }

    @Override
    public boolean update(Client client) {
        return false;
    }

    @Override
    public boolean delete(Client client) {
        return false;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }
}
