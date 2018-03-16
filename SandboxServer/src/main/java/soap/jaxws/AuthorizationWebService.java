package soap.jaxws;

import soap.entity.Client;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by EGBoldyr on 15.03.18.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AuthorizationWebService {

    @WebMethod
    Client authorization(String login, String password);
}
