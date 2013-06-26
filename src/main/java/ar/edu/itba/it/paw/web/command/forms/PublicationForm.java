package ar.edu.itba.it.paw.web.command.forms;

import java.util.Arrays;
import java.util.List;

import ar.edu.itba.it.paw.domain.publications.Operations;
import ar.edu.itba.it.paw.domain.publications.PropertyType;
import ar.edu.itba.it.paw.domain.publications.Publication;
import ar.edu.itba.it.paw.domain.publications.PublicationRepo;
import ar.edu.itba.it.paw.domain.publications.Services;
import ar.edu.itba.it.paw.domain.users.User;

public class PublicationForm {

	private Publication publication;

	private PropertyType propertyType;
	private Operations operationType;
	private String street;
	private Integer numbering;
	private Integer floor;
	private String apartment;
	private String neighbourhood;
	private Integer price;
	private Integer age;
	private Integer coveredSurface;
	private Integer uncoveredSurface;
	private List<Services> services;
	private Integer roomsQty;
	private String description;

	public PublicationForm() {

	}

	public PublicationForm(Publication publication) {
		setPublication(publication);
		setAge(publication.getAge());
		setApartment(publication.getApartment());
		setCoveredSurface(publication.getCoveredSurface());
		setRoomsQty(publication.getRoomsQty());
		setDescription(publication.getDescription());
		setFloor(publication.getFloor());
		setNeighbourhood(publication.getNeighbourhood());
		setNumbering(publication.getNumbering());
		setOperationType(publication.getOperationType());
		setPrice(publication.getPrice());
		setPropertyType(publication.getPropertyType());
		setServices(publication.getServices());
		setStreet(publication.getStreet());
		setUncoveredSurface(publication.getUncoveredSurface());
	}

	public List<Operations> getAllOperationsType() {
		return Arrays.asList(Operations.values());
	}

	public List<PropertyType> getAllPropertyType() {
		return Arrays.asList(PropertyType.values());
	}

	public List<Services> getAllServices() {
		return Arrays.asList(Services.values());
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public Operations getOperationType() {
		return operationType;
	}

	public void setOperationType(Operations operationType) {
		this.operationType = operationType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumbering() {
		return numbering;
	}

	public void setNumbering(Integer numbering) {
		this.numbering = numbering;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCoveredSurface() {
		return coveredSurface;
	}

	public void setCoveredSurface(Integer coveredSurface) {
		this.coveredSurface = coveredSurface;
	}

	public Integer getUncoveredSurface() {
		return uncoveredSurface;
	}

	public void setUncoveredSurface(Integer uncoveredSurface) {
		this.uncoveredSurface = uncoveredSurface;
	}

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
		this.services = services;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRoomsQty() {
		return roomsQty;
	}

	public void setRoomsQty(Integer roomsQty) {
		this.roomsQty = roomsQty;
	}

	public Publication build(User user) {

		Publication publication = new Publication(user, getNeighbourhood(),
				getCoveredSurface(), getUncoveredSurface(), getRoomsQty(),
				getDescription(), getAge(), getStreet(), getNumbering(),
				getFloor(), getApartment(), getPrice(), getOperationType(),
				getPropertyType(), getServices());

		return publication;

	}

	public void update() {
		publication.setAge(getAge());
		publication.setApartment(getApartment());
		publication.setCoveredSurface(getCoveredSurface());
		publication.setRoomsQty(roomsQty);
		publication.setDescription(getDescription());
		publication.setFloor(getFloor());
		publication.setNeighbourhood(getNeighbourhood());
		publication.setNumbering(getNumbering());
		publication.setOperationType(getOperationType());
		publication.setPrice(getPrice());
		publication.setPropertyType(getPropertyType());
		publication.setServices(getServices());
		publication.setStreet(getStreet());
		publication.setUncoveredSurface(getUncoveredSurface());
	}

	public void edit(User user, PublicationRepo publicationRepo) {
		if (publication == null) {
			Publication publication = build(user);
			user.addPublication(publication);
			publicationRepo.add(publication);
			setPublication(publication);
		} else {
			update();
		}
	}

}
