package soap.service;

import soap.entity.Course;

/**
 * Created by EGBoldyr on 28.03.18.
 */

public interface CourseService {

    boolean create(String title);

    Course[] findAll();

}
