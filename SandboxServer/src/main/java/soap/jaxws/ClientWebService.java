package soap.jaxws;

import soap.entity.Client;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ClientWebService {

    @WebMethod
    boolean create(Client client);

    @WebMethod
    boolean update(Client client);

    @WebMethod
    boolean delete(Client client);

}
