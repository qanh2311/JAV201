<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cập nhật đơn hàng</title>

    <script>
        function validateUpdate() {
            let soLuong = document.getElementById("soLuong").value;

            if (soLuong === "" || soLuong < 0) {
                alert("Số lượng không được âm!");
                return false;
            }
        }
    </script>
</head>
<body>
<h3>Sửa đơn hàng</h3>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<form action="/don-hang/sua" method="post" onsubmit="return validateUpdate()">

    ID: <input name="id" type="text" readonly value="${dh.id}"> <br>

    Khách hàng:
    <input name="khachHang" type="text" value="${dh.khachHang}"> <br>

    Số lượng:
    <input id="soLuong" name="soLuong" type="number" value="${dh.soLuong}"> <br>

    Trạng thái: <br>
    <input type="radio" name="daGiao" value="true" ${dh.daGiao ? 'checked' : ''}> Đã giao
    <input type="radio" name="daGiao" value="false" ${!dh.daGiao ? 'checked' : ''}> Chưa giao
    <br>

    Món ăn:
    <select name="idMonAn">
        <c:forEach items="${listMonAn}" var="ma">
            <option value="${ma.id}" ${dh.monAn.id == ma.id ? 'selected' : ''}>
                    ${ma.tenMon}
            </option>
        </c:forEach>
    </select>
    <br>

    <button type="submit">Cập nhật</button>
</form>
</body>
</html>
