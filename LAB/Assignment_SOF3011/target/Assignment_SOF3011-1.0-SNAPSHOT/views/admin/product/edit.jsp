<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: manh0
  Date: 4/4/2022
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="update?id=${proID.id}" method="post" enctype="multipart/form-data" class="container">
    <div class="mb-3">
        <label for="nameProduct" class="form-label">Tên sản phẩm<span class="text-danger top-100">*</span> </label>
        <input type="text" class="form-control" value="${proID.nameProduct}" id="nameProduct" name="nameProduct">
    </div>
    <div class="mb-3">
        <label for="quantilys" class="form-label">Số lượng<span class="text-danger top-100">*</span></label>
        <input type="number" class="form-control" id="quantilys" value="${proID.quantilys}" name="quantilys">
    </div>
    <div class="mb-3">
        <label for="price" class="form-label">Giá tiền<span class="text-danger top-100">*</span></label>
        <input type="text" class="form-control" id="price" value="${proID.price}" name="price">
        <!-- <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div> -->
    </div>
    <div class="mb-3">
        <label for="color" class="form-label">Màu sắc</label>
        <input type="text" class="form-control" id="color" value="${proID.color}" name="color">
    </div>
    <div class="mb-3">
        <label for="size" class="form-label">Size</label>
        <input type="text" class="form-control" id="size" value="${proID.size}" name="size">
    </div>
    <div class="mb-3">
        <label class="form-label">Hình ảnh</label>
        <input type="file" value=" " class="form-control" id="img" name="img">
    </div>
    <div class="mb-3">
        <label for="categoryId" class="form-label">Danh mục<span class="text-danger top-100">*</span></label>
        <select class="form-select"  name="categoryId" id="categoryId" aria-label="Default select example">
<%--            <option selected value="${proID.categoryId.id}" disabled>${proID.categoryId.name}</option>--%>
            <c:forEach var="ct" items="${dsCT}">
                <option value="${ct.id}" ${proID.categoryId.id ==ct.id ?"selected" : ""}>${ct.name}</option>
            </c:forEach>
        </select>
    </div>

    <button type="submit" class="btn btn-primary">Cập nhập</button>
    <button type="reset" class="btn btn-primary">Clear</button>
</form>