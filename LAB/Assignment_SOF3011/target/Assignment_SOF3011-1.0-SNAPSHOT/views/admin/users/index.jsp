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
        <th>Họ tên</th>
        <th>Email</th>
        <th>Giới tính</th>
        <th>Địa chỉ</th>
        <th>SĐT</th>
        <th>Vai trò</th>
        <th>Ngày tạo</th>
        <th colspan="2">Thao tác</th>
        </thead>
        <tbody class="text-center">
        <c:forEach items="${ ds }" var="user">
            <tr>
                <td>${ user.fullname }</td>
                <td>${ user.email }</td>
                <td>
                    <c:choose>
                        <c:when test="${ user.gender == 1 }">Nam</c:when>
                        <c:when test="${ user.gender == 0 }">Nữ</c:when>
                        <c:otherwise> - </c:otherwise>
                    </c:choose>
                </td>
                <td>${ user.address }</td>
                <td>${ user.phone }</td>
                <td>
                    <c:choose>
                        <c:when test="${ user.isAdmin == 1 }">Quản lý</c:when>
                        <c:when test="${ user.isAdmin == 0 }">Người dùng</c:when>
                        <c:otherwise> - </c:otherwise>
                    </c:choose>
                </td>
                <td><fmt:formatDate value="${ user.dateCreated }" pattern="dd/MM/yyyy"></fmt:formatDate> </td>

                <td>
                    <form method="post" action="edit">
                        <input name="id" type="hidden" value="${user.id}"></input>
                        <button type="submit" class="btn btn-primary">Chọn</button>
                    </form>

                </td>
<%--                <td>--%>
<%--                    <a--%>
<%--                            href="/delete?id=${ user.id }"--%>
<%--                            class="btn btn-danger">--%>
<%--                        Xóa--%>
<%--                    </a>--%>
<%--                </td>--%>
            </tr>

        </c:forEach>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Thông Báo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn chắc chắn muốn xóa???
                    </div>
                    <div class="modal-footer">
                        <a type="button" id="yesD" class="btn btn-primary">Xác
                            nhận</a>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
        </tbody>
    </table>
</c:if>


