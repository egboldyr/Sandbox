package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.dto.RequisitionDTO;
import soap.entity.Requisition;
import soap.jaxws.RequisitionWebService;
import soap.service.RequisitionService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        log.info("Receiving " + count + " requisitions... START");

        Requisition[] requisitions = service.getRequisitions(from, count);
        RequisitionDTO[] body = new RequisitionDTO[requisitions.length];
        for (int i = 0; i < requisitions.length; i++) {
            RequisitionDTO item = new RequisitionDTO();
            item.setId(requisitions[i].getId());
            item.setStatus(requisitions[i].getStatus().toString());
            item.setName(requisitions[i].getName());
            item.setPhoneNumber(requisitions[i].getPhoneNumber());
            item.setEmail(requisitions[i].getEmail());
            item.setComment(requisitions[i].getComment());
            item.setCreationDate(sdf.format(requisitions[i].getCreationDate()));
            body[i] = item;
        }
        return body;
    }

    @Override
    public RequisitionDTO[] allRequisitions() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        log.info("Receiving all requisitions... START");
        Requisition[] requisitions = service.findAll();

        RequisitionDTO[] body = new RequisitionDTO[requisitions.length];
        //TODO : Добавить обработку по мере необходимости
        return body;
    }
}
