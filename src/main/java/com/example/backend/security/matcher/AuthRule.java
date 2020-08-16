package com.example.backend.security.matcher;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AuthRule {
	private final boolean requiresAuthorization;
	private final Set<HttpMethod> methods;
	private final Set<String> roles;
	private final String pattern;
	private final AntPathMatcher matcher;

	public AuthRule(String pattern, Set<String> roles, Set<HttpMethod> methods, boolean requiresAuthorization) {
		this.pattern = pattern;
		this.roles = roles;
		this.methods = methods;
		this.requiresAuthorization = requiresAuthorization;
		this.matcher = new AntPathMatcher();
	}

	public boolean match(String path) {
		return matcher.match(pattern, path);
	}

	public boolean match(String path, List<String> roles, HttpMethod method, boolean authorized){
		boolean hasRole = !Collections.disjoint(roles, this.roles) || this.roles.isEmpty();
		boolean methodValid = this.methods.contains(method) || this.methods.isEmpty();
		boolean matched = match(path);

		if (!requiresAuthorization && matched && methodValid){
			return true;
		}

		return matched && hasRole && methodValid && authorized;
	}

	@Override
	public String toString() {
		return "AuthRule{" +
				"requiresAuthorization=" + requiresAuthorization +
				", methods=" + methods +
				", roles=" + roles +
				", pattern='" + pattern + '\'' +
				", matcher=" + matcher +
				'}';
	}
}
