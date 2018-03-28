package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.entity.Course;
import soap.jaxws.CourseWebService;
import soap.service.CourseService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Component
@WebService(serviceName = "Course", portName = "CoursePort", endpointInterface = "soap.jaxws.CourseWebService")
public class CourseWebServiceImpl implements CourseWebService {

    private Logger log;

    @Autowired
    private CourseService service;

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
    public Course[] allCourses() {
        log.info("Receiving all courses... START");
        return service.findAll();
    }
}
