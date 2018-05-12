package soap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import soap.dao.RequisitionDAO;
import soap.dao.RequisitionRepository;
import soap.entity.Requisition;
import soap.entity.enums.RequisitionStatus;
import soap.service.RequisitionService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@Service
@CacheConfig(cacheNames = {"requisitionsCache"})
public class RequisitionServiceImpl implements RequisitionService {

    private Logger log;

    @Autowired
    private RequisitionDAO dao;

    @Autowired
    private RequisitionRepository repository;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionServiceImpl.class);
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean create(String name, String phone, String email, String comment) {
        Requisition requisition = new Requisition(name, phone, email, comment);
        if (repository.saveAndFlush(requisition) == null) {
            return false;
        }
        return true;
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean update(Long id, String status) {
        log.info("Requisition updating... Change [STATUS : " + status + "] for requisition [ID : " + id + "].");
        Requisition requisition = repository.findOne(id);
        if (status.equals("PROCESS")) {
            requisition.setStatus(RequisitionStatus.PROCESS);
        } else if (status.equals("COMPLETE")) {
            requisition.setStatus(RequisitionStatus.COMPLETE);
        } else if (status.equals("DONE")) {
            requisition.setStatus(RequisitionStatus.DONE);
        } else {
            log.error("Incorrect [STATUS : " + status + "] Requisition update CANCELED.");
            return false;
        }
        dao.update(requisition);
        return true;
    }

    @Override
    @Cacheable
    public Requisition[] getRequisitions(Integer page, Integer count) {
        List<Requisition> list = repository.findAll(page, count);
        Requisition[] requisitions = new Requisition[list.size()];
        for (int i = 0; i < list.size(); i++) {
            requisitions[i] = list.get(i);
        }
        return requisitions;
    }

    @Override
    @Cacheable
    public Requisition[] findAll() {
        List<Requisition> list = repository.findAll();
        Requisition[] requisitions = new Requisition[list.size()];
        for (int i = 0; i < requisitions.length; i++) {
            requisitions[i] = list.get(i);
        }
        return requisitions;
    }
}
