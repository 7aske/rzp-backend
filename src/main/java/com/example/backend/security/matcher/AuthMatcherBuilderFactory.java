package com.example.backend.security.matcher;

public class AuthMatcherBuilderFactory {
	public static RuleBuilder getBuilder(){
		return new AuthMatcherBuilder();
	}
}
