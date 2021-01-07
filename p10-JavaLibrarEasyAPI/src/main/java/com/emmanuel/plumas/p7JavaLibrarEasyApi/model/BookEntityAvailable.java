package com.emmanuel.plumas.p7JavaLibrarEasyApi.model;


public class BookEntityAvailable {
	private Long bookId;
	private String bookTitle;
	private String editor;
	private String bookType;
	private AuthorEntity authorEntity;
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

	public int getAvailableCopyNumber() {
		return availableCopyNumber;
	}

	public void setAvailableCopyNumber(int availableCopyNumber) {
		this.availableCopyNumber = availableCopyNumber;
	}

	@Override
	public String toString() {
		return "BookEntityAvailable [bookId=" + bookId + ", bookTitle=" + bookTitle + ", editor=" + editor
				+ ", bookType=" + bookType + ", authorEntity=" + authorEntity + ", availableCopyNumber="
				+ availableCopyNumber + "]";
	}	
}
