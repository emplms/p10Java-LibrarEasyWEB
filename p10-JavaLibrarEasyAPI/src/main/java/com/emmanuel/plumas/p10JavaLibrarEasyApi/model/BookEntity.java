package com.emmanuel.plumas.p10JavaLibrarEasyApi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="book")
public class BookEntity implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue
		private Long bookId;
		private String bookTitle;
		private String editor;
		private String bookType;
		
		@ManyToOne 
		@JoinColumn(name="author_id")
		private AuthorEntity authorEntity;
		
		@OneToMany(mappedBy="bookEntity")
		@JsonIgnoreProperties("bookEntity")
		private List<ReservationEntity> reservationEntities;  
		

		public BookEntity(Long bookId, String bookTitle, String editor, String bookType, AuthorEntity authorEntity,
				List<ReservationEntity> reservationEntities) {
			super();
			this.bookId = bookId;
			this.bookTitle = bookTitle;
			this.editor = editor;
			this.bookType = bookType;
			this.authorEntity = authorEntity;
			this.reservationEntities = reservationEntities;
		}

		public BookEntity() {
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

		
		
		public List<ReservationEntity> getReservationEntities() {
			return reservationEntities;
		}

		public void setReservationEntities(List<ReservationEntity> reservationEntities) {
			this.reservationEntities = reservationEntities;
		}

		@Override
		public String toString() {
			return "BookEntity [bookId=" + bookId + ", bookTitle=" + bookTitle + ", editor=" + editor + ", bookType="
					+ bookType + ", authorEntity=" + authorEntity + ", reservationEntities=" + reservationEntities
					+ "]";
		}		
}
