package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soap.dao.CourseDAO;
import soap.dao.GroupDAO;
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
    private CourseDAO courseDAO;

    @Autowired
    private GroupDAO groupDAO;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(GroupServiceImpl.class);
    }

    @CachePut
    @Override
    public boolean create(Long courseId, String title, Date begin, Date end) {
        if (courseId != null) {
            Course course = courseDAO.read(courseId);
            Group group = new Group(course, title, begin, end);
            if (groupDAO.create(group) == null) {
                return false;
            }
        }
        return true;
    }

    @Cacheable
    @Override
    public Group[] findGroupsByActualDate(Date actualDate) {
        List<Group> list = groupDAO.findAllByActualDate(actualDate);
        Group[] groups = new Group[list.size()];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = list.get(i);
        }
        return groups;
    }

    @Cacheable
    @Override
    public Group[] findAll() {
        List<Group> list = groupDAO.findAll();
        Group[] groups = new Group[list.size()];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = list.get(i);
        }
        return groups;
    }
}
