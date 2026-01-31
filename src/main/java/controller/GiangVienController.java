package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.GiangVien;
import model.TruongHoc;
import repository.GiangVienRepository;
import repository.TruongHocRepository;

import java.io.IOException;

@WebServlet(name = "giangVienController", value = {
        "/giang-vien/hien-thi", // GET
        "/giang-vien/view-update", // GET
        "/giang-vien/xoa", // GET
        "/giang-vien/them", // POST
        "/giang-vien/sua", // POST
        "/giang-vien/tim-kiem", // GET
        "/giang-vien/phan-trang" // GET
})
public class GiangVienController extends HttpServlet {
    GiangVienRepository giangVienRepository = new GiangVienRepository();
    TruongHocRepository truongHocRepository = new TruongHocRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("hien-thi")){
            hienThi(req, resp);
        } else if(uri.contains("view-update")){
            viewUpdate(req, resp);
        } else if(uri.contains("xoa")){
            xoa(req, resp);
        }else if(uri.contains("tim-kiem")){
            timKiemTen(req, resp);
        }else if(uri.contains("phan-trang")){
            phanTrangGV(req, resp);
        }
    }

    private void phanTrangGV(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        int page = 1;
        int size = 3;
        if(req.getParameter("page") != null){ //check chuyen page hay khong
            page= Integer.valueOf(req.getParameter("page")); // check gui dc den page hay khong va chuyen gia tri
        }
        int totalRows = giangVienRepository.getAll().size();
//        double totalPages = Math.

        req.setAttribute("page", page);
        req.setAttribute("ListGiangVien", giangVienRepository.phanTrang(page, size));
        req.setAttribute("ListTruongHoc", truongHocRepository.getAll());
        req.getRequestDispatcher("/buoi4/hien-thi.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("them")){
            them(req, resp);
        } else if(uri.contains("sua")){
            sua(req, resp);
        }
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setAttribute("ListGiangVien", giangVienRepository.getAll());
        req.setAttribute("ListTruongHoc", truongHocRepository.getAll());
        req.getRequestDispatcher("/buoi4/hien-thi.jsp").forward(req, resp);
    }

    private void them(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String tenGiangVien = req.getParameter("ten_giang_vien");
            Integer tuoi = Integer.parseInt(req.getParameter("tuoi"));
            String gioiTinhStr = req.getParameter("gioi_tinh");
            Boolean gioiTinh = "Nữ".equals(gioiTinhStr);
            Integer truongId = Integer.parseInt(req.getParameter("truong_id"));

            GiangVien gv = new GiangVien();
            gv.setTenGiangVien(tenGiangVien);
            gv.setTuoi(tuoi);
            gv.setGioiTinh(gioiTinh);

            TruongHoc truongHoc = truongHocRepository.getById(truongId);
            gv.setTruongHoc(truongHoc);

            giangVienRepository.themGiangVien(gv);
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        }
    }

    private void timKiemTen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String ten = req.getParameter("ten");
        req.setAttribute("ListGiangVien", giangVienRepository.searchByTen(ten));
        req.setAttribute("ListTruongHoc", truongHocRepository.getAll());
        req.getRequestDispatcher("/buoi4/hien-thi.jsp").forward(req, resp);
    }
    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            GiangVien gv = giangVienRepository.getByID(id);
            req.setAttribute("giangVien", gv);
            req.setAttribute("ListGiangVien", giangVienRepository.getAll());
            req.setAttribute("ListTruongHoc", truongHocRepository.getAll());
            req.getRequestDispatcher("/buoi4/hien-thi.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        }
    }

    private void sua(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            String tenGiangVien = req.getParameter("ten_giang_vien");
            Integer tuoi = Integer.parseInt(req.getParameter("tuoi"));
            String gioiTinhStr = req.getParameter("gioi_tinh");
            Boolean gioiTinh = "Nữ".equals(gioiTinhStr);
            Integer truongId = Integer.parseInt(req.getParameter("truong_id"));

            GiangVien gv = new GiangVien();
            gv.setId(id);
            gv.setTenGiangVien(tenGiangVien);
            gv.setTuoi(tuoi);
            gv.setGioiTinh(gioiTinh);

            TruongHoc truongHoc = truongHocRepository.getById(truongId);
            gv.setTruongHoc(truongHoc);

            giangVienRepository.suaGiangVien(gv);
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        }
    }

    private void xoa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            giangVienRepository.xoaGiangVien(id);
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/giang-vien/hien-thi");
        }
    }
}