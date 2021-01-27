package com.emmanuel.plumas.p10JavaLibrarEasyWEB.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.proxies.ApiProxy;

@Service
public class BookService {

	@Autowired
	private ApiProxy apiProxy;
	
	public List<BookEntityAvailable> getAllBooks(){
		return apiProxy.getAllBooks();
	}

	public List<BookEntityAvailable> getBookByTitle(BookEntity bookEntity) {
		return apiProxy.getBooksByTitle(bookEntity.getBookTitle());
	}
}
