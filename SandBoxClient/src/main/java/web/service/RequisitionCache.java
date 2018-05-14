package web.service;

import web.jaxws.RequisitionDTO;

import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

public interface RequisitionCache {

    void updateRequisitionStatus(Long id, String status);

    List<RequisitionDTO> getRequisitionsPage(String action, Integer amount);

    List<RequisitionDTO> getAllRequisitions();
}
