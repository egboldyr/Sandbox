package soap.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soap.dao.ClientDAO;
import soap.entity.Client;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by EGBoldyr on 15.03.18.
 *
 * Data Access Object - для работы с записями клиентов
 *
 */

@Repository
@Transactional
public class ClientDAOImpl implements ClientDAO {

    private Logger log;

    @Autowired
    private SessionFactory factory;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(ClientDAOImpl.class);
    }

    @Override
    public Long create(Client client) {
        try {
            log.info("Creating client... [NAME : " + client.getName() + ", SURNAME : " +client.getSurname() +"]");
            Long clientId = (Long) factory.getCurrentSession().save(client);
            log.info("Creating client SUCCESS. New account [ID : " + clientId + "]");
            return clientId;
        } catch (HibernateException exc) {
            log.error("Creating client FAIL. [NAME : " + client.getName() + ", SURNAME : " +client.getSurname() +"]");
            return null;
        }
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
    public List<Client> findClientsPart(Integer from, Integer count) {
        try {
            log.info("Receiving " + count + " clients...");
            List<Client> clients = factory.getCurrentSession()
                                                .createCriteria(Client.class)
                                                .setFirstResult(from)
                                                .setMaxResults(count)
                                                .list();
            log.info("Receiving " + count + " clients, COMPLETE.");
            return clients;
        } catch (HibernateException exc) {
            log.error("Receiving clients, FAIL.");
            return null;
        }
    }
}
