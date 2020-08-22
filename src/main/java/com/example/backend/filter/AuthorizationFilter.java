package com.example.backend.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.security.*;
import com.example.backend.security.matcher.AuthMatcher;
import com.example.backend.security.matcher.AuthMatcherBuilder;
import com.example.backend.security.matcher.AuthMatcherBuilderFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {

	private static final AuthMatcher matcher;

	static {
		matcher = AuthMatcherBuilderFactory.getBuilder()
				.authorize().withPattern("/admin/**").withRole("admin").and()
				.authorize().withPattern("/user/**").withRole("user", "admin").and()
				.authorize().withPattern("/author/**").withRole("author", "admin").and()
				.allow().withPattern("/**", HttpMethod.OPTIONS).and()
				.allow().withPattern("/auth/*").and()
				.allow().withPattern("/user/getById/*").and()
				.allow().withPattern("/user/register").and()
				.allow().withPattern("/category/**").and()
				.allow().withPattern("/post/**").and()
				.allow().withPattern("/tag/**").and()
				.allow().withPattern("/media/**")
				.build();
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
