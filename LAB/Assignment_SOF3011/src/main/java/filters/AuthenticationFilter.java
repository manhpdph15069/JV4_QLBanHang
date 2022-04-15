package filters;

import entitys._Users;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/admin/users/*",
        "/admin/category/*",
        "/admin/product/*",
})
public class AuthenticationFilter implements Filter {

    public AuthenticationFilter() {

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession();

        _Users users = (_Users) session.getAttribute("user");
        if (users == null) {
            session.setAttribute("message","Vui lòng đăng nhập ");
            httpServletResponse.sendRedirect("/Assignment_SOF3011/login");
            return;
        }
        if (users.getIsAdmin() == 0) {
            httpServletResponse.sendRedirect("/Assignment_SOF3011/client/index");
            return;
        }

        chain.doFilter(request, response);
    }
}
