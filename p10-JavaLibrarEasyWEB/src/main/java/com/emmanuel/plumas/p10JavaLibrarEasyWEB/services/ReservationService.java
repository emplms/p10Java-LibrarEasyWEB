package com.emmanuel.plumas.p10JavaLibrarEasyWEB.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.ReservationWithWaitingListEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.proxies.ApiProxy;

@Service
@Qualifier("ReservationService")
public class ReservationService {

	@Autowired
	@Qualifier("ApiProxy")
	private ApiProxy apiProxy;
	
	@Autowired
	@Qualifier("BorrowService")
	private BorrowService borrowService;
	
	public List<ReservationWithWaitingListEntity> getReservationByUserLastName(String userLastName){
		List<ReservationWithWaitingListEntity> reservationWithWaitingListByUserLastNameEntities=apiProxy.getReservationByUserLastName(userLastName);
		return reservationWithWaitingListByUserLastNameEntities;
	}

	public void createReservation(Long bookId,String userLastName) {
		apiProxy.createReservation(bookId,userLastName);
	}
	
	public void deleteReservation(Long reservationId) {
		apiProxy.deleteReservation(reservationId);
	}
}
