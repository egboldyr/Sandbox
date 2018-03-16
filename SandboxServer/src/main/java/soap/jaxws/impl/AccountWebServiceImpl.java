package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.entity.Account;
import soap.entity.Client;
import soap.jaxws.AccountWebService;
import soap.service.AccountService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@Component
@WebService(serviceName = "AccountService", portName = "AccountPort", endpointInterface = "soap.jaxws.AccountWebService")
public class AccountWebServiceImpl implements AccountWebService {

    private Logger log;

    @Autowired
    private AccountService accountService;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(AuthorizationWebServiceImpl.class);
        log.info("Initialize - Account service...");
    }

    @Override
    public boolean create(Client client, Account account) {
        log.info("Start create account service [NEW ACCOUNT( LOGIN = " + account.getLogin() + " )]");
        accountService.create(account);
        if (account.getId() == null) {
            return false;
        }
        log.info("Create account [NEW ACCOUNT( LOGIN = " + account.getLogin() + " )] - COMPLETE");
        client.setAccount(account);
        return true;
    }

    @Override
    public boolean update(Account account) {
        return false;
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }
}
