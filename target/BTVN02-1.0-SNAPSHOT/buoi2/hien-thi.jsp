<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lovep
  Date: 1/8/2026
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${truongHoc != null ? 'Sua Truong Hoc' : 'Them Truong Hoc Moi'}</h3>

<form action="${truongHoc != null ? '/truong-hoc/sua' : '/truong-hoc/them'}" method="post">
    <c:if test="${truongHoc != null}">
        <input type="hidden" name="id" value="${truongHoc.id}">
    </c:if>

    Ten truong: <input type="text" name="tenTruong" value="${truongHoc != null ? truongHoc.tenTruong : ''}" required>
    <br><br>
    Dia chi: <input type="text" name="diaChi" value="${truongHoc != null ? truongHoc.diaChi : ''}" required>
    <br><br>

    <button type="submit">${truongHoc != null ? 'Cap nhat' : 'Them moi'}</button>

    <c:if test="${truongHoc != null}">
        <button type="button" onclick="window.location.href='/truong-hoc/hien-thi'">Huy</button>
    </c:if>
</form>

<br>
Bang thong tin:
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Ten truong</th>
        <th>Dia chi</th>
        <th>Hanh dong</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listTruongHoc}" var="th">
        <tr>
            <td>${th.id}</td>
            <td>${th.tenTruong}</td>
            <td>${th.diaChi}</td>
            <td>
                <button onclick="window.location.href='/truong-hoc/view-update?id=${th.id}'">Sua</button>
                <button onclick="if(confirm('Ban co chac chan muon xoa?')) window.location.href='/truong-hoc/xoa?id=${th.id}'">Xoa</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>