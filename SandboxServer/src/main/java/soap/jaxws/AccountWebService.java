package soap.jaxws;

import soap.entity.Account;
import soap.entity.Client;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AccountWebService {

    @WebMethod
    boolean create(Client client, Account account);

    @WebMethod
    boolean update(Account account);

    @WebMethod
    boolean delete(Account account);

}
