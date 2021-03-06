package br.com.itau.cre.thecat.processor;

import java.util.ArrayList;
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
public class ListAllCatsProcessor implements Processor {

	@Autowired
	private CatsBreedsRepository catsBreedsRepository;

	@Autowired
	private LoggerUtils loggerUtils;

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			loggerUtils.log(Level.DEBUG, GetCatByIdProcessor.class.toString(),
					"Inicio processamento da api /thecat/api/v1/breed.");
			List<CatsBreeds> response = new ArrayList<>();
			catsBreedsRepository.findAll().forEach(value -> response.add(value));
			exchange.getMessage().setBody(response);
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, GetCatByOriginProcessor.class.toString(),
					"Erro processamento da api /thecat/api/v1/breed: " + " - " + StringUtils.exceptionToString(e));
			Metrics.counter("breed.error-counter").increment();
			exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
			exchange.getMessage().setBody("Error:" + StringUtils.exceptionToString(e));
		}
		loggerUtils.log(Level.DEBUG, GetCatByIdProcessor.class.toString(),
				"Fim processamento da api /thecat/api/v1/breed.");

	}

}
