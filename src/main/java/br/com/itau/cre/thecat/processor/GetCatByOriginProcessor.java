package br.com.itau.cre.thecat.processor;

import java.util.List;

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
public class GetCatByOriginProcessor implements Processor {

	@Autowired
	private CatsBreedsRepository catsBreedsRepository;

	@Autowired
	private LoggerUtils loggerUtils;

	@Override
	public void process(Exchange exchange) throws Exception {
		loggerUtils.log(Level.DEBUG, GetCatByOriginProcessor.class.toString(),
				"Inicio processamento da api /thecat/api/v1/breed-by-origin.");
		String origin = exchange.getMessage().getHeader("origin", String.class);
		try {
			List<CatsBreeds> response = catsBreedsRepository.findByOrigin(origin);
			exchange.getMessage().setBody(response);
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, GetCatByOriginProcessor.class.toString(),
					"Erro processamento da api /thecat/api/v1/breed-by-origin origin: " + origin + " - "
							+ StringUtils.exceptionToString(e));
			Metrics.counter("breed-info-by-origin.error-counter").increment();
			exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
			exchange.getMessage().setBody("Error:" + StringUtils.exceptionToString(e));
		}
		loggerUtils.log(Level.DEBUG, GetCatByOriginProcessor.class.toString(),
				"Fim processamento da api /thecat/api/v1/breed-by-origin.");

	}

}
