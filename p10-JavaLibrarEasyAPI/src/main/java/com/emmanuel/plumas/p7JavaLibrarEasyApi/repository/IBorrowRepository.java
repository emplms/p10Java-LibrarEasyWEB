package com.emmanuel.plumas.p7JavaLibrarEasyApi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BorrowEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.CopyEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.UserEntity;

@Repository
@Qualifier("IBorrowRepository")
public interface IBorrowRepository extends CrudRepository<BorrowEntity, Long>{

	List<BorrowEntity> getBorrowByCopyEntity(CopyEntity copyEntity);
	
	List<BorrowEntity> getBorrowByUserEntity(UserEntity userEntity);
	
	BorrowEntity getBorrowByBorrowId(Long borrowId);
	
	List<BorrowEntity >getBorrowByIsReturned(Boolean isReturned);
	
	void deleteByBorrowId(Long borrowId);
}
