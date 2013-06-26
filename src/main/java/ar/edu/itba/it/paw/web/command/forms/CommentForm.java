package ar.edu.itba.it.paw.web.command.forms;

import ar.edu.itba.it.paw.domain.publications.Comment;

public class CommentForm {

	private String name;
	private String email;
	private String phone;
	private Integer publicationId;
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(Integer propertyId) {
		this.publicationId = propertyId;
	}

	public Comment build(){
		Comment comment = new Comment(getComment(), getEmail(),
				getPhone(), getName());
		return comment;
	}
}
