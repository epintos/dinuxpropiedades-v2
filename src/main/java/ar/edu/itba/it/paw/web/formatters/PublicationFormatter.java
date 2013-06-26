package ar.edu.itba.it.paw.web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.publications.Publication;
import ar.edu.itba.it.paw.domain.publications.PublicationRepo;

@Component
public class PublicationFormatter implements Formatter<Publication> {

	private PublicationRepo repo;

	@Autowired
	public PublicationFormatter(PublicationRepo repo) {
		this.repo = repo;
	}

	public String print(Publication arg0, Locale arg1) {
		return String.valueOf(arg0.getId());
	}

	public Publication parse(String arg0, Locale arg1) throws ParseException {
		return repo.get(Integer.valueOf(arg0));
	}

}
