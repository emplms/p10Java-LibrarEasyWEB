package com.emmanuel.plumas.p7JavaLibrarEasyWEB.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p7JavaLibrarEasyWEB.model.UserEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyWEB.services.UserService;

@Service
@Qualifier("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	
	
	public UserDetails loadUserByUsername(String userLastName) throws UsernameNotFoundException{
		UserEntity userEntity=userService.findByLastName(userLastName);
		return new org.springframework.security.core.userdetails.User(userEntity.getUserLastName(), userEntity.getUserPassword(), getGrantedAuthorities());
	}

	private List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}
}
