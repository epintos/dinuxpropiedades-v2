package ar.edu.itba.it.paw.web.command.forms;

import java.util.Arrays;
import java.util.List;

import ar.edu.itba.it.paw.domain.publications.Publication;
import ar.edu.itba.it.paw.domain.publications.RoomsEnum;

public class AddRoomDescriptionForm {

	private Publication publication;
	private RoomsEnum selected;
	private Integer width;
	private Integer length;

	public AddRoomDescriptionForm() {
	}

	public AddRoomDescriptionForm(Publication publication) {
		setPublication(publication);
	}

	public Integer getLength() {
		return length;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public Publication getPublication() {
		return publication;
	}

	public Integer getWidth() {
		return width;
	}

	public List<RoomsEnum> getAllRooms() {
		return Arrays.asList(RoomsEnum.values());
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public RoomsEnum getSelected() {
		return selected;
	}

	public void setSelected(RoomsEnum selected) {
		this.selected = selected;
	}

}
