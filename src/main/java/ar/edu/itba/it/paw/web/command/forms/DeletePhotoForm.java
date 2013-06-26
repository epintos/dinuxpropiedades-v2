package ar.edu.itba.it.paw.web.command.forms;

import ar.edu.itba.it.paw.domain.photos.Photo;


public class DeletePhotoForm {

	private Photo[] photoId;
	
	public Photo[] getPhotoId() {
		return photoId;
	}
	
	public void setPhotoId(Photo[] photoId) {
		this.photoId = photoId;
	}
	
}
