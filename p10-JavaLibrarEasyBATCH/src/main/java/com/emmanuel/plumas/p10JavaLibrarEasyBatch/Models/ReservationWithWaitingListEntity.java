package com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models;

import java.util.Date;


public class ReservationWithWaitingListEntity {

	private Long reservationId;
	private int position;
	private Date notificationDate;
	private BookEntity bookEntity;
	private UserEntity userEntity;
	private int positionWaitingList;
	private Date dateNextReturn;
	
	
	public ReservationWithWaitingListEntity() {
		super();
	}


	public Long getReservationId() {
		return reservationId;
	}


	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public Date getNotificationDate() {
		return notificationDate;
	}


	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}


	public BookEntity getBookEntity() {
		return bookEntity;
	}


	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}


	public int getPositionWaitingList() {
		return positionWaitingList;
	}


	public void setPositionWaitingList(int positionWaitingList) {
		this.positionWaitingList = positionWaitingList;
	}


	public Date getDateNextReturn() {
		return dateNextReturn;
	}


	public void setDateNextReturn(Date dateNextReturn) {
		this.dateNextReturn = dateNextReturn;
	}


	@Override
	public String toString() {
		return "ReservationWithWaitingListEntity [reservationId=" + reservationId + ", position=" + position
				+ ", notificationDate=" + notificationDate + ", bookEntity=" + bookEntity + ", userEntity=" + userEntity
				+ ", positionWaitingList=" + positionWaitingList + ", dateNextReturn=" + dateNextReturn + "]";
	}
	
	
	
}
