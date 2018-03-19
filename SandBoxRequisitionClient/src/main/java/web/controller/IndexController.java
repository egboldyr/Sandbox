package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Controller
public class IndexController {

    private static final String URL_INDEX = "/index.html";

    @RequestMapping(value = URL_INDEX, method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
