package soap.dao;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import soap.config.MySQLDBTestingConfig;
import soap.entity.Requisition;
import soap.entity.enums.RequisitionStatus;
import soap.util.RequisitionUtil;

import java.util.Calendar;
import java.util.List;

/**
 * Created by EGBoldyr on 02.05.18.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@EntityScan("soap.entity")
@ComponentScan(basePackages = {"soap.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RequisitionDaoTest {

    @Configuration
    @Import(MySQLDBTestingConfig.class)
    static class Config {}

    @Autowired
    private RequisitionDAO requisitionDAO;

    @Test
    public void testNewRequisitionPositive() {
        Requisition requisition = RequisitionUtil.createSingleRequisition();

        Long reqId = requisitionDAO.create(requisition);

        Requisition result = requisitionDAO.read(reqId);

        Assert.assertEquals(requisition.getName(), result.getName());
        Assert.assertEquals(requisition.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(requisition.getEmail(), result.getEmail());
        Assert.assertEquals(requisition.getComment(), result.getComment());
        Assert.assertEquals(requisition.getStatus(), result.getStatus());
        Assert.assertEquals(requisition.getCreationDate(), result.getCreationDate());
    }

    @Test
    public void testUpdateRequisitionPositive() {
        Requisition requisition = RequisitionUtil.createSingleRequisition();
        Long reqId = requisitionDAO.create(requisition);

        Requisition updateData = requisitionDAO.read(reqId);
        updateData.setStatus(RequisitionStatus.PROCESS);
        updateData.setCreationDate(Calendar.getInstance().getTime());
        requisitionDAO.update(updateData);

        Requisition result = requisitionDAO.read(reqId);

        Assert.assertEquals(updateData.getName(), result.getName());
        Assert.assertEquals(updateData.getPhoneNumber(), result.getPhoneNumber());
        Assert.assertEquals(updateData.getEmail(), result.getEmail());
        Assert.assertEquals(updateData.getComment(), result.getComment());
        Assert.assertEquals(updateData.getStatus(), result.getStatus());
        Assert.assertEquals(updateData.getCreationDate(), result.getCreationDate());
    }

    @Test
    public void testFindAllRequisitionsPositive() {
        List<Requisition> requisitions = RequisitionUtil.createRequisitionsList();
        for (Requisition req : requisitions) {
            requisitionDAO.create(req);
        }

        List<Requisition> result = requisitionDAO.findAll();
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindPaginationRequisitionsPositive() {
        List<Requisition> requisitions = RequisitionUtil.createRequisitionsList();
        for (Requisition req : requisitions) {
            requisitionDAO.create(req);
        }

        List<Requisition> result = requisitionDAO.findRequisitions(0, 2);
        Assert.assertEquals(2, result.size());
    }
}
