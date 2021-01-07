package com.emmanuel.plumas.p7JavaLibrarEasyApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.AuthorEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.service.AuthorService;

@RestController	
public class AuthorController {

	@Autowired
	@Qualifier("AuthorService")
	private AuthorService authorService;
	
	@GetMapping(value="authors")
	public List<AuthorEntity> getAllAuthors() {
		List<AuthorEntity> authorEntities=authorService.getAllBooks();
		return authorEntities;
	}
	
	@PostMapping(value="authors/create")
	public void createAuthor(@RequestBody AuthorEntity authorEntity) {
		authorService.createAuthor(authorEntity);
	}
	
	@GetMapping(value="authors/delete/{authorId}")
	public void deleteAuthorById(@PathVariable Long authorId) {
		authorService.deleteAuthorByAuthorId(authorId);
	}
	
	@PostMapping(value="authors/update")
	public void updateAuthor(@RequestBody AuthorEntity authorEntity) {
		authorEntity=authorService.upDateAuthor(authorEntity);
	}
	
}
