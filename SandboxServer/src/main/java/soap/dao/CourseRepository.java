package soap.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import soap.entity.Course;

import java.util.List;

/**
 * Created by EGBoldyr on 12.05.18.
 */

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByTitle(String title);

    default List<Course> findAll(Integer page, Integer count) {
        return this.findAll(new PageRequest(page, count)).getContent();
    }
}
