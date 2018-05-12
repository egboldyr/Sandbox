package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 02.04.18.
 */

@Controller
public class ClientsController {

    private static final String URL_NEW_CLIENT         = "/new_client";
    private static final String URL_NEW_ACCOUNT        = "/new_account";
    private static final String URL_UPDATE_CLIENT      = "/update_client";
    private static final String URL_PART_CLIENT        = "/part_clients";
    private static final String URL_WRITE_DOWN_CLIENT  = "/write_down";
    private static final String URL_WRITE_DOWN_COURSES = "/write_down_courses";

    private Integer from;

    private ClientWebService clientWS;
    private AccountWebService accountWS;

    @PostConstruct
    private void initialize() {
        from = 0;
        ClientService clientService = new ClientService();
        clientWS = clientService.getClientPort();

        AccountService accountService = new AccountService();
        accountWS = accountService.getAccountPort();
    }

    @RequestMapping(value = URL_NEW_CLIENT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newClient(@RequestParam("name") String name, @RequestParam("surname") String surname,
                          @RequestParam("phone") String phone, @RequestParam("email") String email) {
        ClientDTO client = new ClientDTO();
        client.setName(name);
        client.setSurname(surname);
        client.setPhone(phone);
        client.setEmail(email);
        clientWS.create(client);
    }

    @RequestMapping(value = URL_NEW_ACCOUNT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newAccount(@RequestParam("id") Long id,
                           @RequestParam("login") String login, @RequestParam("password") String password) {
        accountWS.create(id, login, password);
    }

    @RequestMapping(value = URL_UPDATE_CLIENT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@RequestParam("id") Long id,
                             @RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("phone") String phone, @RequestParam("email") String email) {
        ClientDTO client = new ClientDTO();
        client.setId(id);
        client.setName(name);
        client.setSurname(surname);
        client.setPhone(phone);
        client.setEmail(email);
        clientWS.update(client);
    }

    @RequestMapping(value = URL_PART_CLIENT, method = RequestMethod.POST)
    public @ResponseBody String findPartOfClients(@RequestParam("action") String action) {
        if (action.equals("PREV") & from > 0) {
            from--;
        } else if (action.equals("NEXT")) {
            from++;
        } else if (action.equals("UPLOAD")) {
            from = 0;
        }

        List<ClientDTO> clients = clientWS.getClients(from).getItem();
        JSONArray body = new JSONArray();
        for (ClientDTO c : clients) {
            JSONObject item = new JSONObject();
            item.put("id", c.getId());
            item.put("name", c.getName());
            item.put("surname", c.getSurname());
            item.put("phone", c.getPhone());
            item.put("email", c.getEmail());
            item.put("login", c.getAccount() == null ? "(empty)" : c.getAccount());
            body.add(item);
        }
        return body.toJSONString();
    }

    @RequestMapping(value = URL_WRITE_DOWN_CLIENT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void writeDown(@RequestParam("clientId") Long clientId, @RequestParam("courseId") Long courseId ) {
        if (clientId != null && courseId != null) {
            clientWS.writeDownClientOnCourse(courseId, clientId);
        }
    }

    @RequestMapping(value = URL_WRITE_DOWN_COURSES, method = RequestMethod.POST)
    public @ResponseBody String writeDownCourses(@RequestParam("clientId") Long clientId) {
        if (clientId != null) {
            List<CourseDTO> courses = clientWS.getWriteDownCoursesByClientId(clientId).getItem();
            JSONArray body = new JSONArray();
            for (CourseDTO course : courses) {
                JSONObject item = new JSONObject();
                item.put("id", course.getId());
                item.put("title", course.getTitle());
                body.add(item);
            }
            return body.toJSONString();
        } else {
            return null;
        }
    }
}
