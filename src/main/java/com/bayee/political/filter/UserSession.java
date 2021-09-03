package com.bayee.political.filter;

import cn.hutool.core.util.RandomUtil;
import com.bayee.political.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xxl
 * @date 2021/8/5
 */
public class UserSession {

    private final static String LOGIN_KEY = "police-login-info";

    /**
     * 警员登录
     * @param httpServletRequest
     * @param user
     */
    public static void login(HttpServletRequest httpServletRequest, User user) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(LOGIN_KEY, user);
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

}
