package com.emmanuel.plumas.p10JavaLibrarEasyApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.repository.IBorrowRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BorrowServiceTest {

	@Mock
	IBorrowRepository borrowRepository;
	
	BorrowService borrowService;
	
	@BeforeEach
	void init() {
		borrowService=new BorrowService();
	}
	
	@Test
	void extendBorrowWithNotFinishedBorrowTest() {
		//GIVEN
		borrowService.setBorrowRepository(borrowRepository);
		BorrowEntity borrowEntity=new BorrowEntity();
		borrowEntity.setBorrowId((long) 1);
		Calendar calStart=Calendar.getInstance();
		borrowEntity.setStartDate(calStart.getTime());
		Calendar calEnd=Calendar.getInstance();
		calEnd.add(Calendar.MONTH,1);
		borrowEntity.setEndDate(calEnd.getTime());
		borrowEntity.setIsExtended(false);
		borrowEntity.setIsReturned(false);
		
		when(borrowRepository.getBorrowByBorrowId((long)(1))).thenReturn(borrowEntity);
		
		//WHEN
		borrowService.extendBorrowEndDate((long)1);
		
		//THEN
		verify(borrowRepository).getBorrowByBorrowId((long)1);
		assertThat(borrowEntity.getIsExtended()).isEqualTo(true);
	}
	
	@Test
	void extendBorrowWithFinishedBorrowTest() {
		//GIVEN
		borrowService.setBorrowRepository(borrowRepository);
		BorrowEntity borrowEntity=new BorrowEntity();
		borrowEntity.setBorrowId((long) 1);
		Calendar calStart=Calendar.getInstance();
		calStart.add(Calendar.MONTH, -2);
		borrowEntity.setStartDate(calStart.getTime());
		Calendar calEnd=Calendar.getInstance();
		calEnd.add(Calendar.MONTH,-1);
		borrowEntity.setEndDate(calEnd.getTime());
		borrowEntity.setIsExtended(false);
		borrowEntity.setIsReturned(false);
		
		when(borrowRepository.getBorrowByBorrowId((long)(1))).thenReturn(borrowEntity);
		
		//WHEN
		borrowService.extendBorrowEndDate((long)1);
		
		//THEN
		verify(borrowRepository).getBorrowByBorrowId((long)1);
		assertThat(borrowEntity.getIsExtended()).isEqualTo(false);
	}
}
