package controllers.admin;

import dao.CategoriesDao;
import dao.ProductDao;
import dao.impl.CategoriesDaoImpl;
import dao.impl.ProductDapImpl;
import entitys._Categories;
import entitys._Products;
import entitys._Users;
import org.apache.commons.beanutils.BeanUtils;
import utils.FileUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/product/index",
        "/admin/product/create",
        "/admin/product/edit",
        "/admin/product/store",
        "/admin/product/update",
        "/admin/product/delete"
})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, //1MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 11
)
public class ProductServlet extends HttpServlet {
    ProductDao proDao;
    CategoriesDao categoriesDao;
    double PRODUCT_MAX_SIZE = 3;

    public ProductServlet() {
        proDao = new ProductDapImpl();
        categoriesDao = new CategoriesDaoImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
//            this.index(request, response);
        } else if (uri.contains("admin/product/create")) {
            create(request, response);
        }else if (uri.contains("admin/product/delete")) {
            delete(request, response);
        }
        else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();

        if (uri.contains("admin/product/update")) {
            update(request, response);
        } else if (uri.contains("admin/product/store")) {
            store(request, response);
        } else if (uri.contains("admin/product/edit")) {
            edit(request, response);
        }
        else {
            //404
        }
    }

//    private void index(
//            HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<_Users> ds = this.userDAO.findAll();
//        request.setAttribute("ds", ds);
//
//        request.setAttribute("view1", "/views/admin/users/create.jsp");
//        request.setAttribute("view2", "/views/admin/users/index.jsp");
//        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
//
//    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<_Products> countPro = proDao.findAll();
        int maxPage = (int) Math.ceil(countPro.size() / (double) PRODUCT_MAX_SIZE);
        request.setAttribute("maxPage", maxPage);
        List<_Products> products;
        String pageNumber = request.getParameter("page");
        if (pageNumber == null || Integer.parseInt(pageNumber) > maxPage) {
            products = proDao.findAll(1, (int) PRODUCT_MAX_SIZE);
            request.setAttribute("currentPage", 1);
        } else {
            products = proDao.findAll(Integer.parseInt(pageNumber), (int) PRODUCT_MAX_SIZE);
            request.setAttribute("currentPage", Integer.valueOf(pageNumber));

        }


        List<_Categories> dsCT = categoriesDao.findAll();
        request.setAttribute("ds", countPro);
        request.setAttribute("dsCT", dsCT);
        request.setAttribute("view1", "/views/admin/product/create.jsp");
        request.setAttribute("view2", "/views/admin/product/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<_Products> ds = proDao.findAll();
        request.setAttribute("ds", ds);
        List<_Categories> dsCT = categoriesDao.findAll();
        request.setAttribute("dsCT", dsCT);
        request.setAttribute("proID", proDao.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("view1", "/views/admin/product/edit.jsp");
//        request.setAttribute("view2", "/views/admin/product/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            _Products entity = proDao.findById(Integer.parseInt(request.getParameter("id")));
            entity.setStatus(0);
            proDao.delete(entity);
            response.sendRedirect("create");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        _Products old = proDao.findById(Integer.parseInt(request.getParameter("id")));
        HttpSession session = request.getSession();
        String message = "";
        try {
            String idCate = request.getParameter("categoryId");
            _Users users = (_Users) session.getAttribute("user");

            _Products entity = new _Products();
            entity.setId(old.getId());
//            BeanUtils.populate(entity, request.getParameterMap());
            entity.setNameProduct(request.getParameter("nameProduct"));
            entity.setQuantilys(Integer.parseInt(request.getParameter("quantilys")));
            entity.setPrice(Double.parseDouble(request.getParameter("price")));
            entity.setColor(request.getParameter("color"));
            entity.setSize(request.getParameter("size"));
            entity.setDateCreate(old.getDateCreate());
            entity.setUsers(old.getUsers());
            File file = FileUtil.saveFileUpload("product", request.getPart("img"));

        if (file.getPath().equals("D:\\LEARN\\ServletJSP\\LAB\\Assignment_SOF3011\\src\\main\\webapp\\image\\product")){
            entity.setImg(old.getImg());
        }else {
                entity.setImg("../image/product/" + file.getName());
        }



            if (entity.getNameProduct().isEmpty() || entity.getQuantilys() == null || entity.getPrice() == null || entity.getColor().isEmpty() || entity.getSize().isEmpty() || idCate == "0") {
                message = "Không được để trống";
                session.setAttribute("message", message);
                response.sendRedirect("create");
            } else {


                if (entity.getQuantilys() > 0 && entity.getQuantilys() <= 1000) {
                    if (entity.getPrice() > 0) {
                        _Categories categories = categoriesDao.findById(Integer.valueOf(idCate));
                        entity.setCategoryId(categories);
                        proDao.update(entity);
                        session.setAttribute("success", "Cập nhập thành công");
                        response.sendRedirect("create");
                    } else {
                        message = "Giá phải lớn hơn 0";
                        session.setAttribute("message", message);
                        response.sendRedirect("create");
                    }
                } else {
                    message = "Số lượng không hợp lệ 0 -> 1000";
                    session.setAttribute("message", message);
                    response.sendRedirect("create");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String message = "";
        try {
            String idCate = request.getParameter("categoryId");
            _Users users = (_Users) session.getAttribute("user");

            _Products entity = new _Products();
//            BeanUtils.populate(entity, request.getParameterMap());
            entity.setNameProduct(request.getParameter("nameProduct"));
            entity.setQuantilys(Integer.parseInt(request.getParameter("quantilys")));
            entity.setPrice(Double.parseDouble(request.getParameter("price")));
            entity.setColor(request.getParameter("color"));
            entity.setSize(request.getParameter("size"));
            entity.setUsers(users);

            String image = request.getParameter("img");

            if (entity.getNameProduct().isEmpty() || entity.getQuantilys() == null || entity.getPrice() == null || entity.getColor().isEmpty() || entity.getSize().isEmpty() || idCate == "0" || image == " ") {
                message = "Không được để trống";
                session.setAttribute("message", message);
                response.sendRedirect("create");
            } else {


                if (entity.getQuantilys() > 0 && entity.getQuantilys() <= 1000) {
                    if (entity.getPrice() > 0) {
                        File file = FileUtil.saveFileUpload("product", request.getPart("img"));
                        entity.setImg("../image/product/" + file.getName());
                        _Categories categories = categoriesDao.findById(Integer.valueOf(idCate));
                        entity.setCategoryId(categories);
                        proDao.create(entity);
                        session.setAttribute("success", "Thêm mới thành công");
                        response.sendRedirect("create");
                    } else {
                        message = "Giá phải lớn hơn 0";
                        session.setAttribute("message", message);
                        response.sendRedirect("create");
                    }
                } else {
                    message = "Số lượng không hợp lệ 0 -> 1000";
                    session.setAttribute("message", message);
                    response.sendRedirect("create");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
