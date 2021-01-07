package com.emmanuel.plumas.p7JavaLibrarEasyApi.service;

import java.util.List;

import javax.transaction.Transactional;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BookEntityAvailable;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.repository.IBookRepository;

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
	
	public BookEntity getBookById(Long bookId) {
		BookEntity bookEntity=bookRepository.findByBookId(bookId);
		return bookEntity;
	}
	
	public List<BookEntity> getAllBooks(){
		List<BookEntity> bookEntities=(List<BookEntity>) bookRepository.findAll();
		return bookEntities;
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
			bookEntityAvailable.setAvailableCopyNumber(copyService.getCopyNumberAvailableByBookEntity(bookEntity));
			bookEntitiesAvailable.add(bookEntityAvailable);
			}
		return bookEntitiesAvailable;
	}
	
}
