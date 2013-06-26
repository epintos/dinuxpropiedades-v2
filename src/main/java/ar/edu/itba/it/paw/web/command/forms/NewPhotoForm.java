package ar.edu.itba.it.paw.web.command.forms;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class NewPhotoForm {

	
	private CommonsMultipartFile file;
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
}
