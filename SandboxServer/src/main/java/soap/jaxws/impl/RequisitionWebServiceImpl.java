package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.dto.RequisitionDTO;
import soap.entity.Requisition;
import soap.jaxws.RequisitionWebService;
import soap.service.RequisitionService;
import soap.util.mapper.RequisitionsMapper;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Component
@WebService(serviceName = "RequisitionService", portName = "RequisitionPort", endpointInterface = "soap.jaxws.RequisitionWebService")
public class RequisitionWebServiceImpl implements RequisitionWebService {

    private Logger log;

    @Autowired
    private RequisitionService service;

    @Autowired
    private RequisitionsMapper mapper;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionWebServiceImpl.class);
        log.info("Initialize - Requisition service...");
    }

    @Override
    public boolean newRequisition(String name, String phone, String email, String comment) {
        log.info("Starting requisition service... CREATE OPTION.");
        return service.create(name, phone, email, comment);
    }

    @Override
    public boolean updateStatus(Long id, String status) {
        log.info("Starting requisition service... UPDATE OPTION.");
        return service.update(id, status);
    }

    @Override
    public RequisitionDTO[] getRequisitions(Integer from, Integer count) {
        log.info("Receiving " + count + " requisitions... START");
        Requisition[] requisitions = service.getRequisitions(from, count);
        return mapper.requitionsArrayToRequisitionDtoArray(requisitions);
    }

    @Override
    public RequisitionDTO[] allRequisitions() {
        log.info("Receiving all requisitions... START");
        Requisition[] requisitions = service.findAll();
        return mapper.requitionsArrayToRequisitionDtoArray(requisitions);
    }
}
