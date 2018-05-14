package web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import web.jaxws.RequisitionDTO;
import web.jaxws.RequisitionService;
import web.jaxws.RequisitionWebService;
import web.service.RequisitionCache;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

@Service
@CacheConfig(cacheNames = {"requisitionsFrontEndCache"})
public class RequisitionCacheImpl implements RequisitionCache {

    private Logger log;

    private RequisitionWebService ws;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(RequisitionCacheImpl.class);
        log.info("Start - RequisitionCache service...");
        RequisitionService service = new RequisitionService();
        ws = service.getRequisitionPort();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void updateRequisitionStatus(Long id, String status) {
        ws.updateStatus(id, status);
    }

    @Override
    @Cacheable
    public List<RequisitionDTO> getRequisitionsPage(Integer page, Integer amount) {
        return ws.getRequisitions(page, amount).getItem();
    }

    @Override
    @Cacheable
    public List<RequisitionDTO> getAllRequisitions() {
        return ws.allRequisitions().getItem();
    }


}
