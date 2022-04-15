<%--
  Created by IntelliJ IDEA.
  User: manh0
  Date: 4/1/2022
  Time: 3:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="row">
    <div class="col-12 img-thumbnail border-0 ">
        <img src="https://caodang.fpt.edu.vn/wp-content/uploads/Banner1-1.png" style="width: 100%;" alt="">
    </div>
</header>
<!-- End Header -->
<!-- Start Menu-->
<nav class="navbar navbar-light bg-light fixed-top">
    <div class="container-fluid">
        <div class="row">

        </div>
        <a class="navbar-brand ms-3 mt-2" href="#">&nbsp; HNAM</a>

<%--        <div class="row text-center " style="height: 90px;">--%>
<%--            <a class="navbar-brand text-black fixed-top bg-secondary bg-opacity-10" href="">Tất cả sản phẩm</a>--%>
<%--        </div>--%>
        <div class="mt-4 mx-auto text-center">

            <a class="navbar-brand text-black" href="/Assignment_SOF3011/admin/users/create">Tài khoản</a>
            <a class="navbar-brand text-black" href="/Assignment_SOF3011/admin/category/create">&nbsp;Danh mục</a>
            <a class="navbar-brand text-black" href="/Assignment_SOF3011/admin/product/create">Sản phẩm</a>
            <a class="navbar-brand text-black" href="/Assignment_SOF3011/admin/order/index">Đơn hàng</a>
        </div>
        <button class="navbar-toggler mt-3" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                aria-controls="offcanvasNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasNavbarLabel">HNAM</h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"
                        aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="offcanvasNavbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Tài khoản
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="offcanvasNavbarDropdown">
                            <li><a class="dropdown-item" href="/Assignment_SOF3011/login">Đăng nhập</a></li>
                            <li><a class="dropdown-item" href="#">Đăng ký</a></li>
                            <li><a class="dropdown-item" href="#">Quên mật khẩu</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item"  data-bs-toggle="modal" data-bs-target="#logout">Đăng
                                xuất</a></li>

                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="danhmucDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Danh mục
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="danhmucDropdown">
                            <li><a class="dropdown-item" href="#">Áo</a></li>
                            <li><a class="dropdown-item" href="#">Quần</a></li>
                            <li><a class="dropdown-item" href="#">Dép</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Tất cả</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <%--                        <a class="nav-link active" aria-current="page" href="#">Giỏ hàng</a>--%>
                        <a type="button" href="#" class="nav-link active position-relative">
                            Giỏ hàng
                            <span class="position-absolute  translate-middle badge rounded-pill bg-danger">
                                5
                            <span class="visually-hidden">unread messages</span>
                        </span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Lịch sử</a>
                    </li>
                </ul>

            </div>
        </div>
    </div>

</nav>
<!-- End Menu -->
<div class="modal" id="logout" tabindex="-1" aria-labelledby="logoutLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logoutLabel">Đăng xuất</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn chắc chắn muốn đăng xuất ?
            </div>
            <div class="modal-footer">
                <a  class="btn btn-primary" href="/Assignment_SOF3011/LogOut">Yes</a>
                <button type="button" data-bs-dismiss="modal" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
            </div>
        </div>
    </div>
</div>


