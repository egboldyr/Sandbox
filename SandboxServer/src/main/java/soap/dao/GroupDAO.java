package soap.dao;

import soap.entity.Group;

import java.util.Date;
import java.util.List;

/**
 * Created by EGBoldyr on 28.03.18.
 */

public interface GroupDAO {

    Long create(Group group);

    Group read(Long id);

    boolean update(Group group);

    List<Group> findAllByActualDate(Date actualDate);

    List<Group> findAllByCourseId(Long courseId);

    List<Group> findAll();

}
