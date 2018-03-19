package soap.jaxws;

import soap.entity.Requisition;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface RequisitionWebService {

    @WebMethod
    boolean newRequisition(String name, String phone, String email, String comment);

    @WebMethod
    Requisition[] allRequisitions();
}
