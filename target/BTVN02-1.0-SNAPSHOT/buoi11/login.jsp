
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}
<form action="/login" method="post">
    username: <input type="text" name="username"> <br>
    password: <input type="text" name="password"> <br>
    <button>Login</button>
</form>
</body>
</html>