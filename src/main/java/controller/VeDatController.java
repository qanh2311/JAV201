package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Phim;
import model.VeDat;
import repository.PhimRepository;
import repository.VeDatRepository;

import java.io.IOException;
import java.time.LocalDate;

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
        }else if(uri.contains("xoa")){
            xoaVeDat(req,resp);
        }else if(uri.contains("tim-kiem")){
            timKiem(req, resp);
        }else if(uri.contains("phan-trang")){
            phanTrang(req, resp);
        }
    }

    private void phanTrang(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size= 3;
        if(req.getParameter("page")!= null){
            page = Integer.valueOf(req.getParameter("page"));
        }
        req.setAttribute("page", page);
        req.setAttribute("listPhim", phimRepository.getAll());
        req.setAttribute("listVeDat", veDatRepository.phanTrang(page, size));
        req.getRequestDispatcher("/buoi14/hien-thi.jsp").forward(req, resp);
    }

    private void timKiem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ten = req.getParameter("nguoiDat");
        req.setAttribute("listPhim", phimRepository.getAll());
        req.setAttribute("listVeDat", veDatRepository.searchByTen(ten));
        req.getRequestDispatcher("/buoi14/hien-thi.jsp").forward(req, resp);

    }

    private void xoaVeDat(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        veDatRepository.xoaVeDat(id);
        resp.sendRedirect(req.getContextPath() + "/ve-dat/hien-thi");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("them")){
            themVeDat(req,resp);
        }else if(uri.contains("sua")){
            suaVeDat(req, resp);
        }
    }

    private void suaVeDat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idPhim");
        String idVeDatStr = req.getParameter("id");
        String nguoiDat = req.getParameter("nguoiDat");
        String soLuong = req.getParameter("soLuongVe");
        String ngayChieuStr = req.getParameter("ngayChieu");
        String daThanhToanStr = req.getParameter("daThanhToan");

        if(nguoiDat.trim().isEmpty() || ngayChieuStr.trim().isEmpty()|| daThanhToanStr.trim().isEmpty()){
            req.setAttribute("error", "Khong duoc de trong du lieu");
            hienThi(req, resp);
            return;
        }

        Integer soLuongVe = Integer.parseInt(soLuong);
        LocalDate ngayChieu = LocalDate.parse(ngayChieuStr);
        Boolean daThanhToan = Boolean.valueOf(daThanhToanStr);
        if(soLuongVe < 0){
            req.setAttribute("error", "So luong khong duoc la so am");
            return;
        }
        Integer idPhim = Integer.parseInt(idStr);
        Integer idVeDat = Integer.parseInt(idVeDatStr);
        Phim phim = phimRepository.getAllById(idPhim);

        VeDat veDat = new VeDat(idVeDat, nguoiDat, soLuongVe, ngayChieu, daThanhToan, phim);
        veDatRepository.suaVeDat(veDat);

        resp.sendRedirect(req.getContextPath()+"/ve-dat/hien-thi");
    }

    private void themVeDat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("idPhim");
        String idVeDatStr = req.getParameter("id");
        String nguoiDat = req.getParameter("nguoiDat");
        String soLuong = req.getParameter("soLuongVe");
        String ngayChieuStr = req.getParameter("ngayChieu");
        String daThanhToanStr = req.getParameter("daThanhToan");

        if(nguoiDat.trim().isEmpty() || ngayChieuStr.trim().isEmpty()|| daThanhToanStr.trim().isEmpty()){
            req.setAttribute("error", "Khong duoc de trong du lieu");
            hienThi(req, resp);
            return;
        }

        Integer soLuongVe = Integer.parseInt(soLuong);
        LocalDate ngayChieu = LocalDate.parse(ngayChieuStr);
        Boolean daThanhToan = Boolean.valueOf(daThanhToanStr);
        if(soLuongVe < 0){
            req.setAttribute("error", "So luong khong duoc la so am");
            return;
        }
        Integer idPhim = Integer.parseInt(idStr);
        Phim phim = phimRepository.getAllById(idPhim);

        VeDat veDat = new VeDat(null, nguoiDat, soLuongVe, ngayChieu, daThanhToan, phim);
        veDatRepository.themVeDat(veDat);

        resp.sendRedirect(req.getContextPath()+"/ve-dat/hien-thi");
    }
}
