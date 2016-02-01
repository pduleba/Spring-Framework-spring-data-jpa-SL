package com.pduleba.spring.security;

import org.springframework.data.domain.AuditorAware;

public class SecurityAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "JPAAuditor";
	}

}
