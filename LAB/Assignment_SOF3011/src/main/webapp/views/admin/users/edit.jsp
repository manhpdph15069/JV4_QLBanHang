<%--
  Created by IntelliJ IDEA.
  User: manh0
  Date: 4/4/2022
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<h1 class="text-primary text-center">Cập nhập</h1>
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
<form action="update?id=${userID.id}" method="post" class="container">
    <div class="mb-3">
        <label for="fullname" class="form-label">Họ tên</label>
        <input type="text" class="form-control" value="${userID.fullname}" id="fullname" name="fullname">
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" value="${userID.email}" name="email">
        <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
    </div>
    <div class="mb-3">
        <label for="sdt" class="form-label">SĐT</label>
        <input type="text" class="form-control" value="${userID.phone}" id="sdt" name="phone">
    </div>
    <div class="mb-3">
        <label for="address" class="form-label">Địa chỉ</label>
        <input type="text" class="form-control" id="address" value="${userID.address}" name="address">
    </div>
    <div class="col-6 p-2">
        <div class="row">
            <label>Giới tính</label>

        </div>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" value="1" name="gender" id="flexRadioGender1" ${userID.gender==1 ? "checked":""}>
            <label class="form-check-label" for="flexRadioGender1">
                Nam
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" value="0" type="radio" name="gender" id="flexRadioGender2" ${userID.gender==0 ? "checked":""}>
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
            <input class="form-check-input" type="radio" value="0" ${userID.isAdmin==0 ? "checked":""} name="isAdmin" id="flexRadioIsAdmin1" checked>
            <label class="form-check-label" for="flexRadioIsAdmin1">
                Người dùng
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" value="1" ${userID.isAdmin==1 ? "checked":""} name="isAdmin" id="flexRadioIsAdmin2">
            <label class="form-check-label" for="flexRadioIsAdmin2">
                Quản lý
            </label>
        </div>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhập</button>
        <a type="submit" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete">Xóa</a>
    <button type="reset"  class="btn btn-secondary">Clear</button>


</form>
<form method="get" action="delete">


    <!-- Modal -->
    <div class="modal fade" id="delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <a type="submit" class="btn btn-primary" href="delete?id=${userID.id}">Save changes</a>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</form>
