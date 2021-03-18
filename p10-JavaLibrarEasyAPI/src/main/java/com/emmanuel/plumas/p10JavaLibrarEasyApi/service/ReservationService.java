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
	
	//Ajouter pour les tests
	public void setReservationRepository(IReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	//Ajouter pour les tests
	public void setBorrowService(BorrowService borrowService) {
		this.borrowService = borrowService;
	}



	public List<ReservationWithWaitingListEntity> getReservationByUserLastName(String userLastName){
		UserEntity userEntity=userService.getUserByUserLastName(userLastName);
		List<ReservationEntity> reservationByUserLastNameEntities=reservationRepository.getReservationByUserEntity(userEntity); 		
		List<ReservationWithWaitingListEntity> reservationWithWaitingListByUserLastNameEntities=transformReservationEntitiesToReservationWithWaitingListEntities(reservationByUserLastNameEntities,null);
		for(ReservationWithWaitingListEntity reservationWithWaitingListEntity:reservationWithWaitingListByUserLastNameEntities) {
			reservationWithWaitingListEntity.setPositionWaitingList(calculatePositionWaitingList(reservationRepository.getReservationByBookEntity(reservationWithWaitingListEntity.getBookEntity()),reservationWithWaitingListEntity));
			reservationWithWaitingListEntity.setDateNextReturn((calculateNextReturnDate(borrowService.getBorrowByBookId(reservationWithWaitingListEntity.getBookEntity().getBookId()))));
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
	
	public int calculatePositionWaitingList(List<ReservationEntity> reservationEntities, ReservationWithWaitingListEntity reservationWithWaitingListEntity) {
		int positionListeAttente=1;
		for (ReservationEntity reservationEntity : reservationEntities) {
			if(!(reservationEntity.getUserEntity().getUserLastName().equals(reservationWithWaitingListEntity.getUserEntity().getUserLastName()))){
				if(reservationWithWaitingListEntity.getPosition()>reservationEntity.getPosition()) {
					positionListeAttente=positionListeAttente+1;
				}
			}
		}
		return positionListeAttente;
	}
	
	
	public Date calculateNextReturnDate(List<BorrowEntity> borrowEntitiesByBookId) {	
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date dateProchainRetour = cal.getTime();
		for (BorrowEntity borrowEntity : borrowEntitiesByBookId) {
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
		BookEntity bookEntity=bookService.getBookById(bookId);
		if(verifyRG(userLastName, bookEntity)) {
			reservationRepository.save(reservationEntity);
		}
	}
	
	private ReservationEntity createReservationEntityWithUserLastNameAndBookId(String userLastName, Long bookId) {
		ReservationEntity reservationEntity= new ReservationEntity();	
		reservationEntity.setPosition(getTheMaximumOfPositionInReservationEntitiesDataBase(bookId)+1);
		reservationEntity.setBookEntity(bookService.getBookById(bookId));
		reservationEntity.setUserEntity(userService.getUserByUserLastName(userLastName));
		return reservationEntity;
	}
	
	// Il peut y avoir des positions identiques mais sur des bookId différents
	private int getTheMaximumOfPositionInReservationEntitiesDataBase(Long bookId) {
		int maxValueOfPosition=0;
		BookEntity bookEntity=bookService.getBookById(bookId);
		List<ReservationEntity> reservationEntities=(List<ReservationEntity>) reservationRepository.getReservationByBookEntity(bookEntity);
		for (ReservationEntity reservationEntity:reservationEntities) {
			if(reservationEntity.getPosition()>maxValueOfPosition) {
				maxValueOfPosition=reservationEntity.getPosition();
			}
		}
		return maxValueOfPosition;
	}
	
	//Centralisation de la vérification des RG avant de créer une réservation dans la BDD
	private Boolean verifyRG(String userLastName, BookEntity bookEntity) {
		return (
				!verifyBookIsReservedByUser(userLastName,bookEntity) &&
				!verifyWaitingListIsFull(bookEntity) && 
				!verifyBookIsAvailable(bookEntity)) &&
				!verifyBookIsBorrowedByUser(userLastName,bookEntity);
	}
	
	public Boolean verifyBookIsReservedByUser(String userLastName, BookEntity bookEntity) {
		Boolean yetReserved= false;
		List<ReservationEntity> reservationEntitiesByBookEntity=(List<ReservationEntity>) reservationRepository.getReservationByBookEntity(bookEntity);
		for(ReservationEntity reservationEntity:reservationEntitiesByBookEntity) {
			if(reservationEntity.getUserEntity().getUserLastName().equals(userLastName)) {
				yetReserved=true;
			}
		}
		return yetReserved;
	}
	
	public Boolean verifyBookIsBorrowedByUser(String userLastName, BookEntity bookEntity) {
		Boolean yetBorrowed=false;
		List<BorrowEntity> borrowEntitiesByBookEntity=borrowService.getBorrowByBookId(bookEntity.getBookId());
		for(BorrowEntity borrowEntity:borrowEntitiesByBookEntity) {
			if(borrowEntity.getUserEntity().getUserLastName().equals(userLastName)) {
				yetBorrowed=true;
			}
		}
		return yetBorrowed;
	}
	
	private Boolean verifyWaitingListIsFull(BookEntity bookEntity) {
		Boolean waitingListIsFull=true;
		//Récupérer le nombre de copies existantes du livre
		int numberOfExistingCopyByBookId = copyService.getExistingCopyNumberByBookId(bookService.getBookById(bookEntity.getBookId()));
		//Vérifier le nombre de réservation existante pour ce livre
		int numberOfReservationByBookId = getReservationByBookId(bookEntity.getBookId()).size();
		//Comparer le nombre de copies extistante et le nombre de réservation
		if((2*numberOfExistingCopyByBookId)>numberOfReservationByBookId) {
			waitingListIsFull=false;
		}
		return waitingListIsFull;
	}
	
	private Boolean verifyBookIsAvailable(BookEntity bookEntity) {
		Boolean bookIsAvailable=true;
		if(copyService.getCopyNumberAvailableByBookEntity(bookService.getBookById(bookEntity.getBookId()))==0) {
			bookIsAvailable=false;
		}
		return bookIsAvailable;
	}

	public void deleteReservation(Long reservationId) {
		reservationRepository.deleteByReservationId(reservationId);;
		
	}

	public List<ReservationEntity> getAllReservations() {
		return (List<ReservationEntity>) reservationRepository.findAll();
	}
	
	public ReservationEntity upDateReservationEntity(ReservationEntity reservationEntity) {
		ReservationEntity reservation=reservationRepository.getReservationByReservationId(reservationEntity.getReservationId());
		reservation.setPosition(reservationEntity.getPosition());
		reservation.setNotificationDate(reservationEntity.getNotificationDate());
		reservation.setBookEntity(reservationEntity.getBookEntity());
		reservation.setUserEntity(reservationEntity.getUserEntity());
		reservationRepository.save(reservation);
		return reservation;
		}
}
