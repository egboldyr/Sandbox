package web.service;

/**
 * Created by EGBoldyr on 19.03.18.
 */
public interface RequisitionClientService {

    boolean createRequisition(String name, String phone, String email, String comment);

}
