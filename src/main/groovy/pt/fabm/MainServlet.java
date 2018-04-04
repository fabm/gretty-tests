package pt.fabm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = -6506276378398106663L;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainGroovyServlet mainGroovyServlet = new MainGroovyServlet();
        mainGroovyServlet.getBinding().setVariable("request",req);
        mainGroovyServlet.getBinding().setVariable("response",resp);
        Object rendered = mainGroovyServlet.run();
        resp.getOutputStream().println(rendered.toString());
    }
}
