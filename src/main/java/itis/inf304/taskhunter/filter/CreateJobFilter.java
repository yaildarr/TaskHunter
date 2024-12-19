package itis.inf304.taskhunter.filter;

import itis.inf304.taskhunter.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


@WebFilter(urlPatterns = "/createJob")
public class CreateJobFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CreateJobFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String uri = httpServletRequest.getRequestURI();
        LOG.info(uri);
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getContextPath() + "/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}