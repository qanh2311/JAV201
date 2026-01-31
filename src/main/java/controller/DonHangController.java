package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DonHang;
import model.MonAn;
import repository.DonHangRepository;
import repository.MonAnRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "donHangController", value = {
        "/don-hang/hien-thi",
        "/don-hang/view-update",
        "/don-hang/them",
        "/don-hang/sua",
        "/don-hang/xoa",
        "/don-hang/tim-kiem-trang-thai",
        "/don-hang/tim-kiem-so-luong",
        "/don-hang/phan-trang",
})
public class DonHangController extends HttpServlet {
    MonAnRepository monAnRepository = new MonAnRepository();
    DonHangRepository donHangRepository = new DonHangRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("hien-thi")){
            hienThi(req, resp);
        }else if(uri.contains("view-update")){
            viewUpate(req, resp);
        }else if(uri.contains("xoa")){
            xoaDonHang(req, resp);
        }else if(uri.contains("phan-trang")){
            phanTrangDH(req, resp);
        }else if(uri.contains("tim-kiem-trang-thai")){
            timKiemTrangThai(req, resp);
        }else if(uri.contains("tim-kiem-so-luong")){
            timKiemSoLuong(req, resp);
        }
    }

    private void timKiemSoLuong(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String minStr = req.getParameter("min");
        String maxStr = req.getParameter("max");

        Integer min = (minStr == null || minStr.isEmpty()) ? 0 : Integer.parseInt(minStr);
        Integer max = (maxStr == null || maxStr.isEmpty()) ? Integer.MAX_VALUE : Integer.parseInt(maxStr);

        List<DonHang> list = donHangRepository.searchBySoLuong(min, max);

        req.setAttribute("listDonHang", list);
        req.setAttribute("listMonAn", monAnRepository.getAll());
        req.getRequestDispatcher("/buoi10/hien-thi.jsp").forward(req, resp);
    }


    private void timKiemTrangThai(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ttStr = req.getParameter("daGiao");

        Boolean trangThai = Boolean.valueOf(ttStr);

        List<DonHang> list = donHangRepository.searchByTrangThai(trangThai);

        req.setAttribute("listDonHang", list);
        req.setAttribute("listMonAn", monAnRepository.getAll());
        req.getRequestDispatcher("/buoi10/hien-thi.jsp").forward(req, resp);
    }



    private void phanTrangDH(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 3;

        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }

        // 1. Tính tổng số dòng và tổng số trang
        int totalRows = donHangRepository.getAll().size();
        int totalPages = (int) Math.ceil((double) totalRows / size);

        // 2. Xử lý logic quay vòng (Hình tròn)
        if (page < 1) {
            page = totalPages; // Nếu nhỏ hơn trang 1 thì nhảy về trang cuối
        } else if (page > totalPages) {
            page = 1; // Nếu lớn hơn trang cuối thì quay về trang 1
        }

        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages); // Gửi sang JSP để biết trang cuối
        req.setAttribute("listMonAn", monAnRepository.getAll());
        req.setAttribute("listDonHang", donHangRepository.phanTrang(page, size));
        req.getRequestDispatcher("/buoi10/hien-thi.jsp").forward(req, resp);
    }

    private void xoaDonHang(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        donHangRepository.xoaDonHang(id);
        resp.sendRedirect(req.getContextPath()+ "/don-hang/hien-thi");
    }

    private void viewUpate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("listMonAn", monAnRepository.getAll());
        req.setAttribute("dh", donHangRepository.getAllById(id));
        req.getRequestDispatcher("/buoi10/view-update.jsp").forward(req, resp);
    }

    private void hienThi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listMonAn", monAnRepository.getAll());
        req.setAttribute("listDonHang", donHangRepository.getAll());
        req.getRequestDispatcher("/buoi10/hien-thi.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if(uri.contains("them")){
            themDonHang(req,resp);
        }else if(uri.contains("sua")){
            suaDonHang(req, resp);
        }
    }

    private void suaDonHang(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String idStr = req.getParameter("id");
        String idMonAnStr = req.getParameter("idMonAn");
        String khachHang = req.getParameter("khachHang");
        String soLuongStr = req.getParameter("soLuong");
        String daGiaoStr = req.getParameter("daGiao");

        if (khachHang == null || khachHang.trim().isEmpty()
                || soLuongStr == null || soLuongStr.isEmpty()
                || idMonAnStr == null || idMonAnStr.isEmpty()
                || daGiaoStr == null || daGiaoStr.isEmpty()) {

            req.setAttribute("error", "Không được để trống dữ liệu!");
            hienThi(req, resp);
            return;
        }

        Integer soLuong = Integer.parseInt(soLuongStr);

        if (soLuong < 0) {
            req.setAttribute("error", "Số lượng không được là số âm!");
            hienThi(req, resp);
            return;
        }

        Integer idDonHang = Integer.parseInt(idStr);
        Integer idMonAn = Integer.parseInt(idMonAnStr);
        Boolean daGiao = Boolean.valueOf(daGiaoStr);

        MonAn monAn = monAnRepository.getAllById(idMonAn);
        DonHang donHang = new DonHang(idDonHang, khachHang, soLuong, daGiao, monAn);

        donHangRepository.suaDonHang(donHang);
        resp.sendRedirect(req.getContextPath() + "/don-hang/hien-thi");
    }



    private void themDonHang(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String idMonAnStr = req.getParameter("idMonAn");
        String khachHang = req.getParameter("khachHang");
        String soLuongStr = req.getParameter("soLuong");
        String daGiaoStr = req.getParameter("daGiao");

        if (khachHang == null || khachHang.trim().isEmpty()
                || soLuongStr == null || soLuongStr.isEmpty()
                || idMonAnStr == null || idMonAnStr.isEmpty()
                || daGiaoStr == null || daGiaoStr.isEmpty()) {

            req.setAttribute("error", "Không được để trống dữ liệu!");
            hienThi(req, resp);
            return;
        }

        Integer soLuong = Integer.parseInt(soLuongStr);

        if (soLuong < 0) {
            req.setAttribute("error", "Số lượng không được là số âm!");
            hienThi(req, resp);
            return;
        }

        Integer idMonAn = Integer.parseInt(idMonAnStr);
        Boolean daGiao = Boolean.valueOf(daGiaoStr);

        MonAn monAn = monAnRepository.getAllById(idMonAn);
        DonHang donHang = new DonHang(null, khachHang, soLuong, daGiao, monAn);

        donHangRepository.themDonHang(donHang);
        resp.sendRedirect(req.getContextPath() + "/don-hang/hien-thi");
    }

}
