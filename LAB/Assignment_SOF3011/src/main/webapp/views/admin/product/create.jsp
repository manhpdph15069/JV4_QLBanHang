<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
<form action="store" method="post" class="container" enctype="multipart/form-data">
    <div class="mb-3">
        <label for="nameProduct" class="form-label">Tên sản phẩm<span class="text-danger top-100">*</span> </label>
        <input type="text" class="form-control" id="nameProduct" name="nameProduct">
    </div>
    <div class="mb-3">
        <label for="quantilys" class="form-label">Số lượng<span class="text-danger top-100">*</span></label>
        <input type="number" value="0" class="form-control" id="quantilys" name="quantilys">
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Giá tiền<span class="text-danger top-100">*</span></label>
        <input type="text" value="0" class="form-control" id="price" name="price">
        <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
    </div>
    <div class="mb-3">
        <label for="color" class="form-label">Màu sắc</label>
        <input type="text" class="form-control" id="color" name="color">
    </div>
    <div class="mb-3">
        <label for="size" class="form-label">Size</label>
        <input type="text" class="form-control" id="size" name="size">
    </div>
    <div class="mb-3">
        <label for="img" class="form-label">Hình ảnh</label>
        <input type="file" value=" " class="form-control" id="img" name="img">
    </div>
    <div class="mb-3">
        <label for="categoryId" class="form-label">Danh mục<span class="text-danger top-100">*</span></label>
        <select class="form-select" name="categoryId" id="categoryId" aria-label="Default select example">
            <option value="0" selected disabled>Chọn...</option>
            <c:forEach items="${dsCT}" var="ct">
                <option value="${ct.id}">${ct.name}</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Thêm mới</button>
    <button type="reset" class="btn btn-primary">Clear</button>
</form>