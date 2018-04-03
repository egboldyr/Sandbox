package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.Client;
import web.jaxws.ClientService;
import web.jaxws.ClientWebService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 02.04.18.
 */

@Controller
public class ClientsController {

    private static final String URL_NEW_CLIENT = "/new_client";
    private static final String URL_UPDATE_CLIENT = "/update_client";
    private static final String URL_PART_CLIENT = "/part_clients";

    private Integer from;

    private ClientService service;
    private ClientWebService clientWS;

    @PostConstruct
    private void initialize() {
        from = 0;
        service = new ClientService();
        clientWS = service.getClientPort();
    }

    @RequestMapping(value = URL_NEW_CLIENT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newClient(@RequestParam("name") String name, @RequestParam("surname") String surname,
                          @RequestParam("phone") String phone, @RequestParam("email") String email) {
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setPhone(phone);
        client.setEmail(email);
        clientWS.create(client);
    }

    @RequestMapping(value = URL_UPDATE_CLIENT, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@RequestParam("id") Long id,
                             @RequestParam("name") String name, @RequestParam("surname") String surname,
                             @RequestParam("phone") String phone, @RequestParam("email") String email) {
        Client client = new Client();
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
            from -= 10;
        } else if (action.equals("NEXT")) {
            from += 10;
        } else if (action.equals("UPLOAD")) {
            from = 0;
        }

        List<Client> clients = clientWS.getClients(from).getItem();
        JSONArray body = new JSONArray();
        for (Client c : clients) {
            JSONObject item = new JSONObject();
            item.put("id", c.getId());
            item.put("name", c.getName());
            item.put("surname", c.getSurname());
            item.put("phone", c.getPhone());
            item.put("email", c.getEmail());
            body.add(item);
        }
        return body.toJSONString();
    }
}
