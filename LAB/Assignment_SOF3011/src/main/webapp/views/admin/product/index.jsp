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
        <th>Tên sản phẩm</th>
        <th>Số lượng</th>
        <th>Giá tiền</th>
        <th>Màu sắc</th>
        <th>Size</th>
        <th>Danh mục</th>
        <th>Ngày tạo</th>
        <th>Người tạo</th>
        <th>Ảnh</th>
        <th colspan="2">Thao tác</th>
        </thead>
        <tbody class="text-center">
        <c:forEach items="${ ds }" var="pro">
            <tr>
                <td>${ pro.nameProduct }</td>
                <td>${ pro.quantilys }</td>
                <td><fmt:formatNumber value="${ pro.price }" pattern="#,###"></fmt:formatNumber></td>
                <td>${ pro.color }</td>
                <td>${ pro.size }</td>
                <td>${ pro.categoryId.name }</td>

                <td><fmt:formatDate value="${ pro.dateCreate }" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                <td>${ pro.users.fullname }</td>
                <td>
                    <img src="../${pro.img}" style="height: 80px;width: 80px" class="rounded mx-auto d-block" alt="...">
                </td>
                <td>
                    <form method="post" action="edit">
                        <input name="id" type="hidden" value="${pro.id}"></input>
                        <button type="submit" class="btn btn-primary">Cập nhập</button>
                    </form>

                </td>
                <td>
                    <a
                            data-bs-toggle="modal" data-bs-target="#p${ pro.id }"
                            class="btn btn-danger">
                        Xóa
                    </a>
                </td>
            </tr>
            <div class="modal fade" id="p${pro.id}" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Thông Báo</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Bạn chắc chắn muốn xóa sản phẩm ${pro.nameProduct}???
                        </div>
                        <div class="modal-footer">

                                <a class="btn btn-primary" href="delete?id=${pro.id}"> Xác
                                    nhận</a>

                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        </tbody>
    </table>
</c:if>


