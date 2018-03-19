package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import web.service.RequisitionClientService;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Controller
public class IndexController {

    private static final String URL_INDEX = "/index.html";
    private static final String URL_NEW_REQUISITION = "/new_requisition";

    @Autowired
    private RequisitionClientService service;

    @RequestMapping(value = URL_INDEX, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = URL_NEW_REQUISITION, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void setUrlNewRequisition(@RequestParam("name") String name,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("email") String email,
                                     @RequestParam("comment") String comment) {
        service.createRequisition(name, phone, email, comment);
    }
}
