package com.emmanuel.plumas.p10JavaLibrarEasyWEB.proxies;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyWEB.model.UserEntity;


@FeignClient(name="p10JavaLibrarEasy-API",url="localhost:9001")
@Qualifier("ApiProxy")
public interface ApiProxy {
	
	@GetMapping(value="books")
	List<BookEntity> getAllBooks();
	
	@GetMapping(value="book/{bookTitle}")
	List<BookEntityAvailable> getBooksByTitle(@PathVariable String bookTitle);
	
	@GetMapping(value="libraryUser/{userLastName}")
	UserEntity getUserByLastName (@PathVariable String userLastName);

	@GetMapping(value="borrow/{userLastName}")
	List<BorrowEntity> getBorrowByUserName(@PathVariable String userLastName);
	
	@GetMapping(value="borrow/extendBorrow/{borrowId}")
	public void setExtendBorrow(@PathVariable Long borrowId);
	
	@GetMapping(value="reservations/reservationsByUser/{userLastName}")
	List<ReservationEntity> getReservationByUserLastName(@PathVariable String userLastName);
	
	@GetMapping(value="reservations/reservationsByBook/{bookId}")
	List<ReservationEntity> getReservationByBookId(@PathVariable Long bookId);
}
