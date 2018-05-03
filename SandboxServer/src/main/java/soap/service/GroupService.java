package soap.service;

import soap.entity.Group;

import java.util.Date;

/**
 * Created by EGBoldyr on 28.03.18.
 */

public interface GroupService {

    boolean create(Long courseId, String title, Date begin, Date end);

    Group[] findGroupsByActualDate(Date actualDate);

    Group[] findGroupsByCourseId(Long courseId);

    Group[] findAll();

}
