package web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import web.jaxws.RequisitionWebService;
import web.jaxws.Requisition_Service;
import web.service.RequisitionClientService;

import javax.annotation.PostConstruct;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Service
public class RequisitionClientServiceImpl implements RequisitionClientService {

    private Logger log;
    private Requisition_Service requisition;
    private RequisitionWebService requisitionWS;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionClientServiceImpl.class);
        requisition = new Requisition_Service();
        requisitionWS = requisition.getRequisitionPort();
    }

    @Override
    public boolean createRequisition(String name, String phone, String email, String comment) {
        log.info("Create new requisition with parameters [" + name + ", " + phone + ", " + email + ", " + comment + "]");
        return requisitionWS.newRequisition(name, phone, email, comment);
    }
}
