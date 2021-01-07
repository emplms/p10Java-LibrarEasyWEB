package com.emmanuel.plumas.p7JavaLibrarEasyApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.UserEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.service.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;

	@GetMapping(value="libraryUser/{userLastName}")
	public UserEntity getUserByLastName (@PathVariable String userLastName) {
		UserEntity userEntity=userService.getUserByUserLastName(userLastName);
		return userEntity;
	}
	
	@GetMapping(value="libraryUsers")
	public List<UserEntity> getAllUsers(){
		List<UserEntity> userEntities=userService.getAllUsers();
		return userEntities;
	}
	
	@PostMapping(value="libraryUser/create")
	public void createLibraryUser(@RequestBody UserEntity userEntity) {
		userService.createUser(userEntity);
	}
	
	@PostMapping(value="libraryUser/update")
	public void upDateLibraryUser(@RequestBody UserEntity userEntity) {
		userEntity=userService.upDateUser(userEntity);
	}
	
	@GetMapping(value="libraryUser/delete/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		userService.deleteUserById(userId);
	}
}
