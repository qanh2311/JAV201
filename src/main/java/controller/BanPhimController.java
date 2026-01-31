package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.BanPhim;
import repository.BanPhimRepository;

import java.io.IOException;

@WebServlet({
        "/ban-phim/hien-thi",
        "/ban-phim/them",
        "/ban-phim/xoa",
        "/ban-phim/view-update",
        "/ban-phim/sua"
})
public class BanPhimController extends HttpServlet {

    BanPhimRepository repo = new BanPhimRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.contains("hien-thi")) {
            req.setAttribute("list", repo.getAll());
            req.getRequestDispatcher("/WEB-INF/views/hien-thi.jsp").forward(req, resp);

        } else if (uri.contains("view-update")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            req.setAttribute("bp", repo.getById(id));
            req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);

        } else if (uri.contains("xoa")) {
            repo.xoa(Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect("/ban-phim/hien-thi");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String ten = req.getParameter("tenBanPhim");
        Boolean tinhTrang = Boolean.valueOf(req.getParameter("tinhTrang"));
        Double gia = Double.valueOf(req.getParameter("gia"));
        Integer soLuong = Integer.valueOf(req.getParameter("soLuong"));

        if (req.getRequestURI().contains("them")) {
            repo.them(new BanPhim(null, ten, tinhTrang, gia, soLuong));
        } else {
            Integer id = Integer.valueOf(req.getParameter("id"));
            repo.sua(new BanPhim(id, ten, tinhTrang, gia, soLuong));
        }

        resp.sendRedirect("/ban-phim/hien-thi");
    }
}