package com.emmanuel.plumas.p10JavaLibrarEasyWEB.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.ReservableBook;
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
	
	@Autowired
	private BookService bookService;
	
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

	public List<ReservableBook> getReservationPossibleByBookId(List<BookEntityAvailable> bookByTitleResult, String userLastName) {
		List<ReservableBook> reservableBooks= new ArrayList<ReservableBook>();
		for (BookEntityAvailable bookEntityAvailable:bookByTitleResult) {
			ReservableBook reservableBook= new ReservableBook();
			reservableBook=bookService.transformBookEntityAvailableToReservabelBook(bookEntityAvailable);
			if(verifyRG(bookEntityAvailable.getBookId(),userLastName) && bookEntityAvailable.getAvailableCopyNumber()==0) {
				reservableBook.setIsReservable(true);
			}else{
				reservableBook.setIsReservable(false);
			}
			reservableBooks.add(reservableBook);
		}
		return reservableBooks;
	}
	
	private Boolean verifyRG(Long bookId, String userLastName) {
		return (
				!verifyBookIsReservedByUser(userLastName,bookId) &&
				!verifyBookIsAlreadyBorrowedByUser(userLastName,bookId) &&
				!verifyWaitingListIsFull(bookId));
	}
	
	private Boolean verifyBookIsReservedByUser(String userLastName, Long bookId) {
		Boolean isAlreadyReserved=false;
		List<ReservationWithWaitingListEntity> reservationEntities=getReservationByUserLastName(userLastName);
		for(ReservationWithWaitingListEntity reservationEntity:reservationEntities) {
			if(reservationEntity.getBookEntity().getBookId().equals(bookId)) {
				isAlreadyReserved=true;
			}
		}
		return isAlreadyReserved;
	}
	
	private Boolean verifyBookIsAlreadyBorrowedByUser(String userLastName, Long bookId) {
		Boolean bookIsAlreadyBorrowedByUser=false;
		List<BorrowEntity> borrowEntities=borrowService.getCurrentBorrowByUserLastName(userLastName);
		for(BorrowEntity borrowEntity:borrowEntities) {
			if(borrowEntity.getCopyEntity().getBookEntity().getBookId().equals(bookId)) {
				bookIsAlreadyBorrowedByUser=true;
			}
		}
		return bookIsAlreadyBorrowedByUser;
	}
	
	private Boolean verifyWaitingListIsFull(Long bookId) {
		Boolean waitingListIsFull=true;
		//Vérifier le nombre de copy existantes pour ce livre
		int numberOfExistingCopyByBookId = bookService.getNumberOfBookCopyByBookId(bookId);
		//Vérifier le nombre de réservation existante pour ce livre
		int numberOfReservationByBookId = apiProxy.getReservationByBookId(bookId).size();
		//Comparer le nombre de copies extistante et le nombre de réservation
		if((2*numberOfExistingCopyByBookId)>numberOfReservationByBookId) {
			waitingListIsFull=false;
		}
		return waitingListIsFull;
	}
	
	
}
