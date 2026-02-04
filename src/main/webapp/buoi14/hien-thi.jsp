<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: QuynhAnh2311
  Date: 04/02/2026
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sach Ve Dat</title>
</head>
<body>
    <h3>Danh sach ve dat</h3>
    <table>
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
</body>
</html>
