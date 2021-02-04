package com.emmanuel.plumas.p10JavaLibrarEasyApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationWithWaitingListEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	@Qualifier("ReservationService")
	private ReservationService reservationService;
	
	@GetMapping(value="reservations/reservationsByUser/{userLastName}")
	List<ReservationWithWaitingListEntity> getReservationByUserLastName(@PathVariable String userLastName){
		List<ReservationWithWaitingListEntity> reservationByUserLastNameEntities=reservationService.getReservationByUserLastName(userLastName);
		return reservationByUserLastNameEntities;
		}
	
	@GetMapping(value="reservations/reservationsByBook/{bookId}")
	List<ReservationEntity> getReservationByBookEntity(@PathVariable Long bookId){
		List<ReservationEntity> reservationByBookIdEntities=reservationService.getReservationByBookId(bookId);
		return reservationByBookIdEntities;
	}
	
	@GetMapping(value="reservations/createReservation/{bookId}/{userLastName}")
	public void createReservation(@PathVariable Long bookId, @PathVariable String userLastName) {
		reservationService.createReservation(userLastName,bookId);
	}
}

