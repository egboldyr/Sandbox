package soap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import soap.entity.Group;

import java.util.Date;
import java.util.List;

/**
 * Created by EGBoldyr on 12.05.18.
 */

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByBeginDateGreaterThanEqual(Date beginDate);

    List<Group> findAllByCourseId(Long id);

}
