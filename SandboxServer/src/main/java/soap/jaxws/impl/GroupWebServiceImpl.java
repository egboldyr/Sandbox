package soap.jaxws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soap.entity.Group;
import soap.jaxws.GroupWebService;
import soap.service.GroupService;

import javax.annotation.PostConstruct;
import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EGBoldyr on 28.03.18.
 */

@Component
@WebService(serviceName = "Group", portName = "GroupPort", endpointInterface = "soap.jaxws.GroupWebService")
public class GroupWebServiceImpl implements GroupWebService {

    private Logger log;
    private SimpleDateFormat sdf;

    @Autowired
    private GroupService service;

    @PostConstruct
    private void initialize() {
        log = LoggerFactory.getLogger(GroupWebServiceImpl.class);
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        log.info("Initialize - Group service...");
    }

    @Override
    public boolean newGroup(String courseId, String title, String beginDate, String endDate) {
        try {
            log.info("Starting group service... CREATE OPTION.");
            log.info("Checking [COURSE_ID : " + courseId + "]");
            Long id = Long.parseLong(courseId);

            log.info("Checking [BEGIN_DATE: " + beginDate + ", END_DATE: " + endDate + "]");
            Date begin = sdf.parse(beginDate);
            Date end = sdf.parse(endDate);
            log.info("Checking COMPLETE.");

            return service.create(id, title, begin, end);
        } catch (ParseException exc) {
            log.error("Error : " + exc.getMessage());
            return false;
        }
    }

    @Override
    public Group[] byActualDate(String actualDate) {
        try {
            log.info("Receiving actual groups... START");
            log.info("Checking [ACTUAL_DATE: " + actualDate + "]");
            Date actual = sdf.parse(actualDate);
            log.info("Checking COMPLETE.");
            return service.findGroupsByActualDate(actual);
        } catch (ParseException exc) {
            log.error("Error : " + exc.getMessage());
            return null;
        }
    }

    @Override
    public Group[] allGroups() {
        log.info("Receiving all groups... START");
        return service.findAll();
    }
}
