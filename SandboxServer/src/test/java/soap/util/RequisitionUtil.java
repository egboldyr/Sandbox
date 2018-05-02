package soap.util;

import soap.entity.Requisition;
import soap.entity.enums.RequisitionStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by EGBoldyr on 02.05.18.
 */
public class RequisitionUtil {

    public static Requisition createSingleRequisition() {
        Requisition requisition = new Requisition();
        requisition.setName("Ivan Ivanov");
        requisition.setPhoneNumber("0978575234");
        requisition.setEmail("ivanov@gmail.com");
        requisition.setComment("Comment...");
        requisition.setStatus(RequisitionStatus.NEW);
        requisition.setCreationDate(Calendar.getInstance().getTime());
        return requisition;
    }

    public static List<Requisition> createRequisitionsList() {
        List<Requisition> requisitions = new ArrayList<Requisition>();

        Requisition first = new Requisition();
        first.setName("Ivan Ivanov");
        first.setPhoneNumber("0978575234");
        first.setEmail("ivanov@gmail.com");
        first.setComment("Comment...");
        first.setStatus(RequisitionStatus.NEW);
        first.setCreationDate(Calendar.getInstance().getTime());
        requisitions.add(first);

        Requisition second = new Requisition();
        second.setName("Petr Fedorov");
        second.setPhoneNumber("0669871256");
        second.setEmail("fedorov_pg@gmail.com");
        second.setComment("Some comment...");
        second.setStatus(RequisitionStatus.NEW);
        second.setCreationDate(Calendar.getInstance().getTime());
        requisitions.add(second);

        Requisition third = new Requisition();
        third.setName("Vasilij Nikiforov");
        third.setPhoneNumber("0635487923");
        third.setEmail("nickifor@gmail.com");
        third.setComment("Comment...");
        third.setStatus(RequisitionStatus.NEW);
        third.setCreationDate(Calendar.getInstance().getTime());
        requisitions.add(third);

        return requisitions;
    }
}
