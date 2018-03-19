package soap.dao;

import soap.entity.Requisition;

import java.util.List;

/**
 * Created by EGBoldyr on 19.03.18.
 */

public interface RequisitionDAO {

    Long create(Requisition requisition);

    boolean update(Requisition requisition);

    List<Requisition> findAll();
}
