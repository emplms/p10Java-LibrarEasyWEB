package com.emmanuel.plumas.p10JavaLibrarEasyApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BookEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.BorrowEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.CopyEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.ReservationWithWaitingListEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.model.UserEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyApi.repository.IReservationRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
	
	@Mock
	IReservationRepository reservationRepository;
	
	@Mock
	BorrowService borrowService;
	
	ReservationService reservationService;
	@BeforeEach
	void init() {
		reservationService=new ReservationService();
	}
	
	
	@Test
	void testTransformationOfReservationEntitiesInToReservationWithWaitingListEntities() {
		
		Date dateNextReturn=new Date();
		ReservationWithWaitingListEntity reservationWithWaitingListEntity=new ReservationWithWaitingListEntity();
		
		ReservationEntity reservationEntity1= new ReservationEntity();
		reservationEntity1.setBookEntity(null);
		reservationEntity1.setUserEntity(null);
		reservationEntity1.setNotificationDate(null);
		reservationEntity1.setPosition(1);
		
		ReservationEntity reservationEntity2= new ReservationEntity();
		reservationEntity2.setBookEntity(null);
		reservationEntity2.setUserEntity(null);
		reservationEntity2.setNotificationDate(null);
		reservationEntity2.setPosition(2);
		
		ReservationEntity reservationEntity3= new ReservationEntity();
		reservationEntity3.setBookEntity(null);
		reservationEntity3.setUserEntity(null);
		reservationEntity3.setNotificationDate(null);
		reservationEntity3.setPosition(3);
		
		List<ReservationEntity> reservationEntities=new ArrayList<ReservationEntity>();
		reservationEntities.add(reservationEntity1);
		reservationEntities.add(reservationEntity2);
		reservationEntities.add(reservationEntity3);
		
		List<ReservationWithWaitingListEntity> reservationWithWaitingListEntitiesResult=new ArrayList<ReservationWithWaitingListEntity>();
		
		reservationWithWaitingListEntitiesResult=reservationService.transformReservationEntitiesToReservationWithWaitingListEntities(reservationEntities, dateNextReturn);
				
		
		assertThat(reservationWithWaitingListEntitiesResult.size()).isEqualTo(3);
		assertThat(reservationWithWaitingListEntitiesResult.get(0)).hasSameClassAs(reservationWithWaitingListEntity);
		assertThat(reservationWithWaitingListEntitiesResult.get(1)).hasSameClassAs(reservationWithWaitingListEntity);
		assertThat(reservationWithWaitingListEntitiesResult.get(2)).hasSameClassAs(reservationWithWaitingListEntity);
		assertThat(reservationWithWaitingListEntitiesResult.get(0).getPosition()).isEqualTo(1);
		assertThat(reservationWithWaitingListEntitiesResult.get(1).getPosition()).isEqualTo(2);
		assertThat(reservationWithWaitingListEntitiesResult.get(2).getPosition()).isEqualTo(3);
		assertThat(reservationWithWaitingListEntitiesResult.get(0).getDateNextReturn()).isEqualToIgnoringHours(dateNextReturn);
		assertThat(reservationWithWaitingListEntitiesResult.get(1).getDateNextReturn()).isEqualToIgnoringHours(dateNextReturn);
		assertThat(reservationWithWaitingListEntitiesResult.get(2).getDateNextReturn()).isEqualToIgnoringHours(dateNextReturn);
		assertThat(reservationWithWaitingListEntitiesResult.get(0).getPositionWaitingList()).isEqualTo(0);
		assertThat(reservationWithWaitingListEntitiesResult.get(1).getPositionWaitingList()).isEqualTo(0);
		assertThat(reservationWithWaitingListEntitiesResult.get(2).getPositionWaitingList()).isEqualTo(0);
	}
	
	@Test
	void calculatePositionWaitingListTest() {
		
		UserEntity userEntity1= new UserEntity();
		userEntity1.setUserLastName("milka");
		UserEntity userEntity2= new UserEntity();
		userEntity2.setUserLastName("sherlock");
		UserEntity userEntity3= new UserEntity();
		userEntity3.setUserLastName("eliot");
		UserEntity userEntity4= new UserEntity();
		userEntity4.setUserLastName("emmanuel");
		
		ReservationEntity reservationEntity1= new ReservationEntity();
		reservationEntity1.setBookEntity(null);
		reservationEntity1.setUserEntity(userEntity1);
		reservationEntity1.setNotificationDate(null);
		reservationEntity1.setPosition(1);
		
		ReservationEntity reservationEntity2= new ReservationEntity();
		reservationEntity2.setBookEntity(null);
		reservationEntity2.setUserEntity(userEntity2);
		reservationEntity2.setNotificationDate(null);
		reservationEntity2.setPosition(7);
		
		ReservationEntity reservationEntity3= new ReservationEntity();
		reservationEntity3.setBookEntity(null);
		reservationEntity3.setUserEntity(userEntity4);
		reservationEntity3.setNotificationDate(null);
		reservationEntity3.setPosition(3);
		
		ReservationEntity reservationEntity4= new ReservationEntity();
		reservationEntity4.setBookEntity(null);
		reservationEntity4.setUserEntity(userEntity3);
		reservationEntity4.setNotificationDate(null);
		reservationEntity4.setPosition(8);
		
		List<ReservationEntity> reservationEntities=new ArrayList<ReservationEntity>();
		reservationEntities.add(reservationEntity1);
		reservationEntities.add(reservationEntity2);
		reservationEntities.add(reservationEntity3);
		reservationEntities.add(reservationEntity4);
		
		
		ReservationWithWaitingListEntity reservationWithWaitingListEntity = new ReservationWithWaitingListEntity();
		reservationWithWaitingListEntity.setBookEntity(null);
		reservationWithWaitingListEntity.setUserEntity(userEntity4);
		reservationWithWaitingListEntity.setPosition(2);
		
		assertEquals(2,reservationService.calculatePositionWaitingList(reservationEntities,reservationWithWaitingListEntity));
	}
	
	
	@Test
	void calculateNextReturnDateWithTwoEqualsDatesTest() {

		
		List<BorrowEntity> borrowEntities=new ArrayList<BorrowEntity>();
		
				
		BorrowEntity borrowEntity1=new BorrowEntity();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE,4);
		Date endDate1=cal1.getTime();
		borrowEntity1.setEndDate(endDate1);
		borrowEntities.add(borrowEntity1);
		
		BorrowEntity borrowEntity2=new BorrowEntity();
		Calendar cal2=Calendar.getInstance();
		cal2.add(Calendar.DATE,3);
		Date endDate2=cal2.getTime();
		borrowEntity2.setEndDate(endDate2);
		borrowEntities.add(borrowEntity2);
		
		BorrowEntity borrowEntity3=new BorrowEntity();
		Calendar cal3=Calendar.getInstance();
		cal3.add(Calendar.DATE,2);
		Date endDate3=cal3.getTime();
		borrowEntity3.setEndDate(endDate3);
		borrowEntities.add(borrowEntity3);
		
		BorrowEntity borrowEntity4=new BorrowEntity();
		Calendar cal4=Calendar.getInstance();
		cal4.add(Calendar.DATE,4);
		Date endDate4=cal4.getTime();
		borrowEntity4.setEndDate(endDate4);
		borrowEntities.add(borrowEntity4);
		
		Calendar realCal=Calendar.getInstance();
		realCal.add(Calendar.DATE,2);
		Date realEndDate=realCal.getTime();
		assertEquals(realEndDate,reservationService.calculateNextReturnDate(borrowEntities));
		
	}
	
	@Test
	void calculateNextReturnDateWithNoEqualDateTest() {

		
		List<BorrowEntity> borrowEntities=new ArrayList<BorrowEntity>();
		
				
		BorrowEntity borrowEntity1=new BorrowEntity();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE,4);
		Date endDate1=cal1.getTime();
		borrowEntity1.setEndDate(endDate1);
		borrowEntities.add(borrowEntity1);
		
		BorrowEntity borrowEntity2=new BorrowEntity();
		Calendar cal2=Calendar.getInstance();
		cal2.add(Calendar.DATE,3);
		Date endDate2=cal2.getTime();
		borrowEntity2.setEndDate(endDate2);
		borrowEntities.add(borrowEntity2);
		
		BorrowEntity borrowEntity3=new BorrowEntity();
		Calendar cal3=Calendar.getInstance();
		cal3.add(Calendar.DATE,2);
		Date endDate3=cal3.getTime();
		borrowEntity3.setEndDate(endDate3);
		borrowEntities.add(borrowEntity3);
		
		BorrowEntity borrowEntity4=new BorrowEntity();
		Calendar cal4=Calendar.getInstance();
		cal4.add(Calendar.DATE,1);
		Date endDate4=cal4.getTime();
		borrowEntity4.setEndDate(endDate4);
		borrowEntities.add(borrowEntity4);
		
		Calendar realCal=Calendar.getInstance();
		realCal.add(Calendar.DATE,1);
		Date realEndDate=realCal.getTime();
		assertEquals(realEndDate,reservationService.calculateNextReturnDate(borrowEntities));
		
	}
	
	@Test
	void calculateNextReturnDateWithReturnDateInThePastTest() {

		
		List<BorrowEntity> borrowEntities=new ArrayList<BorrowEntity>();
		
				
		BorrowEntity borrowEntity1=new BorrowEntity();
		Calendar cal1=Calendar.getInstance();
		cal1.add(Calendar.DATE,-1);
		Date endDate1=cal1.getTime();
		borrowEntity1.setEndDate(endDate1);
		borrowEntities.add(borrowEntity1);
		
		BorrowEntity borrowEntity2=new BorrowEntity();
		Calendar cal2=Calendar.getInstance();
		cal2.add(Calendar.DATE,3);
		Date endDate2=cal2.getTime();
		borrowEntity2.setEndDate(endDate2);
		borrowEntities.add(borrowEntity2);
		
		BorrowEntity borrowEntity3=new BorrowEntity();
		Calendar cal3=Calendar.getInstance();
		cal3.add(Calendar.DATE,2);
		Date endDate3=cal3.getTime();
		borrowEntity3.setEndDate(endDate3);
		borrowEntities.add(borrowEntity3);
		
		BorrowEntity borrowEntity4=new BorrowEntity();
		Calendar cal4=Calendar.getInstance();
		cal4.add(Calendar.DATE,1);
		Date endDate4=cal4.getTime();
		borrowEntity4.setEndDate(endDate4);
		borrowEntities.add(borrowEntity4);
		
		Calendar realCal=Calendar.getInstance();
		realCal.add(Calendar.DATE, -1);
		Date realEndDate=realCal.getTime();
		assertEquals(realEndDate,reservationService.calculateNextReturnDate(borrowEntities));
		
	}
	
	@Test
	void testVerifyIfBookIsReservedByUserWhenBookIsAlreadyReserved() {
		reservationService.setReservationRepository(reservationRepository);
		
		String userLastName="Emmanuel";
		
		BookEntity bookEntity1= new BookEntity();
		bookEntity1.setBookId((long)1);
		
		BookEntity bookEntity2= new BookEntity();
		bookEntity2.setBookId((long)2);
		
		
		UserEntity userEntity1=new UserEntity();
		userEntity1.setUserLastName(userLastName);
		
		UserEntity userEntity2=new UserEntity();
		userEntity2.setUserLastName("un autre");
		
		List<ReservationEntity> reservationEntities=new ArrayList<ReservationEntity>();
		
		ReservationEntity reservationEntity1= new ReservationEntity();
		reservationEntity1.setReservationId((long)1);
		reservationEntity1.setBookEntity(bookEntity1);
		reservationEntity1.setUserEntity(userEntity1);
		reservationEntities.add(reservationEntity1);
		
		ReservationEntity reservationEntity2= new ReservationEntity();
		reservationEntity2.setReservationId((long)2);
		reservationEntity2.setBookEntity(bookEntity1);
		reservationEntity2.setUserEntity(userEntity2);
		reservationEntities.add(reservationEntity2);
		
		bookEntity1.setReservationEntities(reservationEntities);
		
		
		
		when(reservationRepository.getReservationByBookEntity(bookEntity1)).thenReturn(reservationEntities);
		Boolean isReserved=reservationService.verifyBookIsReservedByUser(userLastName,bookEntity1);
		
		verify(reservationRepository).getReservationByBookEntity(bookEntity1);
		assertEquals(true,isReserved);
		
	}
	
	@Test
	void testVerifyIfBookIsReservedByUserWhenBookIsReservedByAnotherUserButNotByOurUser() {
		reservationService.setReservationRepository(reservationRepository);
		
		String userLastName="Emmanuel";
		
		BookEntity bookEntity1= new BookEntity();
		bookEntity1.setBookId((long)1);
		
		BookEntity bookEntity2= new BookEntity();
		bookEntity2.setBookId((long)2);
		
		
		UserEntity userEntity1=new UserEntity();
		userEntity1.setUserLastName(userLastName);
		
		UserEntity userEntity2=new UserEntity();
		userEntity2.setUserLastName("un autre");
		
		List<ReservationEntity> reservationEntities1=new ArrayList<ReservationEntity>();
		List<ReservationEntity> reservationEntities2=new ArrayList<ReservationEntity>();
		
		ReservationEntity reservationEntity1= new ReservationEntity();
		reservationEntity1.setReservationId((long)1);
		reservationEntity1.setBookEntity(bookEntity1);
		reservationEntity1.setUserEntity(userEntity2);
		reservationEntities1.add(reservationEntity1);
		
		ReservationEntity reservationEntity2= new ReservationEntity();
		reservationEntity2.setReservationId((long)2);
		reservationEntity2.setBookEntity(bookEntity2);
		reservationEntity2.setUserEntity(userEntity2);
		reservationEntities2.add(reservationEntity2);
		
		bookEntity1.setReservationEntities(reservationEntities1);
		bookEntity2.setReservationEntities(reservationEntities2);
		
		
		when(reservationRepository.getReservationByBookEntity(bookEntity1)).thenReturn(reservationEntities1);
		Boolean isReserved=reservationService.verifyBookIsReservedByUser(userLastName,bookEntity1);
		
		verify(reservationRepository).getReservationByBookEntity(bookEntity1);
		assertEquals(false,isReserved);
		
	}
	@Test
	void testVerifyIfBookIsReservedByUserWhenAnotherBookIsAlreadyReserved() {
		reservationService.setReservationRepository(reservationRepository);
		
		String userLastName="Emmanuel";
		
		BookEntity bookEntity1= new BookEntity();
		bookEntity1.setBookId((long)1);
		
		BookEntity bookEntity2= new BookEntity();
		bookEntity2.setBookId((long)2);
		
		
		UserEntity userEntity1=new UserEntity();
		userEntity1.setUserLastName(userLastName);
		
		UserEntity userEntity2=new UserEntity();
		userEntity2.setUserLastName("un autre");
		
		List<ReservationEntity> reservationEntities1=new ArrayList<ReservationEntity>();
		List<ReservationEntity> reservationEntities2=new ArrayList<ReservationEntity>();
		
		ReservationEntity reservationEntity1= new ReservationEntity();
		reservationEntity1.setReservationId((long)1);
		reservationEntity1.setBookEntity(bookEntity1);
		reservationEntity1.setUserEntity(userEntity2);
		reservationEntities1.add(reservationEntity1);
		
		ReservationEntity reservationEntity2= new ReservationEntity();
		reservationEntity2.setReservationId((long)2);
		reservationEntity2.setBookEntity(bookEntity2);
		reservationEntity2.setUserEntity(userEntity1);
		reservationEntities2.add(reservationEntity2);
		
		bookEntity1.setReservationEntities(reservationEntities1);
		bookEntity2.setReservationEntities(reservationEntities2);
		
		
		when(reservationRepository.getReservationByBookEntity(bookEntity1)).thenReturn(reservationEntities1);
		Boolean isReserved=reservationService.verifyBookIsReservedByUser(userLastName,bookEntity1);
		
		verify(reservationRepository).getReservationByBookEntity(bookEntity1);
		assertEquals(false,isReserved);
		
	}
	
	
	@Test
	void testVerifyIfBookIsAlreadyBorrowedByUserWhenBookIsAlreadyBorrowedByUser() {
		reservationService.setBorrowService(borrowService);
				
		UserEntity userEntity1= new UserEntity();
		userEntity1.setUserLastName("Emmanuel");
		
		UserEntity userEntity2= new UserEntity();
		userEntity2.setUserLastName("Un autre");
		
		BookEntity bookEntity1=new BookEntity();
		bookEntity1.setBookId((long)1);
		
		CopyEntity copyEntity1= new CopyEntity();
		copyEntity1.setCopyId((long)1);
		copyEntity1.setBookEntity(bookEntity1);
		
		CopyEntity copyEntity2= new CopyEntity();
		copyEntity2.setCopyId((long)2);
		copyEntity2.setBookEntity(bookEntity1);
		
		List<BorrowEntity> borrowEntities=new ArrayList<BorrowEntity>();
		
		BorrowEntity borrowEntity1=new BorrowEntity();
		borrowEntity1.setBorrowId((long)1);
		borrowEntity1.setCopyEntity(copyEntity1);
		borrowEntity1.setUserEntity(userEntity1);
		borrowEntities.add(borrowEntity1);
		
		BorrowEntity borrowEntity2=new BorrowEntity();
		borrowEntity2.setBorrowId((long)2);
		borrowEntity2.setCopyEntity(copyEntity2);
		borrowEntity2.setUserEntity(userEntity2);
		borrowEntities.add(borrowEntity2);
		
		when(borrowService.getBorrowByBookId(bookEntity1.getBookId())).thenReturn(borrowEntities);
		Boolean isBorrowed=reservationService.verifyBookIsBorrowedByUser("Emmanuel", bookEntity1);
		
		verify(borrowService).getBorrowByBookId(bookEntity1.getBookId());
		assertEquals(true,isBorrowed);
	}
	
	@Test
	void testVerifyIfBookIsAlreadyBorrowedByUserWhenBookIsNotBorrowedByUser() {
		reservationService.setBorrowService(borrowService);
				
		UserEntity userEntity1= new UserEntity();
		userEntity1.setUserLastName("Emmanuel");
		
		UserEntity userEntity2= new UserEntity();
		userEntity2.setUserLastName("Un autre");
		
		BookEntity bookEntity1=new BookEntity();
		bookEntity1.setBookId((long)1);
		
		CopyEntity copyEntity1= new CopyEntity();
		copyEntity1.setCopyId((long)1);
		copyEntity1.setBookEntity(bookEntity1);
		
		CopyEntity copyEntity2= new CopyEntity();
		copyEntity2.setCopyId((long)2);
		copyEntity2.setBookEntity(bookEntity1);
		
		List<BorrowEntity> borrowEntities=new ArrayList<BorrowEntity>();
		
		BorrowEntity borrowEntity1=new BorrowEntity();
		borrowEntity1.setBorrowId((long)1);
		borrowEntity1.setCopyEntity(copyEntity1);
		borrowEntity1.setUserEntity(userEntity2);
		borrowEntities.add(borrowEntity1);
		
		when(borrowService.getBorrowByBookId(bookEntity1.getBookId())).thenReturn(borrowEntities);
		Boolean isBorrowed=reservationService.verifyBookIsBorrowedByUser("Emmanuel", bookEntity1);
		
		verify(borrowService).getBorrowByBookId(bookEntity1.getBookId());
		assertEquals(false,isBorrowed);
	}
	
}
 