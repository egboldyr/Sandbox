package soap.dao.impl;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soap.dao.CourseDAO;
import soap.entity.Course;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Repository
@Transactional
public class CourseDAOImpl implements CourseDAO {

    private Logger log;

    @Autowired
    private SessionFactory factory;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(CourseDAOImpl.class);
    }

    @Override
    public Long create(Course course) {
        try {
            log.info("Creating new course...");
            Long courseId = (Long) factory.getCurrentSession().save(course);
            log.info("Creating SUCCESS. New course [ ID : " + course + "]");
            return courseId;
        } catch (HibernateException exc) {
            factory.getCurrentSession().getTransaction().rollback();
            log.error("Creating course FAIL.");
            log.error("Error : " + exc.getMessage());
            return null;
        }
    }

    @Override
    public Course read(Long id) {
        try {
            log.info("Reading course... [ ID : " + id + "]");
            Course course = factory.getCurrentSession().get(Course.class, id);
            log.info("Reading course... COMPLETE.");
            return course;
        } catch (HibernateException exc) {
            log.error("Reading course... FAIL. Incorrect [ID : " + id + "]");
            log.error("Error : " + exc.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Course course) {
        try {
            log.info("Updating course... [ ID: " + course.getId() + "]");
            factory.getCurrentSession().update(course);
            return true;
        } catch (HibernateException exc) {
            factory.getCurrentSession().getTransaction().rollback();
            log.error("Updating course... FAIL. Incorrect [ID : " + course.getId() + "]");
            log.error("Error : " + exc.getMessage());
            return false;
        }
    }

    @Override
    public Course findByTitle(String title) {
        try {
            log.info("Reading course... [ TITLE : " + title + "]");
            Course course = (Course) factory.getCurrentSession()
                    .createCriteria(Course.class)
                    .add(Restrictions.eq("title", title))
                    .uniqueResult();
            log.info("Reading course... COMPLETE.");
            return course;
        } catch (HibernateException exc) {
            log.error("Reading course... FAIL. Incorrect [TITLE : " + title + "]");
            log.error("Error : " + exc.getMessage());
            return null;
        }
    }

    @Override
    public List<Course> findPart(Integer from, Integer count) {
        try {
            log.info("Receiving " + count + " courses...");
            List<Course> courses = factory.getCurrentSession()
                                                .createCriteria(Course.class)
                                                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
                                                .setFirstResult(from)
                                                .setMaxResults(count)
                                                .list();
            log.info("Receiving " + count + " courses, COMPLETE.");
            return courses;
        } catch (HibernateException exc) {
            log.error("Receiving courses, FAIL.");
            log.error("Error : " + exc.getMessage());
            return null;
        }
    }

    @Override
    public List<Course> findAll() {
        try {
            log.info("Receiving all courses...");
            List<Course> courses =
                    factory.getCurrentSession().createCriteria(Course.class).list();
            log.info("Receiving all courses, COMPLETE. Check " + courses.size() + " items.");
            return courses;
        } catch (HibernateException exc) {
            log.error("Receiving all courses, FAIL.");
            log.error("Error : " + exc.getMessage());
            return null;
        }
    }
}
