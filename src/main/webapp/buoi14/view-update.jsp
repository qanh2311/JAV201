<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 2/5/2026
  Time: 9:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Sua ve dat</h3>
<form action="/ve-dat/sua" method="post">
    ID: <input type="number" name="id" value="${vd.id}"readonly><br>
    Nguoi dat: <input type="text" name="nguoiDat" value="${vd.nguoiDat}"><br>
    So luong: <input type="number" name="soLuongVe" value= "${vd.soLuongVe}"><br>
    ngay Chieu: <input type="date" pattern="yyyy-mm-dd" name="ngayChieu" value="${vd.ngayChieu}" ><br>
    Da thanh toan:
    Chua <input type="radio" name="daThanhToan" value="false" ${!vd.daThanhToan ? 'checked' : ''}>
    Da <input type="radio" name="daThanhToan" value="true" ${vd.daThanhToan ? 'checked' : ''}>
    <br>
    Phim:
    <select name="idPhim">
        <c:forEach items="${listPhim}" var="p">
            <option value="${p.id}" ${vd.phim.id == p.id ? 'selected' : ''}>
                    ${p.tenPhim}
            </option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Sua</button>
</form>
</body>
</html>
