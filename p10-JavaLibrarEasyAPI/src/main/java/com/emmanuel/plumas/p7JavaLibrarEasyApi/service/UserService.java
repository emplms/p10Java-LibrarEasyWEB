package com.emmanuel.plumas.p7JavaLibrarEasyApi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.UserEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.repository.IUserRepository;

@Service
@Transactional
@Qualifier("UserService")
public class UserService {
	
	@Autowired
	@Qualifier("IUserRepository")
	private IUserRepository userRepository;
	
	public UserEntity getUserByUserLastName(String userLastName) {
		UserEntity userEntity=userRepository.findByUserLastName(userLastName);
		return userEntity;
	}

	public void createUser(UserEntity userEntity) {
		userEntity.setUserPassword(userEntity.getUserPassword());
		userRepository.save(userEntity);
	}

	public List<UserEntity> getAllUsers() {
		List<UserEntity> userEntities=(List<UserEntity>) userRepository.findAll();
		return userEntities;
	}
	
	public UserEntity getUserById(Long userId) {
		UserEntity userEntity=userRepository.findByUserId(userId);
		return userEntity;
	}
	
	public UserEntity upDateUser(UserEntity userEntity) {
		UserEntity user=getUserById(userEntity.getUserId());
		user.setUserEmail(userEntity.getUserEmail());
		user.setUserFirstName(userEntity.getUserFirstName());
		user.setUserLastName(userEntity.getUserLastName());
		user.setUserPassword(userEntity.getUserPassword());
		user.setUserPhoneNumber(userEntity.getUserPhoneNumber());
		userRepository.save(user);
		return user;
	}

	public void deleteUserById(Long userId) {
		userRepository.deleteByUserId(userId);
	}
}
