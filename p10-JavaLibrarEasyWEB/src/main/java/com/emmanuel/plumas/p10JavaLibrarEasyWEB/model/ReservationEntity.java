package com.emmanuel.plumas.p10JavaLibrarEasyWEB.model;

import java.util.Date;

public class ReservationEntity {

	private Long reservationId;
	private int position;
	private Date reservationDate;
	private BookEntity bookEntity;
	private UserEntity userEntity;
	private int positionListeAttente;
	
	
	public int getPositionListeAttente() {
		return positionListeAttente;
	}

	public void setPositionListeAttente(int positionListeAttente) {
		this.positionListeAttente = positionListeAttente;
	}

	public ReservationEntity() {
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
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
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
		return "ReservationEntity [reservationId=" + reservationId + ", position=" + position + ", reservationDate="
				+ reservationDate + ", bookEntity=" + bookEntity + ", userEntity=" + userEntity + "]";
	}
	
	
	
}
