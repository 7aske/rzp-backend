package com.example.backend.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.security.*;
import com.example.backend.security.matcher.AuthMatcher;
import com.example.backend.security.matcher.AuthMatcherBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class AuthorizationFilter implements Filter {

	private static final AuthMatcher matcher;

	static {
		matcher = new AuthMatcherBuilder().allow().withPattern("/**").build();
		// matcher = new AuthMatcherBuilder()
		// 		.authorize()
		// 		.withPattern("/role/getAll").withRole("admin").and()
		// 		.withPattern("/post/getAllPublished").withRole("admin").and()
		// 		.allow().withPattern("/auth/*")
		// 		.build();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = ServletAttributes.getSession();
		System.out.println(session.getId());

		if (!matcher.authorized(request)) {
			response.sendError(401, "UNAUTHORIZED");
			return;
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
