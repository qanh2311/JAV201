
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/demoAjax/jquery-3.7.1.js" type="application/javascript"></script>
    <script>
        function getData() {
            $.ajax({
                    url: "/api/list",
                    method: "get",
                    dataType: "json",
                    success: function (data) {
                        document.getElementById("data").innerText = data.id + " " + data.name + " " + data.gender
                    },
                    error: function (error) {
                        document.getElementById("data").innerText = "Loi"
                    }
                }
            )
        }
    </script>
</head>
<body>
Thong tin sinh vien: <span id="data"></span>
<button onclick="getData()">Get thong tin sinh vien</button>
</body>
</html>
