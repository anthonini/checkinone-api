package com.checkinone.api.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityConfigRules {

	public static void apply(HttpSecurity http) throws Exception {
		http
    	.authorizeHttpRequests(authz -> authz
    			.requestMatchers(
    					"/layout/**",
    					"/swagger-ui.html",
    					"/swagger-ui/**",
    					"/v3/api-docs*/**"
    					).permitAll()
    			.anyRequest().authenticated());
	}
}
