package com.emmanuel.plumas.p10JavaLibrarEasyApi.service;

import java.util.List;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationWithWaitingListEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.repository.IBookRepository;

@Service
@Transactional
@Qualifier("BookService")
public class BookService {

	@Autowired
	@Qualifier("IBookRepository")
	private IBookRepository bookRepository;

	@Autowired
	@Qualifier("CopyService")
	private CopyService copyService;
	
	@Autowired
	@Qualifier("BorrowService")
	private BorrowService borrowService;
	
	@Autowired
	@Qualifier("ReservationService")
	private ReservationService reservationService;
	
	public BookEntity getBookById(Long bookId) {
		BookEntity bookEntity=bookRepository.findByBookId(bookId);
		return bookEntity;
	}
	
	public List<BookEntityAvailable> getAllBooks(){
		List<BookEntity> bookEntities=(List<BookEntity>) bookRepository.findAll();
		List<BookEntityAvailable> bookEntitiesAvailable= transformBookEntityToAvailable(bookEntities);
		return bookEntitiesAvailable;
	}

	public List<BookEntityAvailable> getsBookByTitleAvalaibale(String bookTitle) {
		List<BookEntity> bookEntities =bookRepository.findByBookTitle(bookTitle);
		List<BookEntityAvailable> bookEntitiesAvailable= transformBookEntityToAvailable(bookEntities);
		return bookEntitiesAvailable;
	}

	public void createBook(BookEntity bookEntity) {
		bookRepository.save(bookEntity);	
	}

	public BookEntity upDateBook(BookEntity bookEntity) {
		BookEntity book=getBookById(bookEntity.getBookId());
		book.setBookTitle(bookEntity.getBookTitle());
		book.setBookType(bookEntity.getBookType());
		book.setEditor(bookEntity.getEditor());
		book.setAuthorEntity(bookEntity.getAuthorEntity());
		bookRepository.save(book);
		return book;
	}

	public void deleteBookById(Long bookId) {
		bookRepository.deleteByBookId(bookId);
	}
	
	private List<BookEntityAvailable> transformBookEntityToAvailable(List<BookEntity> bookEntities){
		List<BookEntityAvailable> bookEntitiesAvailable= new ArrayList<BookEntityAvailable>();
		for(int i=0; i< (bookEntities.size());i++) {
			BookEntityAvailable bookEntityAvailable=new BookEntityAvailable();
			BookEntity bookEntity= bookEntities.get(i);
			bookEntityAvailable.setBookId(bookEntity.getBookId()); 
			bookEntityAvailable.setBookTitle(bookEntity.getBookTitle());
			bookEntityAvailable.setEditor(bookEntity.getEditor());
			bookEntityAvailable.setBookType(bookEntity.getBookType());
			bookEntityAvailable.setAuthorEntity(bookEntity.getAuthorEntity());
			//Récupérér les ReservationEntity et les Transformer en reservation avec liste d'attente
			Date dateNextReturn=reservationService.calculateNextReturnDate(borrowService.getBorrowByBookId(bookEntity.getBookId()));

			List<ReservationWithWaitingListEntity> reservationWithWaitingListEntities=reservationService.transformReservationEntitiesToReservationWithWaitingListEntities(bookEntity.getReservationEntities(),dateNextReturn);
			
			//Mettre à jour la list de le bookEntityAvalaible
			bookEntityAvailable.setReservationWithWaitingListEntities(reservationWithWaitingListEntities);

			bookEntityAvailable.setAvailableCopyNumber(copyService.getCopyNumberAvailableByBookEntity(bookEntity));
			bookEntitiesAvailable.add(bookEntityAvailable);
			}
		return bookEntitiesAvailable;
	}
	
}
