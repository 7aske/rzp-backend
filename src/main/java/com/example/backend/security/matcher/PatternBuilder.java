package com.example.backend.security.matcher;

import java.util.Collection;
import java.util.Set;

public interface PatternBuilder {
	PatternBuilder withRole(String role);

	PatternBuilder withRole(String... roles);

	PatternBuilder withRole(Collection<String> roles);

	PatternBuilder withAnyRole();

	RuleBuilder and();

	AuthMatcher build();
}
