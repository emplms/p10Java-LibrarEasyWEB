package com.emmanuel.plumas.p7JavaLibrarEasyApi.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BookEntityAvailable;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.service.BookService;

@RestController
public class BookController {

	@Autowired
	@Qualifier("BookService")
	private BookService bookService;
	
	@GetMapping(value="book/{bookTitle}")
	public List<BookEntityAvailable> getBooksByTitle(@PathVariable String bookTitle){
		ArrayList<BookEntityAvailable> booksAvailable=(ArrayList<BookEntityAvailable>) bookService.getsBookByTitleAvalaibale(bookTitle);
		return booksAvailable;
	}
	
	@GetMapping(value="books")
	public List<BookEntity> getAllBooks(){
		List<BookEntity> bookEntities=bookService.getAllBooks();
		return bookEntities;
	}
	
	@PostMapping(value="book/create")
	public void createBook(@RequestBody BookEntity bookEntity) {
		bookService.createBook(bookEntity);
	}
	
	@PostMapping(value="book/update")
	public void upDateBook(@RequestBody BookEntity bookEntity) {
		bookEntity=bookService.upDateBook(bookEntity);
	}
	
	@GetMapping(value="book/delete/{bookId}")
	public void deleteBookById(@PathVariable Long bookId) {
		bookService.deleteBookById(bookId);
	}
	
}
