package web.service;

import web.jaxws.GroupDTO;

import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

public interface GroupCache {

    void newGroup(String courseId, String title, String  begin,String end);

    List<GroupDTO> getGroupsByCourseId(String courseId);

    List<GroupDTO> getAllGroups();
}
