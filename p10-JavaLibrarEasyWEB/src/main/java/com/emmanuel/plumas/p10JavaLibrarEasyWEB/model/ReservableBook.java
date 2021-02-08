package com.emmanuel.plumas.p10JavaLibrarEasyWEB.model;

import java.util.List;

public class ReservableBook {

	private Long reservableBookId;
	private String bookTitle;
	private String editor;
	private String bookType;
	private AuthorEntity authorEntity;
	private List<ReservationWithWaitingListEntity> reservationWithWaitingListEntities;
	private int availableCopyNumber;
	private Boolean isReservable;
	
	public ReservableBook() {
		super();
	}

	public Long getReservableBookId() {
		return reservableBookId;
	}

	public void setReservableBookId(Long reservableBookId) {
		this.reservableBookId = reservableBookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public AuthorEntity getAuthorEntity() {
		return authorEntity;
	}

	public void setAuthorEntity(AuthorEntity authorEntity) {
		this.authorEntity = authorEntity;
	}

	public List<ReservationWithWaitingListEntity> getReservationWithWaitingListEntities() {
		return reservationWithWaitingListEntities;
	}

	public void setReservationWithWaitingListEntities(
			List<ReservationWithWaitingListEntity> reservationWithWaitingListEntities) {
		this.reservationWithWaitingListEntities = reservationWithWaitingListEntities;
	}

	public int getAvailableCopyNumber() {
		return availableCopyNumber;
	}

	public void setAvailableCopyNumber(int availableCopyNumber) {
		this.availableCopyNumber = availableCopyNumber;
	}

	public Boolean getIsReservable() {
		return isReservable;
	}

	public void setIsReservable(Boolean isReservable) {
		this.isReservable = isReservable;
	}

	@Override
	public String toString() {
		return "ReservableBook [reservableBookId=" + reservableBookId + ", bookTitle=" + bookTitle + ", editor="
				+ editor + ", bookType=" + bookType + ", authorEntity=" + authorEntity
				+ ", reservationWithWaitingListEntities=" + reservationWithWaitingListEntities
				+ ", availableCopyNumber=" + availableCopyNumber + ", isReservable=" + isReservable + "]";
	}
	
	
	
	
}
