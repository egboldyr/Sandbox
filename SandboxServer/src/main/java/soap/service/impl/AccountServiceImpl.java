package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import soap.dao.AccountRepository;
import soap.entity.Account;
import soap.service.AccountService;

import javax.annotation.PostConstruct;

/**
 * Created by EGBoldyr on 15.03.18.
 */

@Service
public class AccountServiceImpl implements AccountService {

    private Logger log;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(AccountService.class);
    }

    @Override
    @CacheEvict(value = "clientsCache", allEntries = true)
    public void create(Account account) {
        if (account == null) {
            log.error("Account can't be NULL.");
            return;
        }
        accountRepository.saveAndFlush(account);
    }

    @Override
    public Account read(Long id) {
        return null;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public Account getAccountByLogin(String login) {
        if (login == null) {
            log.error("Client [LOGIN = null]");
            return null;
        }
        return accountRepository.findByLogin(login);
    }
}
