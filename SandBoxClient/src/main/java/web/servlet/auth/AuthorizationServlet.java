package web.servlet.auth;

import web.jaxws.Authorization;
import web.jaxws.AuthorizationWebService;
import web.jaxws.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    private Authorization authorization;
    private AuthorizationWebService authorizationWS;

    public AuthorizationServlet() {
        authorization = new Authorization();
        authorizationWS = authorization.getAuthorizationPort();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = authorizationWS.authorization(req.getParameter("login"), req.getParameter("password"));
        if (client != null) {

        }
    }
}
