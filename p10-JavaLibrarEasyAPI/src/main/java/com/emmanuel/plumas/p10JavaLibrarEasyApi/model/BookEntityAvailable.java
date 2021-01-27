package com.emmanuel.plumas.p10JavaLibrarEasyApi.model;

import java.util.List;

public class BookEntityAvailable {
	private Long bookId;
	private String bookTitle;
	private String editor;
	private String bookType;
	private AuthorEntity authorEntity;
	private List<ReservationWithWaitingListEntity> reservationWithWaitingListEntities;
	private int availableCopyNumber;
	
	
	public BookEntityAvailable() {
		super();
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
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

	@Override
	public String toString() {
		return "BookEntityAvailable [bookId=" + bookId + ", bookTitle=" + bookTitle + ", editor=" + editor
				+ ", bookType=" + bookType + ", authorEntity=" + authorEntity + ", reservationWithWaitingListEntities="
				+ reservationWithWaitingListEntities + ", availableCopyNumber=" + availableCopyNumber + "]";
	}	
}
