package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TruongHoc;
import repository.TruongHocRepository;

import java.io.IOException;

@WebServlet(name = "truongHocController", value = {
        "/truong-hoc/hien-thi",
        "/truong-hoc/view-update",
        "/truong-hoc/xoa",
        "/truong-hoc/them",
        "/truong-hoc/sua",
})
public class TruongHocController extends HttpServlet {
    private TruongHocRepository truongHocRepository = new TruongHocRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("hien-thi")) {
            hienThi(req, resp);
        } else if (uri.contains("view-update")) {
            viewUpdate(req, resp);
        } else if (uri.contains("xoa")) {
            xoa(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.contains("them")) {
            them(req, resp);
        } else if (uri.contains("sua")) {
            sua(req, resp);
        }
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listTruongHoc", truongHocRepository.getAll());
        req.getRequestDispatcher("/buoi2/hien-thi.jsp").forward(req, resp);
    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            TruongHoc truongHoc = truongHocRepository.getById(id);
            req.setAttribute("truongHoc", truongHoc);
        }
        req.setAttribute("listTruongHoc", truongHocRepository.getAll());
        req.getRequestDispatcher("/buoi2/hien-thi.jsp").forward(req, resp);
    }

    private void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenTruong = req.getParameter("tenTruong");
        String diaChi = req.getParameter("diaChi");

        TruongHoc truongHoc = new TruongHoc();
        truongHoc.setTenTruong(tenTruong);
        truongHoc.setDiaChi(diaChi);

        truongHocRepository.themTruongHoc(truongHoc);

        resp.sendRedirect("/truong-hoc/hien-thi");
    }

    private void sua(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String tenTruong = req.getParameter("tenTruong");
        String diaChi = req.getParameter("diaChi");

        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            TruongHoc truongHoc = new TruongHoc();
            truongHoc.setId(id);
            truongHoc.setTenTruong(tenTruong);
            truongHoc.setDiaChi(diaChi);

            truongHocRepository.suaTruongHoc(truongHoc);
        }
        resp.sendRedirect("/truong-hoc/hien-thi");
    }

    private void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        if (idStr != null) {
            Integer id = Integer.parseInt(idStr);
            truongHocRepository.xoaTruongHoc(id);
        }

        resp.sendRedirect("/truong-hoc/hien-thi");
    }
}