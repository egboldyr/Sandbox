package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import soap.entity.Account;
import soap.entity.Client;
import soap.jaxws.AuthorizationWebService;
import soap.service.AccountService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 15.03.18.
 */

@Component
@WebService(serviceName = "Authorization", portName = "AuthorizationPort", endpointInterface = "soap.jaxws.AuthorizationWebService")
public class AuthorizationWebServiceImpl implements AuthorizationWebService {

    private Logger log;

    @Autowired
    private AccountService accountService;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(AuthorizationWebServiceImpl.class);
        log.info("Initialize - Authorization service...");
    }

    @Override
    public Client authorization(String login, String password) {
        log.info("Start authorization service [authorization( LOGIN = " + login + " )]");
        Account account = accountService.getAccountByLogin(login);
        if (account != null) {
            log.info("Account received. Password check start...");
            if (password.equals(account.getPassword())) {
                log.info("Password checked, authorization COMPLETE.");
                return account.getClient();
            }
            log.error("Password incorrect.");
        }
        return null;
    }

}
