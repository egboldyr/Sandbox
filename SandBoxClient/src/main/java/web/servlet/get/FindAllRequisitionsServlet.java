package web.servlet.get;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import web.jaxws.Requisition;
import web.jaxws.RequisitionWebService;
import web.jaxws.Requisition_Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by EGBoldyr on 19.03.18.
 */

@WebServlet("/all_requisitions")
public class FindAllRequisitionsServlet extends HttpServlet {

    private Requisition_Service requisition = new Requisition_Service();
    private RequisitionWebService requisitionWS = requisition.getRequisitionPort();

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Requisition> requisitions = requisitionWS.allRequisitions().getItem();
        JSONArray jsonBody = new JSONArray();
        for (Requisition requisition : requisitions) {
            JSONObject jsonItem = new JSONObject();
            jsonItem.put("id", requisition.getId());
            jsonItem.put("status", requisition.getStatus().toString());
            jsonItem.put("name", requisition.getName());
            jsonItem.put("phone", requisition.getPhoneNumber());
            jsonItem.put("email", requisition.getEmail());
            jsonItem.put("comment", requisition.getComment());
            String dateStr = sdf.format(requisition.getCreationDate().toGregorianCalendar().getTime());
            jsonItem.put("date", dateStr);
            jsonBody.add(jsonItem);
        }

        resp.setContentType("application/javascript");
        PrintWriter response = resp.getWriter();
        response.println(jsonBody.toJSONString());
        response.close();
    }
}
