package soap.dao;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import soap.config.MySQLDBTestingConfig;
import soap.entity.Course;
import soap.util.CoursesUtil;

/**
 * Created by EGBoldyr on 02.05.18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@EntityScan("soap.entity")
@ComponentScan(basePackages = {"soap.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoursesDaoTest {

    @Configuration
    @Import(MySQLDBTestingConfig.class)
    static class Config {}

    @Autowired
    private CourseDAO courseDAO;

    @Test
    public void testCreateCourseWithoutGroupsAndClientsPositive() {
        Course course = CoursesUtil.createSingleCourseWithoutClientsAndGroups();
        Long courseId = courseDAO.create(course);

        Course result = courseDAO.read(courseId);

        Assert.assertEquals(course.getTitle(), result.getTitle());
        Assert.assertEquals(0, result.getGroups().size());
        Assert.assertEquals(0, result.getClients().size());
    }

}
