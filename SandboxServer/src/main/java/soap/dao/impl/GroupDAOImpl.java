package soap.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import soap.dao.GroupDAO;
import soap.entity.Group;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Repository
@Transactional
public class GroupDAOImpl implements GroupDAO {

    private Logger log;

    @Autowired
    private SessionFactory factory;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(GroupDAOImpl.class);
    }

    @Override
    public Long create(Group group) {
        try {
            log.info("Creating new group...");
            Long groupId = (Long) factory.getCurrentSession().save(group);
            log.info("Creating SUCCESS. New group [ ID : " + groupId + "]");
            return groupId;
        } catch (HibernateException exc) {
            factory.getCurrentSession().getTransaction().rollback();
            log.error("Creating group FAIL.");
            return null;
        }
    }

    @Override
    public Group read(Long id) {
        try {
            log.info("Reading group... [ ID : " + id + "]");
            Group group = factory.getCurrentSession().get(Group.class, id);
            log.info("Reading group... SUCCESS.");
            return group;
        } catch (HibernateException exc) {
            log.error("Reading group... FAIL. Incorrect [ ID : " + id + "]");
            return null;
        }
    }

    @Override
    public boolean update(Group group) {
        try {
            log.info("Updating group status...");
            factory.getCurrentSession().update(group);
            log.info("Updating group SUCCESS.");
            return true;
        } catch (HibernateException exc) {
            factory.getCurrentSession().getTransaction().rollback();
            log.error("Updating group FAIL.");
            return false;
        }
    }

    @Override
    public List<Group> findAllByActualDate(Date actualDate) {
        try {
            log.info("Receiving actual groups...");
            List<Group> groups = factory.getCurrentSession()
                                            .createCriteria(Group.class)
                                            .add(Restrictions.ge("beginDate", actualDate))
                                            .list();
            log.info("Receiving actual groups... COMPLETE. Find " + groups.size() + " groups.");
            return groups;
        } catch (HibernateException exc) {
            log.error("Receiving actual groups, FAIL.");
            return null;
        }
    }

    @Override
    public List<Group> findAll() {
        try {
            log.info("Receiving all groups...");
            List<Group> groups =
                    factory.getCurrentSession().createCriteria(Group.class).list();
            log.info("Receiving all groups, COMPLETE.");
            return groups;
        } catch (HibernateException exc) {
            log.error("Receiving all groups, FAIL.");
            return null;
        }
    }
}
