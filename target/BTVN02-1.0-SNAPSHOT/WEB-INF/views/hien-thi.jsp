<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>

<h2>THÊM BÀN PHÍM</h2>
<form action="/ban-phim/them" method="post">
    Tên: <input name="tenBanPhim"><br>

    Tình trạng:
    <input type="radio" name="tinhTrang" value="true"> Hoạt động
    <input type="radio" name="tinhTrang" value="false"> Không hoạt động
    <br>

    Giá: <input name="gia"><br>
    Số lượng: <input name="soLuong"><br>

    <button>Thêm</button>
</form>

<br>
<hr>
<br>


<h2>DANH SÁCH BÀN PHÍM</h2>
<table>
    <tr>
        <th>ID</th>
        <th>TÊN</th>
        <th>TRẠNG THÁI</th>
        <th>GIÁ</th>
        <th>SỐ LƯỢNG</th>
        <th>HÀNH ĐỘNG</th>
    </tr>

    <c:forEach items="${list}" var="bp">
        <tr>
            <td>${bp.id}</td>
            <td>${bp.tenBanPhim}</td>
            <td>${bp.tinhTrang ? "Hoạt động" : "Không hoạt động"}</td>
            <td>${bp.gia}</td>
            <td>${bp.soLuong}</td>
            <td>
                <a href="/ban-phim/view-update?id=${bp.id}">Sửa</a>
                <a href="/ban-phim/xoa?id=${bp.id}">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>