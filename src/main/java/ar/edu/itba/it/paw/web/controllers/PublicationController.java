package ar.edu.itba.it.paw.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.photos.Photo;
import ar.edu.itba.it.paw.domain.publications.Comment;
import ar.edu.itba.it.paw.domain.publications.Publication;
import ar.edu.itba.it.paw.domain.publications.PublicationRepo;
import ar.edu.itba.it.paw.domain.publications.Room;
import ar.edu.itba.it.paw.domain.publications.Search;
import ar.edu.itba.it.paw.domain.publications.Status;
import ar.edu.itba.it.paw.domain.users.User;
import ar.edu.itba.it.paw.services.interfaces.EmailSender;
import ar.edu.itba.it.paw.web.UserManager;
import ar.edu.itba.it.paw.web.command.forms.AddRoomDescriptionForm;
import ar.edu.itba.it.paw.web.command.forms.CommentForm;
import ar.edu.itba.it.paw.web.command.forms.DeletePhotoForm;
import ar.edu.itba.it.paw.web.command.forms.NewPhotoForm;
import ar.edu.itba.it.paw.web.command.forms.PublicationForm;
import ar.edu.itba.it.paw.web.command.forms.SearchForm;
import ar.edu.itba.it.paw.web.command.validators.AddRoomDescriptionFormValidator;
import ar.edu.itba.it.paw.web.command.validators.CommentFormValidator;
import ar.edu.itba.it.paw.web.command.validators.PublicationFormValidator;
import ar.edu.itba.it.paw.web.command.validators.SearchFormValidator;

@Controller
public class PublicationController {

	private AddRoomDescriptionFormValidator descriptionFormValidator;
	private PublicationFormValidator publicationFormValidator;
	private CommentFormValidator commentFormValidator;
	private SearchFormValidator searchFormValidator;
	private PublicationRepo publicationRepo;
	private EmailSender emailSender;

	private static Integer resultsPerPage = 10;

	@Autowired
	public PublicationController(
			AddRoomDescriptionFormValidator descriptionValidator,
			PublicationFormValidator publicationFormValidator,
			CommentFormValidator commentFormValidator,
			SearchFormValidator searchFormValidator,
			PublicationRepo publicationRepo, EmailSender emailSender) {
		this.descriptionFormValidator = descriptionValidator;
		this.publicationFormValidator = publicationFormValidator;
		this.commentFormValidator = commentFormValidator;
		this.searchFormValidator = searchFormValidator;
		this.publicationRepo = publicationRepo;
		this.emailSender = emailSender;
	}

	@RequestMapping(value = "public/publications/search", method = RequestMethod.GET)
	public ModelAndView show(SearchForm searchForm, Errors errors) {
		searchFormValidator.validate(searchForm, errors);
		ModelAndView mav = new ModelAndView();
		if (errors.hasErrors()) {
			mav.setViewName("/publications/search");
			return mav;
		}

		Integer from = (searchForm.getPage() - 1) * resultsPerPage;

		Search search = searchForm.build(from, resultsPerPage);

		List<Publication> orderedList = publicationRepo.getAll(searchForm
				.build(from, resultsPerPage));
		int size = publicationRepo.getAllSize(search);
		mav.addObject("nextPage", size > from
				+ resultsPerPage);
		mav.addObject("searchForm", searchForm);
		mav.addObject("publicationsList", orderedList);
		mav.setViewName("/publications/search");
		return mav;
	}

	@RequestMapping(value = "public/publications/list", method = RequestMethod.GET)
	public ModelAndView listPublicPublications(@RequestParam("id") User user) {
		return listPublications(user, false);
	}

	@RequestMapping(value = "private/publications/list", method = RequestMethod.GET)
	public ModelAndView listPrivatePublications(HttpServletRequest req) {
		User user = ((UserManager) req.getAttribute("sessionManager"))
				.getUser();
		return listPublications(user, true);
	}

