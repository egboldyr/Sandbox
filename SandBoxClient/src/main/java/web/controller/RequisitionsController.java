package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.RequisitionDTO;
import web.service.RequisitionCache;

import java.util.List;

/**
 * Created by EGBoldyr on 02.04.18.
 */

@Controller
public class RequisitionsController {

    private Integer page;

    private static final String URL_UPDATE_REQUISITION = "/update_status";
    private static final String URL_FIND_PART_REQUISITION = "/part_requisitions";
    private static final String URL_FIND_ALL_REQUISITION = "/all_requisitions";

    @Autowired
    private RequisitionCache requisitionCache;

    @RequestMapping(value = URL_UPDATE_REQUISITION, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updateStatus(@RequestParam("req_id") Long req_id, @RequestParam("status") String status) {
        requisitionCache.updateRequisitionStatus(req_id, status);
    }

    @RequestMapping(value = URL_FIND_PART_REQUISITION, method = RequestMethod.POST)
    public @ResponseBody String findPartRequisitions(@RequestParam("amount") Integer amount, @RequestParam("action") String action) {
        if      (action.equals("PREV") && page > 0) page--;
        else if (action.equals("NEXT"))             page++;
        else if (action.equals("UPLOAD"))           page = 0;

        List<RequisitionDTO> requisitions = requisitionCache.getRequisitionsPage(page, amount);
        JSONArray body = new JSONArray();
        for (RequisitionDTO req : requisitions) {
            JSONObject jsonItem = new JSONObject();
            jsonItem.put("id", req.getId());
            jsonItem.put("status", req.getStatus());
            jsonItem.put("name", req.getName());
            jsonItem.put("phone", req.getPhoneNumber());
            jsonItem.put("email", req.getEmail());
            jsonItem.put("comment", req.getComment());
            jsonItem.put("date", req.getCreationDate());
            body.add(jsonItem);
        }
        return body.toJSONString();
    }

    @RequestMapping(value = URL_FIND_ALL_REQUISITION, method = RequestMethod.GET)
    public @ResponseBody String findAllRequisitions() {
        List<RequisitionDTO> requisitions = requisitionCache.getAllRequisitions();
        JSONArray body = new JSONArray();
        body.addAll(requisitions);
        return body.toJSONString();
    }
}
