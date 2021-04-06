/**
 * 
 */
package com.bayee.political.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bayee.political.controller.BaseController;
import com.bayee.political.domain.User;

/**
 * @author shentuqiwei
 * @version 2020年5月22日 下午1:23:19
 */
public class SessionFilter extends OncePerRequestFilter {

	protected String url = "http://127.0.0.1:8080";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String[] notFilter = new String[] { "resources", "login", "logout", "signin", "signou", "signup", "api", "sms",
				"registered", "forget", "logistics", "email-check", "updatePath", "update-pass-word" };

		String uri = request.getRequestURI();
		if (uri.endsWith("/"))
			uri = uri.substring(0, uri.lastIndexOf("/"));

		String contextPath = request.getContextPath();

		String redirectUrl = null;
		if (request.getMethod().equals("GET")) {
			String requestUrl = url + request.getRequestURI();
			boolean first = true;
			Set<String> keySets = request.getParameterMap().keySet();
			for (String key : keySets) {
				String par = request.getParameter(key);
				if (first) {
					requestUrl += ("?" + key + "=" + par);
					first = false;
				} else {
					requestUrl += ("&" + key + "=" + par);
				}
			}
			redirectUrl = requestUrl;
		}

		User account = (User) request.getSession().getAttribute(BaseController.SESSION_USER);
		boolean logined = account != null;

		if (!logined) {
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					doFilter = false;
					break;
				}
			}

			if (doFilter) {
				if (!logined) {
					// redirect to login page
					if (StringUtils.isNotEmpty(redirectUrl)) {
						// response.sendRedirect(contextPath+"/account/login?redirectUrl="+redirectUrl);
						response.sendRedirect(contextPath + "/account/login");
					} else {
						response.sendRedirect(contextPath + "/account/login");
					}
				} else {
					filterChain.doFilter(request, response);
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
