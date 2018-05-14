package web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import web.jaxws.CourseDTO;
import web.jaxws.CourseService;
import web.jaxws.CourseWebService;
import web.service.CourseCache;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

@Service
@CacheConfig(cacheNames = {"coursesFrontEndCache"})
public class CourseCacheImpl implements CourseCache {

    private Logger log;

    private CourseWebService courseWS;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionCacheImpl.class);
        log.info("Start - CourseCache service...");

        CourseService service = new CourseService();
        courseWS = service.getCoursePort();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void newCourse(String title) {
        courseWS.newCourse(title);
    }

    @Override
    public CourseDTO findByTitle(String title) {
        return courseWS.findByTitle(title);
    }

    @Override
    @Cacheable
    public List<CourseDTO> getCoursesPage(Integer page) {
        return courseWS.findCoursesPart(page).getItem();
    }

    @Override
    @Cacheable
    public List<CourseDTO> getAllCourses() {
        return courseWS.allCourses().getItem();
    }


}
