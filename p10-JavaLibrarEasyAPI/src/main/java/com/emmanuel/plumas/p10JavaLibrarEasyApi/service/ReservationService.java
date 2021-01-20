package com.emmanuel.plumas.p10JavaLibrarEasyApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.UserEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.repository.IReservationRepository;

@Service
@Qualifier("ReservationService")
public class ReservationService {

	@Autowired
	@Qualifier("IReservationRepository")
	private IReservationRepository reservationRepository;
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	
	@Autowired
	@Qualifier("BookService")
	private BookService bookService;
	
	public List<ReservationEntity> getReservationByUserLastName(String userLastName){
		UserEntity userEntity=userService.getUserByUserLastName(userLastName);
		List<ReservationEntity> reservationByUserLastNameEntities=reservationRepository.getReservationByUserEntity(userEntity); 		
		return reservationByUserLastNameEntities;
	}

	public List<ReservationEntity> getReservationByBookId(Long bookId) {
		BookEntity bookEntity=bookService.getBookById(bookId);
		List<ReservationEntity> reservationByBookEntities=reservationRepository.getReservationByBookEntity(bookEntity);
		return reservationByBookEntities;
	}
	
	
	
}
