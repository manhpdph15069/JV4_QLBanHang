package controllers.admin;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entitys._Users;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import utils.EncryptUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(
        {
                "/admin/users/index",
                "/admin/users/create",
                "/admin/users/edit",
                "/admin/users/store",
                "/admin/users/update",
                "/admin/users/delete"
        }
)
public class UserServlet extends HttpServlet {
    public static final Logger logger = Logger.getLogger(UserServlet.class);
    UserDao userDAO;
//    List<_Users> ds;

    public UserServlet() {
        userDAO = new UserDaoImpl();
//        this.ds  = this.userDAO.findAll();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();
        if (uri.contains("admin/users/index")) {
//            this.index(request, response);
        } else if (uri.contains("admin/users/create")) {
            create(request, response);
        } else if (uri.contains("admin/users/delete")) {
            delete(request, response);
        } else {
            //404
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uri = request.getRequestURI();

        if (uri.contains("admin/users/update")) {
            update(request, response);
        } else if (uri.contains("admin/users/store")) {
            store(request, response);
        } else if (uri.contains("admin/users/edit")) {
            edit(request, response);
        } else {
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
        List<_Users> ds = this.userDAO.findAll();
        request.setAttribute("ds", ds);
        request.setAttribute("view1", "/views/admin/users/create.jsp");
        request.setAttribute("view2", "/views/admin/users/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<_Users> ds = this.userDAO.findAll();
        request.setAttribute("ds", ds);
        request.setAttribute("userID", userDAO.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("view1", "/views/admin/users/edit.jsp");
//        request.setAttribute("view2", "/views/admin/users/index.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        _Users nguoiTao = (_Users) session.getAttribute("user");
        try {

            _Users entity = userDAO.findById(Integer.parseInt(request.getParameter("id")));
            entity.setIsActive((byte) 0);
            userDAO.delete(entity);
            response.sendRedirect("create");
            logger.debug("Khoi tao luc :"+ new Date() +" , Nguoi xoa: "+nguoiTao.getFullname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        _Users nguoiTao = (_Users) session.getAttribute("user");
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String sdt = "^0[0-9]{1}\\d{8}$";
        Pattern pattern = Pattern.compile(sdt);

        String message = "";
        try {
            request.setCharacterEncoding("UTF-8");
            _Users old = userDAO.findById(Integer.parseInt(request.getParameter("id")));
            _Users entity = new _Users();
            BeanUtils.populate(entity, request.getParameterMap());
            Matcher matcher = pattern.matcher(entity.getPhone());

            if (entity.getFullname().isEmpty() || entity.getAddress().isEmpty() || entity.getEmail().isEmpty() || entity.getPhone().isEmpty()) {
                message = "Không được để trống";
                session.setAttribute("message", message);
                response.sendRedirect("create");
            } else {
                if (!EMAIL_PATTERN.matches(entity.getEmail())) {
                    if (matcher.find()) {
                        entity.setPass(old.getPass());
                        entity.setDateCreated(old.getDateCreated());
                        userDAO.update(entity);
                        String succs = "Cập nhập mới thành công";
                        session.setAttribute("success", succs);
                        response.sendRedirect("create");
                        logger.debug("Khoi tao luc :"+ new Date() +" , Nguoi cap nhap: "+nguoiTao.getFullname());
                    } else {
                        message = "Số điện thoại không đúng định dạng";
                        session.setAttribute("message", message);
                        response.sendRedirect("create");
                    }
                } else {
                    message = "Email không đúng định dạng";
                    session.setAttribute("message", message);
                    response.sendRedirect("create");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        _Users nguoiTao = (_Users) session.getAttribute("user");
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String sdt = "^0[0-9]{1}\\d{8}$";
        Pattern pattern = Pattern.compile(sdt);

        String message = "";
        try {
            request.setCharacterEncoding("UTF-8");

            _Users entity = new _Users();
            BeanUtils.populate(entity, request.getParameterMap());
            Matcher matcher = pattern.matcher(entity.getPhone());
            String pass2 = request.getParameter("pass2");

            if (entity.getFullname().isEmpty() || entity.getAddress().isEmpty() || entity.getEmail().isEmpty() || entity.getPass().isEmpty() || entity.getPhone().isEmpty()) {
                message = "Không được để trống";
                session.setAttribute("message", message);
                response.sendRedirect("create");
            } else {
                if (!EMAIL_PATTERN.matches(entity.getEmail())) {
                    _Users checkEmail = userDAO.findByEmail(entity.getEmail());
                    if (checkEmail==null){
                        if (entity.getPass().equals(pass2)) {
                            if (matcher.find()) {
                                entity.setPass(EncryptUtil.encrypt(request.getParameter("pass")));
                                userDAO.create(entity);
                                String succs = "Thêm mới thành công";
                                session.setAttribute("success", succs);
                                response.sendRedirect("create");
                                logger.debug("Khoi tao luc :"+ new Date() +" , Nguoi tao: "+nguoiTao.getFullname());
                            } else {
                                message = "Số điện thoại không đúng định dạng";
                                session.setAttribute("message", message);
                                response.sendRedirect("create");
                            }
                        } else {
                            message = "Xác nhận mật khẩu không khớp vui lòng nhập lại";
                            session.setAttribute("message", message);
                            response.sendRedirect("create");
                        }
                    }else {
                        message = "Email đã tồn tại";
                        session.setAttribute("message", message);
                        response.sendRedirect("create");
                    }

                } else {
                    message = "Email không đúng định dạng";
                    session.setAttribute("message", message);
                    response.sendRedirect("create");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
