package web.servlet.create;

import web.jaxws.Client;
import web.jaxws.ClientService;
import web.jaxws.ClientWebService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EGBoldyr on 20.03.18.
 */

@WebServlet("/new_client")
public class NewClientCreateServlet extends HttpServlet {

    private ClientService service = new ClientService();
    private ClientWebService clientWS = service.getClientPort();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client();
        client.setName(req.getParameter("name"));
        client.setSurname(req.getParameter("surname"));
        client.setPhone(req.getParameter("phone"));
        client.setEmail(req.getParameter("email"));
        clientWS.create(client);
    }
}
