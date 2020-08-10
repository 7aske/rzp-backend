package com.example.backend.security.matcher;


import org.springframework.http.HttpMethod;

public interface RuleBuilder {
	PatternBuilder withPattern(String pattern);

	PatternBuilder withPattern(String pattern, HttpMethod... methods);

	RuleBuilder authorize();

	RuleBuilder allow();

	RuleBuilder and();
}
