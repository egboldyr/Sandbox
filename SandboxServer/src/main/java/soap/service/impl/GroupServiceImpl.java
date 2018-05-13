package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soap.dao.CourseRepository;
import soap.dao.GroupRepository;
import soap.entity.Course;
import soap.entity.Group;
import soap.service.GroupService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Service
@CacheConfig(cacheNames = {"groupsCache"})
public class GroupServiceImpl implements GroupService {

    private Logger log;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(GroupServiceImpl.class);
    }

    @CacheEvict(allEntries = true)
    @Override
    public boolean create(Long courseId, String title, Date begin, Date end) {
        if (courseId != null) {
            Course course = courseRepository.findOne(courseId);
            Group group = new Group(course, title, begin, end);
            course.getGroups().add(groupRepository.saveAndFlush(group));
            courseRepository.saveAndFlush(course);
        }
        return true;
    }

    @Cacheable
    @Override
    public Group[] findGroupsByActualDate(Date actualDate) {
        List<Group> list = groupRepository.findAllByBeginDateGreaterThanEqual(actualDate);
        Group[] groups = new Group[list.size()];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = list.get(i);
        }
        return groups;
    }

    @Cacheable
    @Override
    public Group[] findGroupsByCourseId(Long courseId) {
        Course course = courseRepository.findOne(courseId);

        if (course.getGroups() != null && course.getGroups().size() > 0) {
            List<Group> list = course.getGroups();
            Group[] groups = new Group[list.size()];
            for (int i = 0; i < groups.length; i++) {
                groups[i] = list.get(i);
            }
            return groups;
        }
        return new Group[0];
    }

    @Cacheable
    @Override
    public Group[] findAll() {
        List<Group> list = groupRepository.findAll();
        Group[] groups = new Group[list.size()];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = list.get(i);
        }
        return groups;
    }
}
