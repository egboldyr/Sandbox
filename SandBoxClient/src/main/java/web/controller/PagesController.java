package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by EGBoldyr on 02.04.18.
 */

@Controller
public class PagesController {

    private static final String URL_INDEX = "/index.html";
    private static final String URL_DASHBOARD = "/dashboard.html";

    @RequestMapping(value = URL_INDEX, method = RequestMethod.GET)
    public String index() {
        return "index/index";
    }

    @RequestMapping(value = URL_DASHBOARD, method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard/dashboard";
    }

}
