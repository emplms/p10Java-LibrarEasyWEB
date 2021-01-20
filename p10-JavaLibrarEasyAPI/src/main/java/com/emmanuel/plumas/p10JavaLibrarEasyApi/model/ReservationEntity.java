package com.emmanuel.plumas.p10JavaLibrarEasyApi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class ReservationEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long reservationId;
	@GeneratedValue
	private int position;
	private Date notificationDate;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private BookEntity bookEntity;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity userEntity;

	
	
	public ReservationEntity() {
		super();
	}

	public ReservationEntity(long reservationId, int position, Date notificationDate, BookEntity bookEntity,
			UserEntity userEntity) {
		super();
		this.reservationId = reservationId;
		this.position = position;
		this.notificationDate = notificationDate;
		this.bookEntity = bookEntity;
		this.userEntity = userEntity;
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

}
