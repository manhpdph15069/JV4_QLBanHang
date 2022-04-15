<%--
  Created by IntelliJ IDEA.
  User: manh0
  Date: 4/4/2022
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="text-primary text-center">Thêm mới</h1>
<c:if test="${!empty sessionScope.message}">
   <div class="alert alert-danger">
           ${sessionScope.message}
   </div>
    <c:remove var="message"></c:remove>
</c:if>
<c:if test="${!empty sessionScope.success}">
    <div class="alert alert-success">
            ${sessionScope.success}
    </div>
    <c:remove var="success"></c:remove>
</c:if>
<form action="store" method="post" class="container">
    <div class="mb-3">
        <label for="fullname" class="form-label">Họ tên<span class="text-danger top-100">*</span></label>
        <input type="text" class="form-control" id="fullname" name="fullname">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email<span class="text-danger top-100">*</span></label>
        <input type="email" class="form-control" id="email" name="email">
        <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
    </div>
    <div class="mb-3">
        <label for="pass" class="form-label">Mật khẩu<span class="text-danger top-100">*</span></label>
        <input type="password" class="form-control" id="pass" name="pass">
    </div>
    <div class="mb-3">
        <label for="pass2" class="form-label">Xác nhận<span class="text-danger top-100">*</span></label>
        <input type="password" class="form-control" id="pass2" name="pass2">
    </div>
    <div class="mb-3">
        <label for="sdt" class="form-label">SĐT</label>
        <input type="text" class="form-control" id="sdt" name="phone">
    </div>
    <div class="mb-3">
        <label for="address" class="form-label">Địa chỉ</label>
        <input type="text" class="form-control" id="address" name="address">
    </div>
    <div class="col-6 p-2">
        <div class="row">
            <label>Giới tính</label>

        </div>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" value="1" name="gender" id="flexRadioGender1" checked>
            <label class="form-check-label" for="flexRadioGender1">
                Nam
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" value="0" type="radio" name="gender" id="flexRadioGender2">
            <label class="form-check-label" for="flexRadioGender2">
                Nữ
            </label>
        </div>
    </div>

    <div class="col-6 p-2">
        <div class="row">
            <label>Vai trò</label>

        </div>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" value="0" name="isAdmin" id="flexRadioIsAdmin1" checked>
            <label class="form-check-label" for="flexRadioIsAdmin1">
                Người dùng
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" value="1" name="isAdmin" id="flexRadioIsAdmin2">
            <label class="form-check-label" for="flexRadioIsAdmin2">
                Quản lý
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Đăng ký</button>
    <button type="reset"  class="btn btn-primary">Clear</button>
</form>