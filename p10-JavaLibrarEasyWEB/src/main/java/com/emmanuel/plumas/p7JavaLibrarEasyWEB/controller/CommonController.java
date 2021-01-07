package com.emmanuel.plumas.p7JavaLibrarEasyWEB.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class CommonController {
	
	protected String getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userLastName = "empty";
		if (principal instanceof UserDetails) {
			userLastName = ((UserDetails) principal).getUsername();
		} else {
			userLastName = principal.toString();
		}
		return userLastName;
	}
	
}
