package com.example.backend.security.matcher;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AuthRule {
	private boolean requiresAuthorization;
	private Set<HttpMethod> methods;
	private Set<String> roles;
	private String pattern;
	private AntPathMatcher matcher;

	public AuthRule(String pattern, Set<String> roles, Set<HttpMethod> methods, boolean requiresAuthorizaion) {
		this.pattern = pattern;
		this.roles = roles;
		this.methods = methods;
		this.requiresAuthorization = requiresAuthorizaion;
		this.matcher = new AntPathMatcher();
	}

	public AuthRule() {
	}

	public Set<HttpMethod> getMethods() {
		return methods;
	}

	public void setMethods(Set<HttpMethod> methods) {
		this.methods = methods;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean match(String path) {
		return matcher.match(pattern, path);
	}

	public boolean match(String path, List<String> roles, HttpMethod method, boolean authorized){
		boolean hasRole = !Collections.disjoint(roles, this.roles) || this.roles.isEmpty();
		boolean methodValid = this.methods.contains(method) || this.methods.isEmpty();
		boolean matched = match(path);

		if (!requiresAuthorization && matched){
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
