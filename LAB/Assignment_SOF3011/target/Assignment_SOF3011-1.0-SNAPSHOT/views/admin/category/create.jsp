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
        <label for="name" class="form-label">Tên<span class="text-danger top-100">*</span></label>
        <input type="text" class="form-control" id="name" name="name">
    </div>
    <button type="submit" class="btn btn-primary">Thêm mới</button>
    <button type="reset"  class="btn btn-primary">Clear</button>
</form>