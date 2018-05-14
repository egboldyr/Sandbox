package web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import web.jaxws.*;
import web.service.ClientCache;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

@Service
@CacheConfig(cacheNames = {"clientsFrontEndCache", "coursesFrontEndCache" })
public class ClientCacheImpl implements ClientCache {

    private Logger log = LoggerFactory.getLogger(ClientCacheImpl.class);

    private ClientWebService clientWS;
    private AccountWebService accountWS;

    @PostConstruct
    private void initialize() {
        log.info("Start - ClientsCache service...");

        ClientService clientService = new ClientService();
        clientWS = clientService.getClientPort();
        AccountService accountService = new AccountService();
        accountWS = accountService.getAccountPort();
    }

    @Override
    @CacheEvict(cacheNames = {"clientsFrontEndCache"}, allEntries = true)
    public void newClient(ClientDTO client) {
        clientWS.create(client);
    }

    @Override
    @CacheEvict(cacheNames = {"clientsFrontEndCache"}, allEntries = true)
    public void newAccount(Long id, String login, String password) {
        accountWS.create(id, login, password);
    }

    @Override
    @CacheEvict(cacheNames = {"clientsFrontEndCache"}, allEntries = true)
    public void updateClient(ClientDTO client) {
        clientWS.update(client);
    }

    @Override
    @Cacheable(cacheNames = {"clientsFrontEndCache"})
    public List<ClientDTO> getClientsPage(Integer page) {
        return clientWS.getClients(page).getItem();
    }

    @Override
    @CacheEvict(cacheNames = { "coursesFrontEndCache" }, allEntries = true)
    public void writeDownClientOnCourse(Long clientId, Long courseId) {
        if (clientId != null && courseId != null) {
            clientWS.writeDownClientOnCourse(courseId, clientId);
        }
    }

    @Override
    @Cacheable(cacheNames = { "coursesFrontEndCache" })
    public List<CourseDTO> getClientCourses(Long clientId) {
        return clientWS.getWriteDownCoursesByClientId(clientId).getItem();
    }
}
