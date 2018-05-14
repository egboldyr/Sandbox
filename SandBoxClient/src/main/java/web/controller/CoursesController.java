package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.CourseDTO;
import web.service.CourseCache;

import java.util.List;

/**
 * Created by EGBoldyr on 06.04.18.
 */

@Controller
public class CoursesController {

    private Integer page;

    private static final String URL_NEW_COURSE           = "/new_course";
    private static final String URL_FIND_COURSE_BY_TITLE = "/find_by_title_course";
    private static final String URL_GET_COURSES          = "/get_courses";
    private static final String URL_GET_COURSES_PART     = "/part_courses";

    @Autowired
    private CourseCache courseCache;

    @RequestMapping(value = URL_NEW_COURSE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void newCourse(@RequestParam("title") String title) {
        courseCache.newCourse(title);
    }

    @RequestMapping(value = URL_FIND_COURSE_BY_TITLE, method = RequestMethod.POST)
    public @ResponseBody String findByTitle(@RequestParam("title") String title) {
        CourseDTO course = courseCache.findByTitle(title);
        JSONObject item = new JSONObject();
        item.put("id", course.getId());
        item.put("title", course.getTitle());
        return item.toJSONString();
    }

    @RequestMapping(value = URL_GET_COURSES_PART, method = RequestMethod.POST)
    public @ResponseBody String getCoursesPart(@RequestParam("action") String action) {
        if      (action.equals("PREV") && page > 0) page--;
        else if (action.equals("NEXT"))             page++;
        else if (action.equals("UPLOAD"))           page = 0;

        List<CourseDTO> courses = courseCache.getCoursesPage(page);
        JSONArray body = new JSONArray();
        for (CourseDTO course : courses) {
            JSONObject item = new JSONObject();
            item.put("id", course.getId());
            item.put("title", course.getTitle());
            body.add(item);
        }
        return body.toJSONString();
    }

    @RequestMapping(value = URL_GET_COURSES, method = RequestMethod.GET)
    public @ResponseBody String getCourses() {
        List<CourseDTO> courses = courseCache.getAllCourses();
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
