package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soap.dao.CourseRepository;
import soap.entity.Course;
import soap.service.CourseService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Service
@CacheConfig(cacheNames = {"coursesCache"})
public class CourseServiceImpl implements CourseService {

    private Logger log;

    @Autowired
    private CourseRepository repository;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(CourseServiceImpl.class);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean create(String title) {
        Course course = new Course(title);
        if (repository.saveAndFlush(course) == null) {
            return false;
        }
        return true;
    }

    @Override
    public Course read(Long id) {
        if (id != null) {
            return repository.findOne(id);
        }
        return null;
    }

    @Override
    public Course findByTitle(String title) {
        if (title == null) {
            return null;
        }
        return repository.findByTitle(title);
    }

    @Override
    @Cacheable
    public Course[] findCoursesPart(Integer page, Integer count) {
        List<Course> list = repository.findAll(page, count);
        Course[] courses = new Course[list.size()];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = list.get(i);
        }
        return courses;
    }

    @Override
    @Cacheable
    public Course[] findAll() {
        List<Course> list = repository.findAll();
        Course[] courses = new Course[list.size()];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = list.get(i);
        }
        return courses;
    }
}
