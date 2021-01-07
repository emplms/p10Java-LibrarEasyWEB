package com.emmanuel.plumas.p7JavaLibrarEasyApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BorrowEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.service.BorrowService;

@RestController
public class BorrowController {

	@Autowired
	@Qualifier("BorrowService")
	private BorrowService borrowService;
	
	@GetMapping(value="borrow/{userLastName}")
	public List<BorrowEntity> getBorrowByUserName(@PathVariable String userLastName){
		List<BorrowEntity> borrowEntities=borrowService.getBorrowByUserLastName(userLastName);
		return borrowEntities;
	}
	
	@GetMapping(value="borrow/extendBorrow/{borrowId}")
	public void setExtendBorrow(@PathVariable Long borrowId) {
		borrowService.extendBorrowEndDate(borrowId);
	}
	
	@GetMapping(value="borrow/isNotReturned")
	public List<BorrowEntity> getOutOfTimeAndNotReturnedBorrow(){
		List<BorrowEntity> borrowEntities=borrowService.getOutOfTimeAndNotReturnedBorrow();
		return borrowEntities;
	}
	
	@GetMapping(value="borrows")
	public List<BorrowEntity> getAllBorrows(){
		List<BorrowEntity> borrowEntities=borrowService.getAllBorrows();
		return borrowEntities;
	}
	
	@PostMapping(value="borrow/create")
	public void createBorrow(@RequestBody BorrowEntity borrowEntity) {
		borrowService.createBorrow(borrowEntity);
	}
	
	@PostMapping(value="borrow/update")
	public void updateBorrow(@RequestBody BorrowEntity borrowEntity) {
		borrowEntity=borrowService.upDateBorrow(borrowEntity);
	}
	
	@GetMapping(value="borrow/delete/{borrowId}")
	public void deleteBorrow(@PathVariable Long borrowId) {
		borrowService.deleteBorrow(borrowId);
	}
}
