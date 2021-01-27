package com.emmanuel.plumas.p10JavaLibrarEasyWEB.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.services.BookService;

@Controller
public class BookController extends CommonController{

	@Autowired
	private BookService bookService;
	
	
	@ModelAttribute("bookForm")
	public BookEntity setBookEntity() {
		return new BookEntity();
	}
	
	@GetMapping(value="/books")
	public String getBooks(Model model) {	
		List<BookEntityAvailable> bookEntities=bookService.getAllBooks();
		model.addAttribute("bookEntities", bookEntities);
		return "books";
	}
	
	@GetMapping(value="/bookByTitleSearch")
	public String getBookByTitle(Model model) {
		return "bookByTitleSearch";
	}
	
	@PostMapping(value="/bookByTitleSearch")
	public String getBookByTitle(Model model, @ModelAttribute("bookForm") BookEntity bookEntity) {
		List<BookEntityAvailable> bookByTitleResult=bookService.getBookByTitle(bookEntity);
		model.addAttribute("booksList", bookByTitleResult);
		return "bookByTitleResult";
	}
}
