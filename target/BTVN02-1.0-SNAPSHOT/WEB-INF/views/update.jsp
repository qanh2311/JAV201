<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>

<h3>SỬA BÀN PHÍM</h3>

<form action="/ban-phim/sua" method="post">
    <input type="hidden" name="id" value="${bp.id}">

    Tên: <input name="tenBanPhim" value="${bp.tenBanPhim}"><br>

    Tình trạng:
    <input type="radio" name="tinhTrang" value="true"
    ${bp.tinhTrang ? "checked" : ""}> Hoạt động
    <input type="radio" name="tinhTrang" value="false"
    ${!bp.tinhTrang ? "checked" : ""}> Không hoạt động
    <br>

    Giá: <input name="gia" value="${bp.gia}"><br>
    Số lượng: <input name="soLuong" value="${bp.soLuong}"><br>

    <button>Save</button>
</form>

</body>
</html>