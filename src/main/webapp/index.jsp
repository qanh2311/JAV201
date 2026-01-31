<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="${pageContext.request.contextPath}/truong-hoc/hien-thi">Truong hoc</a> <br>
<a href="${pageContext.request.contextPath}/ban-phim/hien-thi">Ban phim</a> <br>
<a href="${pageContext.request.contextPath}/giang-vien/hien-thi">Giang vien</a> <br>
<a href="${pageContext.request.contextPath}/don-hang/hien-thi">Don hang</a> <br>
<a href="${pageContext.request.contextPath}/login">Login</a>
</body>
</html>