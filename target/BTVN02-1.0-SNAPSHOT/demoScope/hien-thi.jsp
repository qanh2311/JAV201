<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    pageContext.setAttribute("pageMessage", "From page scope");
%>
<body>
<%-- Request scope--%>
    Message lay tu request scope: ${demoRequestScope}
<br>
<%-- Session scope--%>
    Message lay tu session scope: ${sessionScope.demoSessionScope}
<br>
<%-- Application scope--%>
    Message lay tu application scope: ${applicationScope.demoApplicationScope}
<br>
    Message lay tu application scope: ${pageMessage}


</body>
</html>