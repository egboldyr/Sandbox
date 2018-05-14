package web.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import web.jaxws.GroupDTO;
import web.jaxws.GroupService;
import web.jaxws.GroupWebService;
import web.service.GroupCache;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by EGBoldyr on 14.05.18.
 */

@Service
@Cacheable(cacheNames = {"groupsFrontEndCache"})
public class GroupCacheImpl implements GroupCache {

    private GroupWebService groupWS;

    @PostConstruct
    private void initialize() {
        GroupService service = new GroupService();
        groupWS = service.getGroupPort();
    }

    @Override
    @CacheEvict(cacheNames = {"groupsFrontEndCache"}, allEntries = true)
    public void newGroup(String courseId, String title, String begin, String end) {
        groupWS.newGroup(courseId, title, begin, end);
    }

    @Override
    @Cacheable(cacheNames = {"groupsFrontEndCache"})
    public List<GroupDTO> getGroupsByCourseId(String courseId) {
        return groupWS.byCourseId(courseId).getItem();
    }

    @Override
    @Cacheable(cacheNames = {"groupsFrontEndCache"})
    public List<GroupDTO> getAllGroups() {
        return groupWS.allGroups().getItem();
    }

}
