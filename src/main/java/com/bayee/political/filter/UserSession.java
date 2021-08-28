package com.bayee.political.filter;

import com.bayee.political.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author xxl
 * @date 2021/8/5
 */
public class UserSession {

    private final static String LOGIN_KEY = "police-login-info";

    public static void login(HttpServletRequest httpServletRequest, User user) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute(LOGIN_KEY, user);
    }

    public static User getCurrentLoginPolice(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        Object obj = httpSession.getAttribute(LOGIN_KEY);
        if (obj instanceof User) {
            return (User) obj;
        }
        return null;
    }

}
