package demoScope;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "demoScope1", value = {
        "/demo-scope-1"
})
public class DemoScope1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Request cope
        String message = "From demo REQUEST scope";
        req.setAttribute("demoRequestScope", message);

        String sessionMessage = "From demo SESSION scope";
        req.getSession().setAttribute("demoSessionScope", sessionMessage);

        // Application scope
        ServletContext sevletContext = getServletContext();
        String applicationMessage = "From demo APPLICATION scope";
        sevletContext.setAttribute("demoApplicationScope", applicationMessage);

        req.getRequestDispatcher("/demo-scope-2").forward(req, resp);
    }
}