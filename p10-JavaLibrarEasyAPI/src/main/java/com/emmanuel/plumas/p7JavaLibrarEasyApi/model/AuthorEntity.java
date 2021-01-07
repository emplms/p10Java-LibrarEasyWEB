package com.emmanuel.plumas.p7JavaLibrarEasyApi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class AuthorEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long authorId;
	private String authorLastName;
	private String authorFirstName;
	
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getAuthorLastName() {
		return authorLastName;
	}
	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}
	public String getAuthorFirstName() {
		return authorFirstName;
	}
	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}
	
	public AuthorEntity() {
		super();
	}
	public AuthorEntity(Long authorId, String authorLastName, String authorFirstName) {
		super();
		this.authorId = authorId;
		this.authorLastName = authorLastName;
		this.authorFirstName = authorFirstName;
	}
	@Override
	public String toString() {
		return "AuthorEntity [authorId=" + authorId + ", authorLastName=" + authorLastName + ", authorFirstName="
				+ authorFirstName + "]";
	}
	
}
