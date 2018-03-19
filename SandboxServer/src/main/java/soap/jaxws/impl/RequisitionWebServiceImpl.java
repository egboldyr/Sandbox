package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.entity.Requisition;
import soap.jaxws.RequisitionWebService;
import soap.service.RequisitionService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Component
@WebService(serviceName = "Requisition", portName = "RequisitionPort", endpointInterface = "soap.jaxws.RequisitionWebService")
public class RequisitionWebServiceImpl implements RequisitionWebService {

    private Logger log;

    @Autowired
    private RequisitionService service;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionWebServiceImpl.class);
        log.info("Initialize - Requisition service...");
    }

    @Override
    public boolean newRequisition(String name, String phone, String email, String comment) {
        log.info("Starting requisition service...");
        return service.create(name, phone, email, comment);
    }

    @Override
    public Requisition[] allRequisitions() {
        return new Requisition[0];
    }
}
