package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.CourseDTO;
import web.jaxws.CourseService;
import web.jaxws.CourseWebService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 06.04.18.
 */

@Controller
public class CoursesController {

    private static final String URL_NEW_COURSE           = "/new_course";
    private static final String URL_FIND_COURSE_BY_TITLE = "/find_by_title_course";
    private static final String URL_GET_COURSES          = "/get_courses";

    private CourseWebService courseWS;

    @PostConstruct
    private void initialize() {
        CourseService service = new CourseService();
        courseWS = service.getCoursePort();
    }

    @RequestMapping(value = URL_NEW_COURSE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newCourse(@RequestParam("title") String title) {
        courseWS.newCourse(title);
    }

    @RequestMapping(value = URL_FIND_COURSE_BY_TITLE, method = RequestMethod.POST)
    public @ResponseBody String findByTitle(@RequestParam("title") String title) {
        CourseDTO course = courseWS.findByTitle(title);
        JSONObject item = new JSONObject();
        item.put("id", course.getId());
        item.put("title", course.getTitle());
        return item.toJSONString();
    }

    @RequestMapping(value = URL_GET_COURSES, method = RequestMethod.GET)
    public @ResponseBody String getCourses() {
        List<CourseDTO> courses = courseWS.allCourses().getItem();
        JSONArray body = new JSONArray();
        for (CourseDTO course : courses) {
            JSONObject item = new JSONObject();
            item.put("id", course.getId());
            item.put("title", course.getTitle());
            body.add(item);
        }
        return body.toJSONString();
    }
}
