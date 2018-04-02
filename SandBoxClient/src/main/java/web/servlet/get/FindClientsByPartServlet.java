package web.servlet.get;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import web.jaxws.Client;
import web.jaxws.ClientService;
import web.jaxws.ClientWebService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by EGBoldyr on 02.04.18.
 */

@WebServlet("/part_clients")
public class FindClientsByPartServlet extends HttpServlet {

    private Integer from = 0;

    private ClientService client = new ClientService();
    private ClientWebService clientWS = client.getClientPort();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("PREV") & from > 0) {
            from -= 10;
        } else if (req.getParameter("action").equals("NEXT")) {
            from += 10;
        } else if (req.getParameter("action").equals("UPLOAD")) {
            from = 0;
        }

        List<Client> clients = clientWS.getClients(from).getItem();
        JSONArray jsonBody = new JSONArray();

        for (Client client : clients) {
            JSONObject jsonItem = new JSONObject();
            jsonItem.put("id", client.getId());
            jsonItem.put("name", client.getName());
            jsonItem.put("surname", client.getSurname());
            jsonItem.put("phone", client.getPhone());
            jsonItem.put("email", client.getEmail());
            jsonBody.add(jsonItem);
        }

        resp.setContentType("application/javascript");
        PrintWriter response = resp.getWriter();
        response.println(jsonBody.toJSONString());
        response.close();
    }
}
