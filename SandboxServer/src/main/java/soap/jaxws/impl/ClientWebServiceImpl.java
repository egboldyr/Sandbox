package soap.jaxws.impl;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.dto.ClientDTO;
import soap.entity.Client;
import soap.entity.Course;
import soap.jaxws.ClientWebService;
import soap.service.ClientService;
import soap.service.CourseService;

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

    @Autowired
    private CourseService courseService;

    @Autowired
    private DozerBeanMapper mapper;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(ClientWebServiceImpl.class);
        log.info("Initialize - Client service...");
    }

    @Override
    public boolean create(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        client.setPhone(clientDTO.getPhone());
        client.setEmail(clientDTO.getEmail());

        log.info("Start create account service [NEW CLIENT( NAME = " + client.getName() + ", " + client.getSurname() + " )]");
        clientService.create(client);
        if (client.getId() == null) {
            return false;
        }
        log.info("Create account [NEW CLIENT( NAME = " + client.getName() + ", " + client.getSurname() + " )]");
        return true;
    }

    @Override
    public boolean update(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        client.setPhone(clientDTO.getPhone());
        client.setEmail(clientDTO.getEmail());

        log.info("Updating client information [CLIENT_ID: " + client.getId() + "]");
        clientService.update(client);
        log.info("Updating client, COMPLETE.");
        return true;
    }

    @Override
    public boolean delete(ClientDTO clientDTO) {
        return false;
    }

    @Override
    public ClientDTO[] getClients(Integer from) {
        log.info("Receiving clients... START");
        Client[] clients = clientService.findClientsPart(from, 10);
        log.info("Receiving clients... COMPLETE");
        ClientDTO[] body = new ClientDTO[clients.length];
        for (int i = 0; i < clients.length; i++) {
            body[i] = mapper.map(clients[i], ClientDTO.class);
        }
        return body;
    }

    @Override
    public boolean writeDownClientOnCourse(Long courseId, Long clientId) {
        log.info("Checking client... [ ID : " + clientId + "]");
        Client client = clientService.read(clientId);
        log.info("Checking client COMPLETE.");
        log.info("Checking course... [ ID : " + courseId + "]");
        Course course = courseService.read(courseId);
        log.info("Checking course COMPLETE.");
        if (client.getCourses().contains(course)) {
            log.warn("Client already recorded...");
            return false;
        }
        log.info("Write down client [ ID: " + clientId + "] on course [ ID: " + courseId + "] ...");
        client.getCourses().add(course);
        clientService.update(client);
        log.info("Write down SUCCESS!");
        return true;
    }
}
