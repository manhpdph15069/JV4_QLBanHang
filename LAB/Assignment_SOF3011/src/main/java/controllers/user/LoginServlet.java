package controllers.user;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entitys._Users;
import utils.EncryptUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserDao userDao;

    public LoginServlet() {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/user/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
       this.login(request,response,session);

    }
    private void login (HttpServletRequest request,HttpServletResponse response,HttpSession session) throws ServletException,IOException{
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        _Users users = userDao.findByEmail(email);
        boolean check = EncryptUtil.check(pass,users.getPass());
        if (check ==true){
            if (users.getIsAdmin() ==1){
                session.setAttribute("user",users);
                response.sendRedirect("admin/users/create");
            }else {
                session.setAttribute("user",users);
                //Người dùng
                response.sendRedirect("client/index");
            }
        }else {
            session.setAttribute("message","Email hoặc mật khẩu không chính xác");
            request.getRequestDispatcher("views/user/login.jsp").forward(request,response);
        }
    }
}
