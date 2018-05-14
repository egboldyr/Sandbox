package web.service;

import web.jaxws.ClientDTO;
import web.jaxws.CourseDTO;

import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */


public interface ClientCache {

    void newClient(ClientDTO clientDTO);

    void newAccount(Long id, String login, String password);

    void updateClient(ClientDTO clientDTO);

    List<ClientDTO> getClientsPage(String action);

    void writeDownClientOnCourse(Long clientId, Long courseId);

    List<CourseDTO> getClientCourses(Long clientId);
}
