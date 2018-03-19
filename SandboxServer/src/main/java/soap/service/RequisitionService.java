package soap.service;

import soap.entity.Requisition;

import java.util.List;

/**
 * Created by EGBoldyr on 19.03.18.
 */

public interface RequisitionService {

    boolean create(String name, String phone, String email, String comment);

    boolean update(Requisition requisition);

    Requisition[] findAll();
}
