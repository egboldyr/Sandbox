package soap.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import soap.entity.Requisition;

import java.util.List;

/**
 * Created by EGBoldyr on 12.05.18.
 */

public interface RequisitionRepository extends JpaRepository<Requisition, Long> {

    default List<Requisition> findAll(Integer page, Integer count) {
        return this.findAll(new PageRequest(page, count)).getContent();
    }

}
