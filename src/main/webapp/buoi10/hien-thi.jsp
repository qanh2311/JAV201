<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách</title>
    <script>
        function validateThem() {
            let soLuong = document.getElementById("soLuong").value;

            if (soLuong === "" || soLuong < 0) {
                alert("Số lượng không được âm!");
                return false;
            }
<<<<<<< HEAD

            alert("Thêm thành công");
            return true;
=======
>>>>>>> 76d67fe2690f98247086c9819d3f89f24d3eb127
        }
    </script>
</head>
<body>

<h3>Thêm đơn hàng</h3>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="/don-hang/them" method="post" onsubmit="return validateThem()">
    Khách hàng: <input name="khachHang" type="text"> <br>

    Số lượng:
    <input id="soLuong" name="soLuong" type="number"> <br>

    Trạng thái: <br>
    <input type="radio" name="daGiao" value="true"> Đã giao
    <input type="radio" name="daGiao" value="false"> Chưa giao
    <br>

    Món ăn:
    <select name="idMonAn">
        <c:forEach items="${listMonAn}" var="ma">
            <option value="${ma.id}">
                    ${ma.tenMon}
            </option>
        </c:forEach>
    </select>
    <br>

    <button type="submit">ADD</button>
</form>

<hr>

<h3>Tìm kiếm</h3>
<strong>Tìm kiếm bằng số lượng</strong>
<form action="/don-hang/tim-kiem-so-luong" method="get">
    Từ: <input type="number" name="min" min="0">
    Đến: <input type="number" name="max" min="0">
    <button>Tìm</button>
</form>
<strong>Tìm kiếm bằng trạng thái</strong>
<form action="/don-hang/tim-kiem-trang-thai" method="get">
    <input type="radio" name="daGiao" value="true"> Đã giao
    <input type="radio" name="daGiao" value="false"> Chưa giao
    <button>Tìm</button>
</form>

<a href="/don-hang/hien-thi">Huỷ tìm kiếm</a>

<hr>

<h3>Danh sách đơn hàng</h3>
<table width="100%" border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Khách hàng</th>
        <th>Số lượng</th>
        <th>Đã giao</th>
        <th>Tên món</th>
        <th>Giá tiền</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listDonHang}" var="dh">
        <tr>
            <td>${dh.id}</td>
            <td>${dh.khachHang}</td>
            <td>${dh.soLuong}</td>
            <td>${dh.daGiao ? "Đã giao" : "Chưa giao"}</td>
            <td>${dh.monAn.tenMon}</td>
            <td>${dh.monAn.giaTien}</td>
            <td>
                <a href="/don-hang/view-update?id=${dh.id}">Sửa</a>
                <a href="/don-hang/xoa?id=${dh.id}"
                   onclick="return confirm('Bạn chắc muốn xoá ${dh.id}?')">Xoá</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr>

<h3>Phân trang</h3>
<a href="/don-hang/phan-trang?page=1">First</a>
<a href="/don-hang/phan-trang?page=${page - 1}">Prev</a>
<a href="/don-hang/phan-trang?page=${page + 1}">Next</a>
<a href="/don-hang/phan-trang?page=${totalPages}">Last</a>
<br>
<span>Trang ${page} / ${totalPages}</span>

</body>
</html>
