package controllers;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        request.setAttribute("headerL", "../common/header.jsp");
//        request.setAttribute("footerL", "../common/footer.jsp");
//        request.setAttribute("view1", "/views/admin/users/create.jsp");
//        request.setAttribute("view2", "/views/admin/users/index.jsp");

        request.getRequestDispatcher("views/user/home.jsp").forward(request, response);
    }
}