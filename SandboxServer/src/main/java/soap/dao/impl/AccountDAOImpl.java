package soap.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soap.dao.AccountDAO;
import soap.entity.Account;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Created by EGBoldyr on 15.03.18.
 *
 * Data Access Object - для работы с учетными записями клиентов
 *
 */

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {

    private Logger log;

    @Autowired
    private SessionFactory factory;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(AccountDAOImpl.class);
    }

    @Override
    public Long create(Account account) {
        try {
            log.info("Creating account... [LOGIN : " + account.getLogin() + "]");
            Long accountId = (Long) factory.getCurrentSession().save(account);
            log.info("Creating account SUCCESS. New account [ID : " + accountId + "]");
            return accountId;
        } catch (HibernateException exc) {
            log.error("Creating account FAIL. [LOGIN : " + account.getLogin() + "]");
            return null;
        }
    }

    @Override
    public Account read(Long id) {
        return null;
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }

    @Override
    public Account getByLogin(String login) {
        try {
            log.info("Getting client account by [LOGIN : " + login + "]");
            return factory
                    .getCurrentSession()
                    .byNaturalId(Account.class)
                    .using("login", login)
                    .load();
        } catch (HibernateException e) {
            log.info("Login " + login +  " incorrect.");
            return null;
        }
    }
}
