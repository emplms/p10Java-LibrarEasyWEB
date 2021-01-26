package com.emmanuel.plumas.p10JavaLibrarEasyApi.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationWithWaitingListEntity;
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
	
	@Autowired
	@Qualifier("BorrowService")
	private BorrowService borrowService;
	
	public List<ReservationWithWaitingListEntity> getReservationByUserLastName(String userLastName){
		UserEntity userEntity=userService.getUserByUserLastName(userLastName);
		List<ReservationEntity> reservationByUserLastNameEntities=reservationRepository.getReservationByUserEntity(userEntity); 		
		List<ReservationWithWaitingListEntity> reservationWithWaitingListByUserLastNameEntities=transformReservationEntitiesToReservationWithWaitingListEntities(reservationByUserLastNameEntities);
		for(ReservationWithWaitingListEntity reservationWithWaitingListEntity:reservationWithWaitingListByUserLastNameEntities) {
			reservationWithWaitingListEntity.setPositionWaitingList(calculatePositionWaitingList(reservationWithWaitingListEntity.getBookEntity(),reservationWithWaitingListEntity.getPosition(),userLastName));
			reservationWithWaitingListEntity.setDateNextReturn((calculateNextReturnDate(reservationWithWaitingListEntity.getBookEntity().getBookId())));
		}
		return reservationWithWaitingListByUserLastNameEntities;
	}

	private List<ReservationWithWaitingListEntity> transformReservationEntitiesToReservationWithWaitingListEntities(List<ReservationEntity> reservationEntities){
		List<ReservationWithWaitingListEntity> reservationWithWaitingListEntities=new ArrayList<ReservationWithWaitingListEntity>();
		for (int i=0;i<(reservationEntities.size());i++) {
			ReservationWithWaitingListEntity reservationWithWaitingListEntity= new ReservationWithWaitingListEntity();
			ReservationEntity reservationEntity=reservationEntities.get(i);
			reservationWithWaitingListEntity.setReservationId(reservationEntity.getReservationId());
			reservationWithWaitingListEntity.setPosition(reservationEntity.getPosition());
			reservationWithWaitingListEntity.setnotificationDate(reservationEntity.getNotificationDate());
			reservationWithWaitingListEntity.setBookEntity(reservationEntity.getBookEntity());
			reservationWithWaitingListEntity.setUserEntity(reservationEntity.getUserEntity());
			reservationWithWaitingListEntities.add(reservationWithWaitingListEntity);
		}
		return reservationWithWaitingListEntities;
	}
	
	private int calculatePositionWaitingList(BookEntity bookEntity, int position, String userLastName) {
		int positionListeAttente=1;
		List<ReservationEntity> reservationEntities=reservationRepository.getReservationByBookEntity(bookEntity);
		for (ReservationEntity reservationEntity : reservationEntities) {
			if(!(reservationEntity.getUserEntity().getUserLastName().equals(userLastName))){
				if(position>reservationEntity.getPosition()) {
					positionListeAttente=positionListeAttente+1;
				}
			}
		}
		return positionListeAttente;
	}
	
	
	private Date calculateNextReturnDate(Long bookId) {	
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
	
	public List<ReservationEntity> getReservationByBookId(Long bookId) {
		BookEntity bookEntity=bookService.getBookById(bookId);
		List<ReservationEntity> reservationByBookEntities=reservationRepository.getReservationByBookEntity(bookEntity);
		return reservationByBookEntities;
	}
	
	
	
}
