package pt.fabm;

import groovy.lang.Script;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainHandler extends AbstractHandler {

    private Script script = new DefaultHandlerScript();

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        script.getBinding().setVariable("request",request);
        script.getBinding().setVariable("response",response);
        Object rendered = script.run();
        response.getOutputStream().println(rendered.toString());
        baseRequest.setHandled(true);
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
