package web.service;

import web.jaxws.CourseDTO;

import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

public interface CourseCache {

    void newCourse(String title);

    CourseDTO findByTitle(String title);

    List<CourseDTO> getCoursesPage(String action);

    List<CourseDTO> getAllCourses();
}
