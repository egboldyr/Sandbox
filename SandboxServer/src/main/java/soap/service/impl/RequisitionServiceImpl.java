package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soap.dao.RequisitionDAO;
import soap.entity.Requisition;
import soap.service.RequisitionService;

import javax.annotation.PostConstruct;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Service
public class RequisitionServiceImpl implements RequisitionService {

    private Logger log;

    @Autowired
    private RequisitionDAO dao;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionServiceImpl.class);
    }

    @Override
    public boolean create(String name, String phone, String email, String comment) {
        Requisition requisition = new Requisition(name, phone, email, comment);
        if (dao.create(requisition) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Requisition requisition) {
        return false;
    }

    @Override
    public Requisition[] findAll() {
        return new Requisition[0];
    }
}
