package com.emmanuel.plumas.p10JavaLibrarEasyBatch.proxies;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.UserEntity;

@FeignClient(name="p10JavaLibrarEasy-API",url="localhost:9001")
@Qualifier("ApiProxy")
public interface ApiProxy{
	
	@GetMapping(value="borrow/isNotReturned")
	List<BorrowEntity> getOutOfTimeAndNotReturnedBorrow();
	
	@GetMapping(value="libraryUsers")
	List<UserEntity> getAllUsers();
	
	@GetMapping(value="reservations")
	List<ReservationEntity> getAllReservations();
	
	@GetMapping(value="reservations/reservationsByBook/{bookId}")
	List<ReservationEntity> getReservationByBookEntity(@PathVariable Long bookId);
	
	@GetMapping(value="books")
	List<BookEntityAvailable> getAllBooks();
	
	@PostMapping(value="reservation/update")
	public void upDateReservation(@RequestBody ReservationEntity reservationEntity); 
	
	@GetMapping(value="deleteReservation/{reservationId}")
	public void deleteReservation(@PathVariable Long reservationId);
}
