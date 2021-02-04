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
	
	@Autowired
	@Qualifier("CopyService")
	private CopyService copyService;
	
	
	public List<ReservationWithWaitingListEntity> getReservationByUserLastName(String userLastName){
		UserEntity userEntity=userService.getUserByUserLastName(userLastName);
		List<ReservationEntity> reservationByUserLastNameEntities=reservationRepository.getReservationByUserEntity(userEntity); 		
		List<ReservationWithWaitingListEntity> reservationWithWaitingListByUserLastNameEntities=transformReservationEntitiesToReservationWithWaitingListEntities(reservationByUserLastNameEntities,null);
		for(ReservationWithWaitingListEntity reservationWithWaitingListEntity:reservationWithWaitingListByUserLastNameEntities) {
			reservationWithWaitingListEntity.setPositionWaitingList(calculatePositionWaitingList(reservationWithWaitingListEntity.getBookEntity(),reservationWithWaitingListEntity.getPosition(),userLastName));
			reservationWithWaitingListEntity.setDateNextReturn((calculateNextReturnDate(reservationWithWaitingListEntity.getBookEntity().getBookId())));
		}
		return reservationWithWaitingListByUserLastNameEntities;
	}

	public List<ReservationWithWaitingListEntity> transformReservationEntitiesToReservationWithWaitingListEntities(List<ReservationEntity> reservationEntities,Date dateNextReturn){
		List<ReservationWithWaitingListEntity> reservationWithWaitingListEntities=new ArrayList<ReservationWithWaitingListEntity>();
		for (int i=0;i<(reservationEntities.size());i++) {
			ReservationWithWaitingListEntity reservationWithWaitingListEntity= new ReservationWithWaitingListEntity();
			ReservationEntity reservationEntity=reservationEntities.get(i);
			reservationWithWaitingListEntity.setReservationId(reservationEntity.getReservationId());
			reservationWithWaitingListEntity.setPosition(reservationEntity.getPosition());
			reservationWithWaitingListEntity.setnotificationDate(reservationEntity.getNotificationDate());
			reservationWithWaitingListEntity.setBookEntity(reservationEntity.getBookEntity());
			reservationWithWaitingListEntity.setUserEntity(reservationEntity.getUserEntity());
			reservationWithWaitingListEntity.setDateNextReturn(dateNextReturn);
			reservationWithWaitingListEntities.add(reservationWithWaitingListEntity);
		}
		return reservationWithWaitingListEntities;
	}
	
	public int calculatePositionWaitingList(BookEntity bookEntity, int position, String userLastName) {
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
	
	
	public Date calculateNextReturnDate(Long bookId) {	
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

	public void createReservation(String userLastName, Long bookId) {
		ReservationEntity reservationEntity=createReservationEntityWithUserLastNameAndBookId(userLastName,bookId);
		if(verifyRG(userLastName, bookId)) {
			reservationRepository.save(reservationEntity);
		}
	}
	
	private ReservationEntity createReservationEntityWithUserLastNameAndBookId(String userLastName, Long bookId) {
		ReservationEntity reservationEntity= new ReservationEntity();	
		reservationEntity.setPosition(getTheMaximumOfPositionInReservationEntitiesDataBase()+1);
		reservationEntity.setBookEntity(bookService.getBookById(bookId));
		reservationEntity.setUserEntity(userService.getUserByUserLastName(userLastName));
		return reservationEntity;
	}
	
	private int getTheMaximumOfPositionInReservationEntitiesDataBase() {
		int maxValueOfPosition=0;
		List<ReservationEntity> reservationEntities=(List<ReservationEntity>) reservationRepository.findAll();
		for (ReservationEntity reservationEntity:reservationEntities) {
			if(reservationEntity.getPosition()>maxValueOfPosition) {
				maxValueOfPosition=reservationEntity.getPosition();
			}
		}
		return maxValueOfPosition;
	}
	
	private Boolean verifyRG(String userLastName, Long bookId) {
		Boolean rgVerificationIsOk=false;
		if(!verifyBookIsBorrowedByUser(userLastName,bookId) && !verifyWaitingListIsFull(bookId) && !verifyBookIsAvailable(bookId)) {
			rgVerificationIsOk=true;
		}
		return rgVerificationIsOk;
	}
	
	private Boolean verifyBookIsBorrowedByUser(String userLastName, Long bookId) {
		Boolean yetBorrowed= false;
		List<ReservationEntity> reservationEntities=(List<ReservationEntity>) reservationRepository.findAll();
		for(ReservationEntity reservationEntity:reservationEntities) {
			if((reservationEntity.getBookEntity().getBookId()==bookId) && (reservationEntity.getUserEntity().getUserLastName().equals(userLastName))) {
				yetBorrowed=true;
			}
		}
		return yetBorrowed;
	}
	
	private Boolean verifyWaitingListIsFull(Long bookId) {
		Boolean waitingListIsFull=true;
		//Récupérer le nombre de copies existantes du livre
		int numberOfExistingCopyByBookId = copyService.getExistingCopyNumberByBookId(bookService.getBookById(bookId));
		//Vérifier le nombre de réservation existante pour ce livre
		int numberOfReservationByBookId = getReservationByBookId(bookId).size();
		//Comparer le nombre de copies extistante et le nombre de réservation
		if((2*numberOfExistingCopyByBookId)>numberOfReservationByBookId) {
			waitingListIsFull=false;
		}
		return waitingListIsFull;
	}
	
	private Boolean verifyBookIsAvailable(Long bookId) {
		Boolean bookIsAvailable=true;
		if(copyService.getCopyNumberAvailableByBookEntity(bookService.getBookById(bookId))==0) {
			bookIsAvailable=false;
		}
		return bookIsAvailable;
	}

	public void deleteReservation(Long reservationId) {
		reservationRepository.deleteById(reservationId);;
		
	}
}
