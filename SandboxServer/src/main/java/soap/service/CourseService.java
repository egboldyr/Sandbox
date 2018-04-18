package soap.service;

import soap.entity.Course;

/**
 * Created by EGBoldyr on 28.03.18.
 */

public interface CourseService {

    boolean create(String title);

    Course read(Long id);

    Course findByTitle(String title);

    Course[] findCoursesPart(Integer from, Integer count);

    Course[] findAll();

}
