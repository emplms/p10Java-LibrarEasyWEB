package com.emmanuel.plumas.p7JavaLibrarEasyApi.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.AuthorEntity;

@Repository
@Qualifier("IAuthorRepository")
public interface IAuthorRepository extends CrudRepository<AuthorEntity, Long>{

	void deleteAuthorByAuthorId (Long authorId);
	
	AuthorEntity findByAuthorId(Long authorId);
}
