package demoAjax;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ajaxController", value = {
        "/hien-thi",
        "/api/list"
})
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("hien-thi")) {
            hienThi(req, resp);
        }else if(uri.contains("api/list")){
            ajaxApi(req, resp);
        }
    }

    private void ajaxApi(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SinhVien sv = new SinhVien(1, "Nguyen van a", true);
        // Chuyen object sang string sjon
        Gson gson = new Gson();
        String data = gson.toJson(sv);
        // Dat kieu du lieu tra ve cua header resp
        resp.setContentType("application/json");
        // Day du lieu xuong client
        PrintWriter pw = resp.getWriter();
        pw.print(data);
        pw.flush();
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/demoAjax/hien-thi.jsp").forward(req, resp);
    }
}