	private ModelAndView listPublications(User user, boolean isPrivate) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("isPrivate", isPrivate);
		boolean filterFinished = !isPrivate;
		List<Publication> publicationsList = publicationRepo.getAll(user,
				filterFinished);
		mav.addObject("searchForm", new SearchForm());
		mav.addObject("publicationsList", publicationsList);
		mav.setViewName("/publications/myPublications");
		return mav;

	}

	@RequestMapping(value = "private/publications/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("publicationForm", new PublicationForm());
		mav.setViewName("/publications/editPublication");
		return mav;
	}

	@RequestMapping(value = "private/publications/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") Publication publication,
			HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		User user = ((UserManager) req.getAttribute("sessionManager"))
				.getUser();

		if (!user.isMyPublication(publication)) {
			mav.setViewName("redirect:error?type=401");
			return mav;
		}

		mav.addObject("publicationForm", new PublicationForm(publication));
		mav.setViewName("/publications/editPublication");
		return mav;
	}

	@RequestMapping(value = "private/publications/edit", method = RequestMethod.POST)
	public String create(PublicationForm publicationForm, Errors errors,
			HttpServletRequest req) {
		publicationFormValidator.validate(publicationForm, errors);

		if (errors.hasErrors())
			return "/publications/editPublication";
		UserManager manager = (UserManager) req.getAttribute("sessionManager");

		publicationForm.edit(manager.getUser(), publicationRepo);
		if (!manager.getUser()
				.isMyPublication(publicationForm.getPublication())) {
			return "redirect:error?type=401";
		}

		return "redirect:../../public/publications/detail?id="
				+ publicationForm.getPublication().getId();
	}

	@RequestMapping(value = "private/publications/modifyStatus", method = RequestMethod.GET)
	public String modify(@RequestParam("id") Publication publication,
			@RequestParam("status") String status) {
		publication.modifyStatus(Status.valueOf(status.toUpperCase()));
		return "redirect:list";
	}

	@RequestMapping(value = "public/publications/detail", method = RequestMethod.GET)
	public ModelAndView viewAdvertisement(
			@RequestParam("id") Publication publication) {

		publication.increaseVisitCount();
		ModelAndView mav = new ModelAndView();
		mav.addObject("publication", publication);
		mav.addObject("commentForm", new CommentForm());
		mav.setViewName("/publications/viewPublication");
		return mav;
	}

	@RequestMapping(value = "private/publications/addRoomDescription", method = RequestMethod.GET)
	public ModelAndView addRoomDescription(
			@RequestParam("id") Publication publication) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("addRoomDescriptionForm", new AddRoomDescriptionForm(
				publication));
		mav.setViewName("/publications/addRoomDescription");
		return mav;
	}

	@RequestMapping(value = "private/publications/addRoomDescription", method = RequestMethod.POST)
	public ModelAndView saveRoomDescription(AddRoomDescriptionForm form,
			Errors errors) {

		ModelAndView mav = new ModelAndView();

		descriptionFormValidator.validate(form, errors);
		if (errors.hasErrors()) {
			mav.setViewName("/publications/addRoomDescription");
			return mav;
		}

		Publication publication = form.getPublication();
		publication.addRoom(new Room(form.getSelected(), form.getWidth(), form
				.getLength()));

		mav.addObject("commentForm", new CommentForm());
		mav.setViewName("redirect:../../public/publications/detail?id="
				+ form.getPublication().getId());
		return mav;
	}

	@RequestMapping(value = "public/publications/addComment", method = RequestMethod.POST)
	protected ModelAndView addComment(CommentForm commentForm, Errors errors,
			@RequestParam("id") Publication publication) {

		commentFormValidator.validate(commentForm, errors);
		ModelAndView mav = new ModelAndView();
		if (errors.hasErrors()) {
			mav.addObject("publication", publication);
			mav.setViewName("/publications/viewPublication");
			return mav;
		}

		Comment comment = commentForm.build();
		publication.addComment(comment);
		emailSender.sendEmail(comment,publication);

		mav.addObject("user", publication.getUser());
		mav.setViewName("/users/userInfo");
		return mav;
	}

	@RequestMapping("public/publications/viewPhoto")
	public void viewPhoto(@RequestParam("photoId") Photo photo,
			HttpServletResponse resp) throws IOException {
		ControllerUtils.RenderPhoto(resp, photo);
	}

	@RequestMapping("private/publications/editPhotos")
	public ModelAndView editPhotos(@RequestParam("id") Publication publication,
			HttpServletRequest req) {

		ModelAndView mav = new ModelAndView();

		User user = ((UserManager) req.getAttribute("sessionManager"))
				.getUser();

		if (!user.isMyPublication(publication)) {
			mav.setViewName("redirect:error?type=401");
			return mav;
		}

		List<Photo> photos;
		photos = publication.getPhotos();
		mav.addObject("id", publication.getId());
		mav.addObject("photos", photos);
		mav.addObject("newPhotoForm", new NewPhotoForm());
		mav.addObject("deletePhotoForm", new DeletePhotoForm());
		mav.setViewName("/publications/editPhotos");

		return mav;
	}

	@RequestMapping(value = "private/publications/addPhoto", method = RequestMethod.POST)
	public String addPhoto(NewPhotoForm form,
			@RequestParam("id") Publication publication) {

		List<Photo> photos = new LinkedList<Photo>();

		if (!publication.isMyPhoto(photos))
			return "redirect:error?type=401";

		photos.add(new Photo(form.getFile().getBytes(), publication));
		publication.addPhotos(photos);

		return "redirect:../../public/publications/detail?id="
				+ publication.getId();

	}

	@RequestMapping(value = "private/publications/deletePhoto", method = RequestMethod.POST)
	public String deletePhotos(@RequestParam("id") Publication publication,
			DeletePhotoForm form, Errors errors) {

		Photo[] delete = form.getPhotoId();
		if (delete == null)
			return "redirect:../../public/publications/detail?id="
					+ publication.getId();

		if (!publication.isMyPhoto(Arrays.asList(delete)))
			return "redirect:error?type=401";

		publication.deletePhotos(Arrays.asList(delete));

		return "redirect:../../public/publications/detail?id="
				+ publication.getId();

	}

	@RequestMapping("private/publications/error")
	public ModelAndView error(Integer type) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorType", type);
		mav.setViewName("/general/error");
		return mav;
	}
}
