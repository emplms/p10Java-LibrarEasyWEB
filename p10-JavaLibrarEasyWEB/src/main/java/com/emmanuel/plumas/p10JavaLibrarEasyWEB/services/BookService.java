package com.emmanuel.plumas.p10JavaLibrarEasyWEB.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.CopyEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.ReservableBook;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.proxies.ApiProxy;

@Service
public class BookService {

	@Autowired
	private ApiProxy apiProxy;
	
	@Autowired
	private ReservationService reservationService;
	
	public List<ReservableBook> getAllBooks(String userLastName){
		List<BookEntityAvailable> allBooks=apiProxy.getAllBooks();
		List<ReservableBook> allReservableBooks=reservationService.getReservationPossibleByBookId(allBooks,userLastName);
		return allReservableBooks;
	}

	public List<ReservableBook> getBookByTitle(BookEntity bookEntity, String userLastName) {
		List<BookEntityAvailable> booksByTitleResult=apiProxy.getBooksByTitle(bookEntity.getBookTitle());
		List<ReservableBook> reservableBooks=reservationService.getReservationPossibleByBookId(booksByTitleResult,userLastName);
		return reservableBooks;
	}
	
	public int getNumberOfBookCopyByBookId(Long bookId) {
		int numberOfBookCopyByBookId=0;
		List<CopyEntity> copyEntities=apiProxy.getAllCopies();
		for(CopyEntity copyEntity:copyEntities) {
			if(copyEntity.getBookEntity().getBookId().equals(bookId)) {
				numberOfBookCopyByBookId=numberOfBookCopyByBookId+1;
			}
		}
		return numberOfBookCopyByBookId;
	}
	
	
	public ReservableBook transformBookEntityAvailableToReservabelBook(BookEntityAvailable bookEntityAvailable) {
		ReservableBook reservableBook=new ReservableBook();
		reservableBook.setReservableBookId(bookEntityAvailable.getBookId());
		reservableBook.setBookTitle(bookEntityAvailable.getBookTitle());
		reservableBook.setEditor(bookEntityAvailable.getEditor());
		reservableBook.setBookType(bookEntityAvailable.getBookType());
		reservableBook.setAuthorEntity(bookEntityAvailable.getAuthorEntity());
		reservableBook.setReservationWithWaitingListEntities(bookEntityAvailable.getReservationWithWaitingListEntities());;
		reservableBook.setAvailableCopyNumber(bookEntityAvailable.getAvailableCopyNumber());
		return reservableBook;
	}
}
