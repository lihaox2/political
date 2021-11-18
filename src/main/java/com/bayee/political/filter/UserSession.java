package com.bayee.political.filter;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.User;
import com.bayee.political.exception.HandlerException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author xxl
 * @date 2021/8/5
 */
public class UserSession implements Filter {

    private final static String LOGIN_KEY = "police-login-info";

    private final String[] ignorePath = {"/account/login/submit", "/train","/major", "/global", "/honour","/police/promotion","/disciplinary","/doc.html", "/evaluationActivity"};

    /**
     * 警员登录
     * @param httpServletRequest
     * @param user
     */
    public static void login(HttpServletRequest httpServletRequest, User user) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(LOGIN_KEY, user);
//        httpSession.setMaxInactiveInterval(120);
    }

    /**
     *
     * @param httpServletRequest
     * @return
     */
    public static User getCurrentLoginPolice(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        Object obj = httpSession.getAttribute(LOGIN_KEY);
        if (obj instanceof User) {
            return (User) obj;
        }
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        //本地调试放行
        String remoteAddress = httpServletRequest.getRemoteAddr();
        String origin = httpServletRequest.getHeader("origin");

        boolean ignoreFlag = false;
        if (StrUtil.isNotBlank(remoteAddress) && (remoteAddress.contains("127.0.0.1") || remoteAddress.contains("0:0:0:0:0:0:0:1"))) {
            ignoreFlag = true;
        }
        if (StrUtil.isNotBlank(origin) && (origin.contains("127.0.0.1") || origin.contains("0:0:0:0:0:0:0:1"))) {
            ignoreFlag = true;
        }
        if (ignoreFlag) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //特定url放行
        String url = httpServletRequest.getRequestURI();
        for (String path : ignorePath) {
            if (url.contains(path)) {
                chain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }

        //登录验证
        User user = getCurrentLoginPolice(httpServletRequest);
        if (user != null) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.getWriter().print("用户未登录，请登录后操作！");
    }

    @Override
    public void destroy() {

    }
}
