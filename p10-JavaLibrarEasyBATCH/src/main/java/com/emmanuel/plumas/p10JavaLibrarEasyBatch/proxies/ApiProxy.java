package com.emmanuel.plumas.p10JavaLibrarEasyBatch.proxies;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.UserEntity;

@FeignClient(name="p10JavaLibrarEasy-API",url="localhost:9001")
@Qualifier("ApiProxy")
public interface ApiProxy{
	
	@GetMapping(value="borrow/isNotReturned")
	List<BorrowEntity> getOutOfTimeAndNotReturnedBorrow();
	
	@GetMapping(value="libraryUsers")
	List<UserEntity> getAllUsers();
}
