package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.PhimRepository;
import repository.VeDatRepository;

import java.io.IOException;

@WebServlet(name = "veDatController", value = {
        "/ve-dat/hien-thi",
        "/ve-dat/view-update",
        "/ve-dat/them",
        "/ve-dat/sua",
        "/ve-dat/xoa",
        "/ve-dat/tim-kiem",
        "/ve-dat/phan-trang",
})
public class VeDatController extends HttpServlet {
    PhimRepository phimRepository = new PhimRepository();
    VeDatRepository veDatRepository = new VeDatRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("hien-thi")){
            hienThi(req, resp);
        }else if(uri.contains("view-update")){
            viewUpdate(req, resp);
        }
    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("listPhim", phimRepository.getAll());
        req.setAttribute("vd", veDatRepository.getAllById(id));
        req.getRequestDispatcher("/buoi14/view-update.jsp").forward(req, resp);
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listPhim", phimRepository.getAll());
        req.setAttribute("listVeDat", veDatRepository.getAll());
        req.getRequestDispatcher("/buoi14/hien-thi.jsp").forward(req, resp);
    }
}
