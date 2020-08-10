package com.example.backend.security.matcher;

import org.springframework.http.HttpMethod;
import org.springframework.web.HttpMediaTypeException;

import java.util.*;

public class AuthMatcherBuilder {
	private String currentPattern;
	private final List<AuthRule> rules;
	private final Set<String> currentRoles;
	private final Set<HttpMethod> currentMethods;

	public AuthMatcherBuilder() {
		currentRoles = new HashSet<>();
		currentMethods = new HashSet<>();
		currentPattern = null;
		rules = new ArrayList<>();
	}

	public AuthMatcherBuilder withPattern(String pattern) {
		finalizeRule();
		this.currentPattern = pattern;
		return this;
	}

	public AuthMatcherBuilder withPattern(String pattern, HttpMethod... methods) {
		finalizeRule();
		this.currentPattern = pattern;
		currentMethods.addAll(Arrays.asList(methods));
		return this;
	}

	public AuthMatcherBuilder withRole(String role) {
		currentRoles.add(role);
		return this;
	}

	public AuthMatcherBuilder withRole(String... roles) {
		currentRoles.addAll(Arrays.asList(roles));
		return this;
	}

	public AuthMatcherBuilder withAnyRole() {
		currentRoles.clear();
		return this;
	}

	private void finalizeRule() {
		if (currentPattern != null) {
			AuthRule authRule = new AuthRule(
					currentPattern,
					new HashSet<>(currentRoles),
					new HashSet<>(currentMethods));

			currentPattern = null;
			currentRoles.clear();
			currentMethods.clear();

			rules.add(authRule);
		}
	}


	public AuthMatcher build() {
		finalizeRule();
		return new AuthMatcher(rules);
	}

}
