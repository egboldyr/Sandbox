package web.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.jaxws.GroupDTO;
import web.jaxws.GroupService;
import web.jaxws.GroupWebService;
import web.service.GroupCache;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 01.05.18.
 */

@Controller
public class GroupsController {

    private static final String URL_NEW_GROUP  ="/new_group";
    private static final String URL_ALL_GROUPS ="/get_all_groups";
    private static final String URL_GROUPS_BY_COURSE_ID ="/get_groups_by_course_id";

    @Autowired
    private GroupCache groupCache;

    @RequestMapping(value = URL_NEW_GROUP, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void newGroup(@RequestParam("course_id") String courseId,
                         @RequestParam("title") String title,
                         @RequestParam("begin_date") String beginDate,
                         @RequestParam("end_date") String endDate) {
        groupCache.newGroup(courseId, title, beginDate, endDate);
    }

    @RequestMapping(value = URL_GROUPS_BY_COURSE_ID, method = RequestMethod.POST)
    public @ResponseBody String getGroupsByCourseId(@RequestParam("course_id") String courseId) {
        List<GroupDTO> groups = groupCache.getGroupsByCourseId(courseId);

        JSONArray body = new JSONArray();
        for (GroupDTO group : groups) {
            JSONObject item = new JSONObject();
            item.put("id", group.getId());
            item.put("title", group.getTitle());
            item.put("begin_date", group.getBeginDate());
            item.put("end_date", group.getEndDate());
            body.add(item);
        }
        return body.toJSONString();
    }


    @RequestMapping(value = URL_ALL_GROUPS, method = RequestMethod.GET)
    public @ResponseBody String getAllGroups() {
        List<GroupDTO> groups = groupCache.getAllGroups();

        JSONArray body = new JSONArray();
        for (GroupDTO group : groups) {
            JSONObject item = new JSONObject();
            item.put("id", group.getId());
            item.put("title", group.getTitle());
            item.put("begin_date", group.getBeginDate());
            item.put("end_date", group.getEndDate());
            body.add(item);
        }
        return body.toJSONString();
    }

}
