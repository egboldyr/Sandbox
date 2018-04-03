package soap.jaxws;

import soap.dto.ClientDTO;

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
    boolean create(ClientDTO client);

    @WebMethod
    boolean update(ClientDTO client);

    @WebMethod
    boolean delete(ClientDTO client);

    @WebMethod
    ClientDTO[] getClients(Integer from);
}
