package com.example.backend.filter;


import com.example.backend.entity.data.Locale;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
public class LocalizationFilter implements Filter {
	private static final String DEFAULT_LOCALE = Locale.DEFAULT_LOCALE;
	private static final String COOKIE_NAME = "locale";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		Cookie localeCookie = getCookie(httpServletRequest.getCookies(), COOKIE_NAME);
		if (localeCookie == null) {
			localeCookie = new Cookie(COOKIE_NAME, DEFAULT_LOCALE);
			httpServletResponse.addCookie(localeCookie);
		}
		httpServletRequest.setAttribute(COOKIE_NAME, localeCookie.getValue());
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}

	private Cookie getCookie(Cookie[] cookies, String name) {
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}
}
