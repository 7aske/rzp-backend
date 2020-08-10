package com.example.backend.security.matcher;

import org.springframework.http.HttpMethod;
import org.springframework.web.HttpMediaTypeException;

import java.util.*;

public class AuthMatcherBuilder implements PatternBuilder, RuleBuilder {
	private boolean requiresAuthorization;
	private String currentPattern;
	private final List<AuthRule> rules;
	private final Set<String> currentRoles;
	private final Set<HttpMethod> currentMethods;

	protected AuthMatcherBuilder() {
		currentRoles = new HashSet<>();
		currentMethods = new HashSet<>();
		currentPattern = null;
		requiresAuthorization = false;
		rules = new ArrayList<>();
	}

	public RuleBuilder authorize() {
		this.requiresAuthorization = true;
		return this;
	}

	public RuleBuilder allow() {
		this.requiresAuthorization = false;
		return this;
	}

	public PatternBuilder withPattern(String pattern) {
		this.currentPattern = pattern;
		return this;
	}

	public PatternBuilder withPattern(String pattern, HttpMethod... methods) {
		this.currentPattern = pattern;
		currentMethods.addAll(Arrays.asList(methods));
		return this;
	}

	public RuleBuilder and() {
		finalizeRule();
		return this;
	}


	public PatternBuilder withRole(String role) {
		currentRoles.add(role);
		return this;
	}

	public PatternBuilder withRole(String... roles) {
		currentRoles.addAll(Arrays.asList(roles));
		return this;
	}

	public PatternBuilder withRole(Collection<String> roles) {
		currentRoles.addAll(roles);
		return this;
	}

	public PatternBuilder withAnyRole() {
		currentRoles.clear();
		return this;
	}

	private void finalizeRule() {
		if (currentPattern != null) {
			AuthRule authRule = new AuthRule(
					currentPattern,
					new HashSet<>(currentRoles),
					new HashSet<>(currentMethods),
					requiresAuthorization);

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
