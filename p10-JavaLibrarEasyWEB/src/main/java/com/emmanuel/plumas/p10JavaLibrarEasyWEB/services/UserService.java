package com.emmanuel.plumas.p10JavaLibrarEasyWEB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.UserEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.proxies.ApiProxy;

@Service
@Qualifier("UserService")
public class UserService {

	@Autowired
	@Qualifier("ApiProxy")
	private ApiProxy apiProxy;
	
	public UserEntity findByLastName(String userLastName) {
		UserEntity userEntity=apiProxy.getUserByLastName(userLastName);
		return userEntity;
	}
}
