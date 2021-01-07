package com.emmanuel.plumas.p7JavaLibrarEasyApi.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.BorrowEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.CopyEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.model.UserEntity;
import com.emmanuel.plumas.p7JavaLibrarEasyApi.repository.IBorrowRepository;

@Service
@Transactional
@Qualifier("BorrowService")
public class BorrowService {

	@Autowired
	@Qualifier("IBorrowRepository")
	private IBorrowRepository borrowRepository;
	
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	
	public List<BorrowEntity> getBorrowByCopyEntity(CopyEntity copyEntity) {
		List<BorrowEntity> borrowEntities=borrowRepository.getBorrowByCopyEntity(copyEntity);
		return borrowEntities;
	}
	
	
	public List<BorrowEntity> getBorrowByUserLastName(String userLastName){
		UserEntity userEntity=userService.getUserByUserLastName(userLastName);
		List<BorrowEntity> borrowEntities=borrowRepository.getBorrowByUserEntity(userEntity);
		return borrowEntities;
	}

	public void extendBorrowEndDate(Long borrowId) {
		BorrowEntity borrowEntity=borrowRepository.getBorrowByBorrowId(borrowId);
		Calendar cal=Calendar.getInstance();
		cal.setTime(borrowEntity.getEndDate());
		cal.add(Calendar.MONTH, 1);
		borrowEntity.setEndDate(cal.getTime());
		borrowEntity.setIsExtended(true);
		borrowRepository.save(borrowEntity);
	}


	public List<BorrowEntity> getOutOfTimeAndNotReturnedBorrow() {
		List<BorrowEntity> notReturnedBorrowEntities=borrowRepository.getBorrowByIsReturned(false);
		List<BorrowEntity> outOfTimeBorrowEntities=new ArrayList<BorrowEntity>();
		Date date=new Date();
		for(BorrowEntity borrowEntity: notReturnedBorrowEntities) {
			if(borrowEntity.getEndDate().compareTo(date)<0) {
				outOfTimeBorrowEntities.add(borrowEntity);
			}
		}
		return outOfTimeBorrowEntities;
	}
	
	public List<BorrowEntity> getAllBorrows(){
		List<BorrowEntity> borrowEntities=(List<BorrowEntity>) borrowRepository.findAll();
		return borrowEntities;
	}
	
	public void createBorrow(BorrowEntity borrowEntity) {
		borrowRepository.save(borrowEntity);
	}
	
	public BorrowEntity upDateBorrow(BorrowEntity borrowEntity) {
		BorrowEntity borrow=borrowRepository.getBorrowByBorrowId(borrowEntity.getBorrowId());
		borrow.setStartDate(borrowEntity.getStartDate());
		borrow.setEndDate(borrowEntity.getEndDate());
		borrow.setIsExtended(borrowEntity.getIsExtended());
		borrow.setIsReturned(borrowEntity.getIsReturned());
		borrow.setCopyEntity(borrowEntity.getCopyEntity());
		borrow.setUserEntity(borrowEntity.getUserEntity());
		borrowRepository.save(borrow);
		return borrow;
	}
	
	public void deleteBorrow(Long borrowId) {
		borrowRepository.deleteByBorrowId(borrowId);
	}
}
