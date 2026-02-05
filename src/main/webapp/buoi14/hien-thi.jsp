<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<<<<<<< HEAD
<%--
  Created by IntelliJ IDEA.
  User: QuynhAnh2311
  Date: 04/02/2026
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
=======

>>>>>>> 76d67fe2690f98247086c9819d3f89f24d3eb127
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sach Ve Dat</title>
</head>
<body>
<<<<<<< HEAD
    <h3>Danh sach ve dat</h3>
    <table>
=======
<h3>Them ve dat</h3>
<form action="/ve-dat/them" method="post">
    Nguoi dat: <input type="text" name="nguoiDat"><br>
    So luong: <input type="number" name="soLuongVe"><br>
    ngay Chieu: <input type="date" pattern="yyyy-mm-dd" name="ngayChieu"><br>
    Da thanh toan:
    Chua <input type="radio" name="daThanhToan" value="false">
    Da <input type="radio" name="daThanhToan" value="true">
    Phim:
    <select name="idPhim">
        <c:forEach items="${listPhim}" var="p">
            <option value="${p.id}">
                ${p.tenPhim}
            </option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Add</button>
</form>

<hr>
<h3>Tìm kiem theo ten</h3>
<form action="/ve-dat/tim-kiem" method="get">
    Nguoi dat: <input type="text" name="nguoiDat">
    <button>Search</button>
</form>
<hr>
    <h3>Danh sach ve dat</h3>
    <table width="100%" border="1">
>>>>>>> 76d67fe2690f98247086c9819d3f89f24d3eb127
        <thead>
        <tr>
            <th>ID</th>
            <th>Nguoi dat</th>
            <th>So luong</th>
            <th>Ngay chieu</th>
            <th>Da thanh toan</th>
            <th>Ten phim</th>
            <th>The loai</th>
            <th>thoi luong</th>
            <th>Hanh dong</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listVeDat}" var="vd">
            <tr>
                <td>${vd.id}</td>
                <td>${vd.nguoiDat}</td>
                <td>${vd.soLuongVe}</td>
                <td>${vd.ngayChieu}</td>
                <td>${vd.daThanhToan == true ? "Da thanh toan": "Chua thanh toan"}</td>
                <td>${vd.phim.tenPhim}</td>
                <td>${vd.phim.theLoai}</td>
                <td>${vd.phim.thoiLuong}</td>
                <td>
                    <a href="/ve-dat/view-update?id=${vd.id}">Sua</a>
                    <a href="/ve-dat/xoa?id=${vd.id}">Xoa</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<<<<<<< HEAD
=======

<hr>
<a href="/ve-dat/phan-trang?page=0">First</a>
<a href="/ve-dat/phan-trang?page=${page - 1}">Prev</a>
<a href="/ve-dat/phan-trang?page=${page + 1}">Next</a>
<a href="/ve-dat/phan-trang?page=${page - 1}">Last</a>

>>>>>>> 76d67fe2690f98247086c9819d3f89f24d3eb127
</body>
</html>
