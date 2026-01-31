package buoi11.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "loginController", value = {
        "/login", // GET - hien thi form, POST - nhan form
        "/logout" // GET - dang xuat
})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("login")) {
            req.getRequestDispatcher("/buoi11/login.jsp").forward(req, resp);
        } else if(uri.contains("logout")) {
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        // tai khoan nhan vien: abc - 123
        // tai khoan quan ly: bde - 234
        if (
                (username.equals("abc") && password.equals("123"))
                        ||
                        (username.equals("bde") && password.equals("234"))
        ) {
            // tai khoan, mat khau dung
            if(username.equals("abc")) {
                session.setAttribute("role", "nhanVien");
                resp.sendRedirect("/nhan-vien/hien-thi");
            } else if(username.equals("bde")) {
                session.setAttribute("role", "quanLy");
                resp.sendRedirect("/quan-ly/hien-thi");
            }
        } else {
            // tai khoan, mat khau sai
            req.setAttribute("message", "Sai thong tin dang nhap");
            req.getRequestDispatcher("/buoi11/login.jsp").forward(req, resp);
        }
    }
}