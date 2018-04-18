package soap.dao;

import soap.entity.Course;

import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

public interface CourseDAO {

    Long create(Course course);

    Course read(Long id);

    Course findByTitle(String title);

    List<Course> findPart(Integer from, Integer count);

    List<Course> findAll();

}
