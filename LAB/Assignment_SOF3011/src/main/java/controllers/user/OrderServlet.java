package controllers.user;

import dao.OrderDao;
import dao.OrderDetailDao;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderDetailDaoImpl;
import entitys._OrderDetails;
import entitys._Orders;
import entitys._Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet({
        "/client/add-order",
        "/admin/order/index",
        "/admin/order/edit",
        "/admin/order/update",
        "/admin/order/delete"
})
public class OrderServlet extends HttpServlet {
    OrderDao orderDao;
    OrderDetailDao orderDetailDao;

    public OrderServlet() {
        orderDao = new OrderDaoImpl();
        orderDetailDao = new OrderDetailDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add-order")){
            this.addOrder(request,response);
        }else if (uri.contains("admin/order/index")){
            this.index(request,response);
        }else if (uri.contains("admin/order/delete")){
            this.delete(request,response);
        }else if (uri.contains("admin/order/update")){
            this.update(request,response);
        }
        else {

        }
    }

    private void index(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        List<_Orders> orders = orderDao.findAll();
        request.setAttribute("ds",orders);
        request.setAttribute("view1", "/views/admin/order/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
    private void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String id = request.getParameter("id");
        _Orders orders = orderDao.findById(Integer.valueOf(id));
        if (orders!=null && orders.getStatus()!=0){
            orders.setStatus(0);
            orderDao.delete(orders);
            response.sendRedirect("index");
        }
    }
    private void update(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
            String id = request.getParameter("id");
            _Orders orders = orderDao.findById(Integer.valueOf(id));
            if (orders!=null && orders.getStatus()!=2){
                orders.setStatus(2);
                orderDao.update(orders);
                response.sendRedirect("index");
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    private void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            //lấy danh sách sản phẩm về
            Object obj = session.getAttribute("cart");
            if (obj!= null){
                //ép thành map
                Map<String, _OrderDetails> map = (Map<String, _OrderDetails>) obj;
                _Orders order = new _Orders();
                _Users user= (_Users) session.getAttribute("user");
                order.setCusId(user);
                //tạo để lấy id
                orderDao.create(order);

                long total =0;
                //duyệt từng sp 1 vào hóa đơn chi tiết
                for (Map.Entry<String, _OrderDetails> entry: map.entrySet()){
                        _OrderDetails orderDetails =entry.getValue();
                        orderDetails.setOrdid(order);
                        orderDetailDao.create(orderDetails);
                        total+= orderDetails.getQuantity() * orderDetails.getPrice();
                }
                order.setTotal(total);
                orderDao.update(order);
                session.removeAttribute("cart");
                session.setAttribute("order-success","Đặt đơn thành công vui lòng đợi xác nhận");
                response.sendRedirect("index");
            }else {
                session.setAttribute("order-fail","Đặt đơn không thành công vui lòng chọn sản phẩm muốn mua");
                response.sendRedirect("index");
            }
    }
}
