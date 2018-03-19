package web.servlet.page;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;
import web.servlet.util.VelocityEngineUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by EGBoldyr on 16.03.18.
 */

@WebServlet("/index.html")
public class IndexPageServlet extends VelocityViewServlet {

    @Override
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
        return VelocityEngineUtil.startTemplate("index/index.vm");
    }
}
