package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.dto.CourseDTO;
import soap.entity.Course;
import soap.jaxws.CourseWebService;
import soap.service.CourseService;
import soap.util.CoursesMapper;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Component
@WebService(serviceName = "CourseService", portName = "CoursePort", endpointInterface = "soap.jaxws.CourseWebService")
public class CourseWebServiceImpl implements CourseWebService {

    private Logger log;

    @Autowired
    private CourseService service;

    @Autowired
    private CoursesMapper mapper;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(CourseWebServiceImpl.class);
        log.info("Initialize - Course service...");
    }

    @Override
    public boolean newCourse(String title) {
        log.info("Starting course service... CREATE OPTION.");
        return service.create(title);
    }

    @Override
    public CourseDTO findByTitle(String title) {
        log.info("Starting find course by TITLE option...");
        Course course = service.findByTitle(title);
        return mapper.courseToCourseDto(course);
    }

    @Override
    public CourseDTO[] findCoursesPart(Integer from) {
        log.info("Receiving courses... START");
        Course[] courses = service.findCoursesPart(from, 4);
        log.info("Receiving courses... COMPLETE");
        return mapper.coursesArrayToCourseDtoArray(courses);
    }

    @Override
    public CourseDTO[] allCourses() {
        log.info("Receiving all courses... START");
        log.info("Receiving courses... START");
        Course[] courses = service.findAll();
        log.info("Receiving courses... COMPLETE");
        return mapper.coursesArrayToCourseDtoArray(courses);
    }
}
