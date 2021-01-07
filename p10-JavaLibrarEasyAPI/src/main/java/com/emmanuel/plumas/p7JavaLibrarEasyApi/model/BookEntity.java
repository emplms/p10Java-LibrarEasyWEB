package com.emmanuel.plumas.p7JavaLibrarEasyApi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
		
		
		public BookEntity(Long bookId, String bookTitle, String editor, String bookType, AuthorEntity authorEntity) {
			super();
			this.bookId = bookId;
			this.bookTitle = bookTitle;
			this.editor = editor;
			this.bookType = bookType;
			this.authorEntity = authorEntity;
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

		@Override
		public String toString() {
			return "BookEntity [bookId=" + bookId + ", bookTitle=" + bookTitle + ", editor=" + editor + ", bookType="
					+ bookType + ", authorEntity=" + authorEntity + "]";
		}		
}
