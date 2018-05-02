package soap.util;

import soap.entity.Course;

/**
 * Created by EGBoldyr on 02.05.18.
 */
public class CoursesUtil {

    public static Course createSingleCourseWithoutClientsAndGroups() {
        Course course = new Course();
        course.setTitle("Test Course 1");
        return course;
    }
}
