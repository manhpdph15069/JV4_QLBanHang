<%--
  Created by IntelliJ IDEA.
  User: manh0
  Date: 4/4/2022
  Time: 3:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="update?id=${cate.id}" method="post" class="container">
    <div class="mb-3">
        <label for="cate" class="form-label">Họ tên</label>
        <input type="text" class="form-control" value="${cate.name}" id="cate" name="name">
    </div>

    <button type="submit" class="btn btn-primary">Cập nhập</button>
    <button type="reset"  class="btn btn-primary">Clear</button>
</form>
