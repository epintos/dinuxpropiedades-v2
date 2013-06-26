package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.domain.photos.Photo;

public class ControllerUtils {

	
	public static void RenderPhoto(HttpServletResponse response, Photo photo) throws IOException {
		response.setContentType("image/jpeg");
		OutputStream stream = response.getOutputStream();

		byte[] bytes = photo.getFile();
		stream.write(bytes);
	}
}
