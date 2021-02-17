package com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models;

import java.util.Date;



public class ReservationEntity {

	private long reservationId;
	private int position;
	private Date notificationDate;
	private BookEntity bookEntity;
	private UserEntity userEntity;
	
	public ReservationEntity() {
		super();
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
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

	public void setNotificationDate(Date date) {
		this.notificationDate = date;
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

	@Override
	public String toString() {
		return "ReservationEntity [reservationId=" + reservationId + ", position=" + position + ", notificationDate="
				+ notificationDate + ", bookEntity=" + bookEntity + ", userEntity=" + userEntity + "]";
	}

	
	
}
