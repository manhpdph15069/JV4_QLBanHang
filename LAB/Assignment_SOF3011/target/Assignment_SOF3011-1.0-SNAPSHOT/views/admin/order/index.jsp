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
        <th>Mã hóa đơn</th>
        <th>Người đặt</th>
        <th>Ngày đặt</th>
        <th>Địa chỉ</th>
        <th>SĐT</th>
        <th>Sản phẩm</th>
        <th>Tổng tiền</th>
        <th>Trạng thái</th>
        <th colspan="2">Thao tác</th>
        </thead>
        <c:forEach items="${ ds }" var="od">
        <tbody class="text-center">
        <tr>
            <td>MHDS${ od.id }</td>
            <td>${ od.cusId.email }</td>
            <td><fmt:formatDate value="${ od.dateOrder }" pattern="dd/MM/yyyy"></fmt:formatDate></td>
            <td>${ od.cusId.address }</td>
            <td>${ od.cusId.phone }</td>
            <td>
                <c:forEach var="sp" items="${od.item}">
                    ${sp.proid.nameProduct}(SL: ${sp.quantity}),
                </c:forEach>
            </td>
            <td><fmt:formatNumber pattern="##,###" value="${od.total}"></fmt:formatNumber>VNĐ</td>
            <td>
                <c:choose>
                    <c:when test="${ od.status == 1 }">Xử lý</c:when>
                    <c:when test="${ od.status == 0 }">Đã hủy</c:when>
                    <c:when test="${ od.status == 2 }">Xác nhận</c:when>
                    <c:otherwise> - </c:otherwise>
                </c:choose>
            </td>

            <td>
                <a class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#xacNhan${ od.id }">Xác nhận</a>
            </td>
            <td>
                <a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#huyDon${ od.id }">Hủy</a>
            </td>

        </tr>
        </tbody>
            <%--            Modal Xác nhận--%>
        <div class="modal fade" id="xacNhan${ od.id }" tabindex="-1" aria-labelledby="xacNhanLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="xacNhanLabel">Xác nhận</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn chắc chắn muốn xác nhận đơn hàng của ${ od.cusId.email }??
                    </div>
                    <div class="modal-footer">
                        <a  class="btn btn-primary" href="update?id=${ od.id }">Xác
                            nhận</a>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
            <%--            Modal Hủy--%>
        <div class="modal fade" id="huyDon${ od.id }" tabindex="-1" aria-labelledby="huyDonLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="huyDonLabel">Hủy đơn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Bạn chắc chắn muốn hủy đơn hàng của ${ od.cusId.email }??
                    </div>
                    <div class="modal-footer">
                        <a class="btn btn-primary" href="delete?id=${ od.id }">Xác
                            nhận</a>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </table>

</c:if>


