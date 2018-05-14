package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.*;
import web.service.ClientCache;

import java.util.List;

/**
 * Created by EGBoldyr on 02.04.18.
 */

@Controller
public class ClientsController {

    private Integer page;

    private static final String URL_NEW_CLIENT         = "/new_client";
    private static final String URL_NEW_ACCOUNT        = "/new_account";
    private static final String URL_UPDATE_CLIENT      = "/update_client";
    private static final String URL_PART_CLIENT        = "/part_clients";
    private static final String URL_WRITE_DOWN_CLIENT  = "/write_down";
    private static final String URL_WRITE_DOWN_COURSES = "/write_down_courses";

    @Autowired
    private ClientCache clientCache;

    @RequestMapping(value = URL_NEW_CLIENT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newClient(@RequestParam("name") String name, @RequestParam("surname") String surname,
                          @RequestParam("phone") String phone, @RequestParam("email") String email) {
        ClientDTO client = new ClientDTO();
        client.setName(name);
        client.setSurname(surname);
        client.setPhone(phone);
        client.setEmail(email);
        clientCache.newClient(client);
    }

    @RequestMapping(value = URL_NEW_ACCOUNT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newAccount(@RequestParam("id") Long id,
                           @RequestParam("login") String login, @RequestParam("password") String password) {
        clientCache.newAccount(id, login, password);
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
        clientCache.updateClient(client);
    }

    @RequestMapping(value = URL_PART_CLIENT, method = RequestMethod.POST)
    public @ResponseBody String findPartOfClients(@RequestParam("action") String action) {
        if      (action.equals("PREV") && page > 0) page--;
        else if (action.equals("NEXT"))             page++;
        else if (action.equals("UPLOAD"))           page = 0;

        List<ClientDTO> clients = clientCache.getClientsPage(page);
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
        clientCache.writeDownClientOnCourse(clientId, courseId);
    }

    @RequestMapping(value = URL_WRITE_DOWN_COURSES, method = RequestMethod.POST)
    public @ResponseBody String writeDownCourses(@RequestParam("clientId") Long clientId) {
        if (clientId != null) {
            List<CourseDTO> courses = clientCache.getClientCourses(clientId);
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
