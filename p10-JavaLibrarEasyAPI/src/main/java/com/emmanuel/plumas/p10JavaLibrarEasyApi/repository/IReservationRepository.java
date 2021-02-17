package com.emmanuel.plumas.p10JavaLibrarEasyApi.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.UserEntity;

@Repository
@Qualifier("IReservationRepository")
public interface IReservationRepository extends CrudRepository<ReservationEntity,Long>{

	List<ReservationEntity> getReservationByUserEntity(UserEntity userEntity);

	List<ReservationEntity> getReservationByBookEntity(BookEntity bookEntity);

	ReservationEntity getReservationByReservationId(long reservationId);
	
	void deleteByReservationId(long reservationId);
}
