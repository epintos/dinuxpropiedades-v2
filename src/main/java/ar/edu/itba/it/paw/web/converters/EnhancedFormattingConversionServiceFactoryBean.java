package ar.edu.itba.it.paw.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import ar.edu.itba.it.paw.domain.publications.Publication;
import ar.edu.itba.it.paw.web.formatters.PublicationFormatter;

public class EnhancedFormattingConversionServiceFactoryBean extends
		FormattingConversionServiceFactoryBean {

	private Converter<?, ?>[] converters;
	private PublicationFormatter publicationFormatter;

	@Autowired
	public EnhancedFormattingConversionServiceFactoryBean(
			Converter<?, ?>[] converters,
			PublicationFormatter publicationFormatter) {
		this.converters = converters;
		this.publicationFormatter = publicationFormatter;

	}

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		for (Converter<?, ?> c : converters) {
			registry.addConverter(c);
		}

		registry.addFormatterForFieldType(Publication.class,
				publicationFormatter);
	}

}
