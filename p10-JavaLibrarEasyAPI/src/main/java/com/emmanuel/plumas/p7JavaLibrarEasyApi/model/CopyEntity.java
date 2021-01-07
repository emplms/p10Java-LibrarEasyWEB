package com.emmanuel.plumas.p7JavaLibrarEasyApi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="copy")
public class CopyEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long copyId;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private BookEntity bookEntity;

	
	public CopyEntity(Long copyId, BookEntity bookEntity) {
		super();
		this.copyId = copyId;
		this.bookEntity = bookEntity;
	}

	public CopyEntity() {
		super();
	}

	public Long getCopyId() {
		return copyId;
	}

	public void setCopyId(Long copyId) {
		this.copyId = copyId;
	}

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	@Override
	public String toString() {
		return "CopyEntity [copyId=" + copyId + ", bookEntity=" + bookEntity + "]";
	}
}
