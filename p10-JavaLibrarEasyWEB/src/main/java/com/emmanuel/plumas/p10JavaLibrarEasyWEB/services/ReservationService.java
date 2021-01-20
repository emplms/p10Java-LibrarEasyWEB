package com.emmanuel.plumas.p10JavaLibrarEasyWEB.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.ReservationEntity;
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
	
	public List<ReservationEntity> getReservationByUserLastName(String userLastName){
		List<ReservationEntity> reservationByUserLastNameEntities=apiProxy.getReservationByUserLastName(userLastName);
		for (ReservationEntity reservationEntity : reservationByUserLastNameEntities) {
			reservationEntity.setPositionListeAttente(determinePositionListeAttente(reservationEntity.getBookEntity().getBookId(),reservationEntity.getPosition(), userLastName));
			reservationEntity.setDateProchainRetour(determineDateProchainRetour(reservationEntity.getBookEntity().getBookId()));
		}
		return reservationByUserLastNameEntities;
	}
	
	private int determinePositionListeAttente(Long bookId, int position, String userLastName) {
		int positionListeAttente=1;
		List<ReservationEntity> reservationEntities=apiProxy.getReservationByBookId(bookId);
		for (ReservationEntity reservationEntity : reservationEntities) {
			if(!(reservationEntity.getUserEntity().getUserLastName().equals(userLastName))){
				if(position>reservationEntity.getPosition()) {
					positionListeAttente=positionListeAttente+1;
				}
			}
		}
		return positionListeAttente;
	}
	
	
	private Date determineDateProchainRetour(Long bookId) {
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date dateProchainRetour = cal.getTime();
		List<BorrowEntity> borrowEntities=borrowService.getBorrowByBookId(bookId);
		for (BorrowEntity borrowEntity : borrowEntities) {
			if(borrowEntity.getEndDate().before(dateProchainRetour)) {
				dateProchainRetour=borrowEntity.getEndDate();
			}
		}
		return dateProchainRetour;
	}
}
