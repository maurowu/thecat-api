package br.com.itau.cre.thecat.processor;

import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.itau.cre.thecat.model.CatsBreeds;
import br.com.itau.cre.thecat.repository.CatsBreedsRepository;
import br.com.itau.cre.thecat.service.utils.LoggerUtils;
import br.com.itau.cre.thecat.service.utils.StringUtils;
import io.micrometer.core.instrument.Metrics;

@Component
public class GetCatByIdProcessor implements Processor {

	@Autowired
	private CatsBreedsRepository catsBreedsRepository;

	@Autowired
	private LoggerUtils loggerUtils;

	@Override
	public void process(Exchange exchange) throws Exception {
		loggerUtils.log(Level.DEBUG, GetCatByIdProcessor.class.toString(),
				"Inicio processamento da api /thecat/api/v1/breed-info-by-id.");
		String catId = exchange.getMessage().getHeader("catID", String.class);
		try {
			Optional<CatsBreeds> response = catsBreedsRepository.findById(catId);
			exchange.getMessage().setBody(response.get());
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, GetCatByIdProcessor.class.toString(),
					"Erro processamento da api /thecat/api/v1/breed-info-by-id catID" + catId + " - " + StringUtils.exceptionToString(e));
			Metrics.counter("breed-info-by-id.error-counter").increment();
			exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
			exchange.getMessage().setBody("Error:" + StringUtils.exceptionToString(e));
		}
		loggerUtils.log(Level.DEBUG, GetCatByIdProcessor.class.toString(), "Fim processamento da api /thecat/api/v1/breed-info-by-id.");

	}

}
