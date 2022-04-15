package controllers.admin;

import dao.CategoriesDao;
import dao.impl.CategoriesDaoImpl;
import entitys._Categories;
import entitys._Users;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/category/index",
        "/admin/category/create",
        "/admin/category/edit",
        "/admin/category/store",
        "/admin/category/update",
        "/admin/category/delete"
})
public class CategoryServlet extends HttpServlet {
    private CategoriesDao cateDao;


    public CategoryServlet() {
        cateDao = new CategoriesDaoImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("index")) {
//            this.index(request, response);
        } else if (uri.contains("admin/category/create")) {
            create(request, response);
        } else if (uri.contains("admin/category/delete")) {
            delete(request, response);
        } else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();

        if (uri.contains("admin/category/update")) {
            update(request, response);
        } else if (uri.contains("admin/category/store")) {
            store(request, response);
        } else if (uri.contains("admin/category/edit")) {
            edit(request, response);
        } else {
            //404
        }
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<_Categories> ds = this.cateDao.findAll();
        request.setAttribute("ds", ds);
        request.setAttribute("view1", "/views/admin/category/create.jsp");
        request.setAttribute("view2", "/views/admin/category/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<_Categories> ds = this.cateDao.findAll();
        request.setAttribute("cate", cateDao.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("view1", "/views/admin/category/edit.jsp");
//        request.setAttribute("view2", "/views/admin/category/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            _Categories entity = cateDao.findById(Integer.parseInt(request.getParameter("id")));
            entity.setStatus(0);
            cateDao.delete(entity);
            response.sendRedirect("index");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            _Categories old = cateDao.findById(Integer.parseInt(request.getParameter("id")));
            _Categories entity = new _Categories();
            BeanUtils.populate(entity, request.getParameterMap());
            entity.setDateCreate(old.getDateCreate());
            cateDao.update(entity);
            response.sendRedirect("create");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            request.setCharacterEncoding("UTF-8");
            _Categories entity = new _Categories();
            BeanUtils.populate(entity, request.getParameterMap());
            _Users u = (_Users) session.getAttribute("user");
            entity.setUser(u);
            if (!entity.getName().isEmpty()) {
                cateDao.create(entity);
                session.setAttribute("success", "Thêm thành công");
                response.sendRedirect("create");
            } else {
                session.setAttribute("message", "Không được để trống");
                response.sendRedirect("create");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
