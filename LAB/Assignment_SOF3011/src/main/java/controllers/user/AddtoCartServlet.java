package controllers.user;

import dao.CategoriesDao;
import dao.ProductDao;
import dao.UserDao;
import dao.impl.CategoriesDaoImpl;
import dao.impl.ProductDapImpl;
import dao.impl.UserDaoImpl;
import entitys.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({
        "/client/index",
        "/client/addtoCart",
        "/client/cart",
        "/client/findByCategory",
        "/client/delete-from-cart",
        "/client/history"
})
public class AddtoCartServlet extends HttpServlet {
    ProductDao productDao;
    CategoriesDao cateDao;
    UserDao userDao;
    public AddtoCartServlet() {
        productDao = new ProductDapImpl();
        cateDao = new CategoriesDaoImpl();
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("client/index")){
            this.index(request,response);
        }else if (uri.contains("client/addtoCart")){
            this.addToCart(request,response);
        }else if (uri.contains("client/cart")){
            this.Cart(request,response);
        }else if (uri.contains("client/findByCategory")){
            this.findByCategory(request,response);
        }else  if (uri.contains("delete-from-cart")){
            this.delete(request,response);
        }else  if (uri.contains("client/history")){
            this.history(request,response);
        }
        else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void history(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        _Users usersLogin = (_Users) session.getAttribute("user");
        if (usersLogin!=null){
        _Users users = userDao.findByEmail(usersLogin.getEmail());
            request.setAttribute("dsOrder",users.getOrders());
            request.getRequestDispatcher("/views/user/history.jsp").forward(request,response);
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("key");

        // gio hang co nhieu mat hang
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");// luu tam vao session

        if (obj != null) {
            Map<String, _OrderDetails> map = (Map<String, _OrderDetails>) obj;
            map.remove(key);
            // update lai vao session
            session.setAttribute("cart", map);
        }
        // chuyen ve trang gio hang
        response.sendRedirect("cart");
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<_Products> products = productDao.findAll();
        List<_Categories> categories = cateDao.findAll();
        request.setAttribute("listCT" ,categories);
        request.setAttribute("listProduct" , products);
//        request.setAttribute("view1","/views/user/list-product.jsp");
        request.getRequestDispatcher("/views/user/list-product.jsp").forward(request,response);
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pId = request.getParameter("proID");
        _Products product = productDao.findById(Integer.parseInt(pId));

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("cart");// luu tam vao session
        if (obj == null) {// tao moi
            // Tao mat hang
            _OrderDetails details = new _OrderDetails();
            details.setProid(product);
            details.setQuantity(1);
            details.setPrice(product.getPrice());
            // gio hang co nhieu mat hang

            Map<String, _OrderDetails> map = new HashMap<>();
            map.put(pId, details);// them mat hang vao ds

            session.setAttribute("cart", map);// luu tam vao session
        } else {
            Map<String, _OrderDetails> map = (Map<String, _OrderDetails>) obj;

            _OrderDetails details = map.get(pId);

            if (details == null) {
                details = new _OrderDetails();
                details.setProid(product);
                details.setQuantity(1);
                details.setPrice(product.getPrice());

                map.put(pId, details);
            } else {

                details.setQuantity(details.getQuantity() + 1);
            }

            session.setAttribute("cart", map);// luu tam vao session
        }

        response.sendRedirect("cart");
    }

    private void Cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        _Orders orders = (_Orders) session.getAttribute("order");
//        request.setAttribute("order",orders);
            request.getRequestDispatcher("/views/user/cart.jsp").forward(request,response);
    }
    private void findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      _Categories categories = cateDao.findById(Integer.valueOf(request.getParameter("id")));
      List<_Categories> categoriesList = cateDao.findAll();
        List<_Products> products =categories.getProducts();
        request.setAttribute("listCT" ,categoriesList);
        request.setAttribute("listProduct" , products);
//        request.setAttribute("view1","/views/user/list-product.jsp");
        request.getRequestDispatcher("/views/user/list-product.jsp").forward(request,response);
    }

}
