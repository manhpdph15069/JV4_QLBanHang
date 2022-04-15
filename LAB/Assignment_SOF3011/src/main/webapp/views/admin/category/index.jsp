<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<c:if test="${ empty ds }">
    <p class="alert alert-warning">Không có dữ liệu</p>
</c:if>
<h1 class="text-primary text-center">Danh sách</h1>
<c:if test="${ !empty ds }">
    <table class="table">
        <thead class="table-dark text-center">
        <th>Tên</th>
        <th>Người tạo</th>

        <th colspan="2">Thao tác</th>
        </thead>
        <tbody class="text-center">
        <c:forEach items="${ ds }" var="cate">
            <tr>
                <td>${ cate.name }</td>
                <td>${ cate.user.fullname }</td>
                <td>
                    <form method="post" action="edit">
                        <input name="id" type="hidden" value="${cate.id}"></input>
                        <button type="submit" class="btn btn-primary">Cập nhập</button>
                    </form>

                </td>
                <td>
                    <a data-bs-toggle="modal" data-bs-target="#c${ cate.id }"

                            class="btn btn-danger">
                        Xóa
                    </a>
                </td>
            </tr>
<%--            <div class="modal fade" id="c${cate.id }" tabindex="-1" aria-labelledby="exampleModalLabel"--%>
<%--                 aria-hidden="true">--%>
<%--                <div class="modal-dialog">--%>
<%--                    <div class="modal-content">--%>
<%--                        <div class="modal-header">--%>
<%--                            <h5 class="modal-title" id="exampleModalLabel">Thông Báo</h5>--%>
<%--                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                        </div>--%>
<%--                        <div class="modal-body">--%>
<%--                            Bạn chắc chắn muốn xóa ${ cate.name }???--%>
<%--                        </div>--%>
<%--                        <div class="modal-footer">--%>
<%--                            <form action="delete?id=${cate.id}" method="post">--%>
<%--                                <a type="submit"  class="btn btn-primary">Xác--%>
<%--                                    nhận</a>--%>
<%--                            </form>--%>

<%--                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
        </c:forEach>

        </tbody>
    </table>
</c:if>


