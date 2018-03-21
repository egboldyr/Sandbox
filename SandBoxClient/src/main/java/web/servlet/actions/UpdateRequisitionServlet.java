package web.servlet.actions;

import web.jaxws.RequisitionWebService;
import web.jaxws.Requisition_Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by EGBoldyr on 20.03.18.
 */

@WebServlet("/update_status")
public class UpdateRequisitionServlet extends HttpServlet {

    private Requisition_Service requisition = new Requisition_Service();
    private RequisitionWebService requisitionWS = requisition.getRequisitionPort();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requisitionWS.updateStatus(
                Long.parseLong(req.getParameter("req_id")),
                req.getParameter("status")
        );
    }
}
