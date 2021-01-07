package com.emmanuel.plumas.p7JavaLibrarEasyApi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.AuthorEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.repository.IAuthorRepository;

@Service
@Transactional
@Qualifier("AuthorService")
public class AuthorService {

	@Autowired
	@Qualifier("IAuthorRepository")
	private IAuthorRepository authorRepository;
	
	public List<AuthorEntity> getAllBooks(){
		List <AuthorEntity> authorEntities=(List<AuthorEntity>) authorRepository.findAll();
		return authorEntities;
	}
	
	public AuthorEntity getById(Long authorId) {
		AuthorEntity authorEntity=authorRepository.findByAuthorId(authorId);
		return authorEntity;
	}
	
	public void createAuthor(AuthorEntity authorEntity) {
		authorRepository.save(authorEntity);
	}
	
	public void deleteAuthorByAuthorId(Long authorId) {
		authorRepository.deleteAuthorByAuthorId(authorId);
	}
	
	public AuthorEntity upDateAuthor(AuthorEntity authorEntity) {
		AuthorEntity author=getById(authorEntity.getAuthorId());
		author.setAuthorFirstName(authorEntity.getAuthorFirstName());
		author.setAuthorLastName(authorEntity.getAuthorLastName());
		authorRepository.save(author);
		return author;
	}
}
