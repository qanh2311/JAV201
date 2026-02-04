<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản Lý Giảng Viên</title>
</head>
<body>
<h3>${giangVien != null ? "Sửa" : "Thêm"} Giảng Viên:</h3>
<form action="${pageContext.request.contextPath}/giang-vien/${giangVien != null ? 'sua' : 'them'}" method="post">
    <c:if test="${giangVien != null}">
        <input type="hidden" name="id" value="${giangVien.id}">
    </c:if>

    Tên giảng viên: <input type="text" name="ten_giang_vien" value="${giangVien.tenGiangVien}" required> <br>
    Tuổi: <input type="number" name="tuoi" value="${giangVien.tuoi}" required> <br>
    Giới tính:
    <input type="radio" name="gioi_tinh" value="Nam" ${giangVien == null || !giangVien.gioiTinh ? 'checked' : ''}> Nam
    <input type="radio" name="gioi_tinh" value="Nữ" ${giangVien.gioiTinh ? 'checked' : ''}> Nữ <br>

    Trường học:
    <select name="truong_id" required>
        <option value="">-- Chọn trường --</option>
        <c:forEach items="${ListTruongHoc}" var="th">
            <option value="${th.id}" ${giangVien.truongHoc.id == th.id ? 'selected' : ''}>
                    ${th.id}
            </option>
        </c:forEach>
    </select> <br>

    <button type="submit">${giangVien != null ? "Cập nhật" : "Thêm mới"}</button>
    <c:if test="${giangVien != null}">
        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/giang-vien/hien-thi'">Hủy</button>
    </c:if>
</form>

<hr>
<h3>Tìm kiếm tên giảng viên:</h3>
<form action="/giang-vien/tim-kiem" method="get">
    Ten giang vien: <input type="text" name="ten">
    <button>Search</button>
</form>
<hr>
<h3>Danh sách Giảng viên:</h3>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên giảng viên</th>
        <th>Tuổi</th>
        <th>Giới tính</th>
        <th>Tên Trường</th>
        <th>Địa chỉ</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ListGiangVien}" var="gv">
        <tr>
            <td>${gv.id}</td>
            <td>${gv.tenGiangVien}</td>
            <td>${gv.tuoi}</td>
            <td>${gv.gioiTinh ? "Nữ" : "Nam"}</td>
            <td>${gv.truongHoc.tenTruong}</td>
            <td>${gv.truongHoc.diaChi}</td>
            <td>
                <a href="${pageContext.request.contextPath}/giang-vien/view-update?id=${gv.id}">Sửa</a> |
                <a href="${pageContext.request.contextPath}/giang-vien/xoa?id=${gv.id}"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa giảng viên ${gv.tenGiangVien}?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/giang-vien/phan-trang?page=0">First</a>
<a href="/giang-vien/phan-trang?page=${page - 1}">Prev</a>
<a href="/giang-vien/phan-trang?page=${page + 1}">Next</a>
<a href="/giang-vien/phan-trang?page=${page - 1}">Last</a>
</body>
</html>