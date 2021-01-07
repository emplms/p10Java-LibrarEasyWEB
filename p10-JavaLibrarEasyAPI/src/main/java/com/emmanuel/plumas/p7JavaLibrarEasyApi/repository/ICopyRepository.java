package com.emmanuel.plumas.p7JavaLibrarEasyApi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.CopyEntity;

@Repository
@Qualifier("ICopyRepository")
public interface ICopyRepository extends CrudRepository<CopyEntity, Long>{

	List<CopyEntity> findByBookEntity(BookEntity bookEntity);

	CopyEntity findByCopyId(Long copyId);

	void deleteByCopyId(Long copyId);


}
