package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soap.dao.RequisitionDAO;
import soap.entity.Requisition;
import soap.entity.enums.RequisitionStatus;
import soap.service.RequisitionService;

import javax.annotation.PostConstruct;
import java.util.List;

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
    public boolean update(Long id, String status) {
        Requisition requisition = dao.read(id);
        if (status.equals("PROCESS")) {
            requisition.setStatus(RequisitionStatus.PROCESS);
        } else if (status.equals("DONE")) {
            requisition.setStatus(RequisitionStatus.DONE);
        } else {
            return false;
        }
        dao.update(requisition);
        return true;
    }

    @Override
    public Requisition[] getRequisitions(Integer from, Integer count) {
        List<Requisition> list = dao.findRequisitions(from, count);
        Requisition[] requisitions = new Requisition[list.size()];
        for (int i = 0; i < list.size(); i++) {
            requisitions[i] = list.get(i);
        }
        return requisitions;
    }

    @Override
    public Requisition[] findAll() {
        List<Requisition> list = dao.findAll();
        Requisition[] requisitions = new Requisition[list.size()];
        for (int i = 0; i < requisitions.length; i++) {
            requisitions[i] = list.get(i);
        }
        return requisitions;
    }
}
