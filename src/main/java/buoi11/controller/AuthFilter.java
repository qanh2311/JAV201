package buoi11.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", value = {
        "/nhan-vien/*",
        "/quan-ly/*"
})
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        Filter.super.init(config);
    }

    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if(role != null) {
            // da dang nhap
            if(role.equals("quanLy")) {
                chain.doFilter(request, response);
            } else if(role.equals("nhanVien")) {
                String uri = req.getRequestURI();
                if(uri.contains("quan-ly")) {
                    req.getRequestDispatcher("/buoi11/403.jsp").forward(req, resp);
                    return;
                }
                chain.doFilter(request, response);
            }
        } else {
            // chua dang nhap
            req.setAttribute("message", "Vui long dang nhap");
            req.getRequestDispatcher("/buoi11/login.jsp").forward(req, resp);
        }

    }
}