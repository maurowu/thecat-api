package br.com.itau.cre.thecat.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.itau.cre.thecat.model.CatsBreeds;
import br.com.itau.cre.thecat.processor.exception.TestException;
import br.com.itau.cre.thecat.repository.CatsBreedsRepository;
import br.com.itau.cre.thecat.service.utils.LoggerUtils;

@ExtendWith(MockitoExtension.class)
public class GetCatByIdProcessorTest {

	@Mock
	private CatsBreedsRepository catsBreedsRepository;
	
	@Mock
	private LoggerUtils loggerUtils;

	@InjectMocks
	private GetCatByIdProcessor getCatByIdProcessor = new GetCatByIdProcessor();

	@Test
	public void processTest() throws Exception {
		CamelContext ctx = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(ctx);
		CatsBreeds catsBreeds = CatsBreeds.builder().catId("10").build();
		Optional<CatsBreeds> opt = Optional.of(catsBreeds);
		Mockito.when(catsBreedsRepository.findById(Mockito.any())).thenReturn(opt);
		getCatByIdProcessor.process(exchange);
		assertEquals(exchange.getMessage().getBody(), catsBreeds, "retorno de CatsBreeds incorreto");
	}

	@Test
	public void processExceptionTest() throws Exception {
		CamelContext ctx = new DefaultCamelContext();
		Exchange exchange = new DefaultExchange(ctx);
		CatsBreeds catsBreeds = CatsBreeds.builder().catId("10").build();
		Optional<CatsBreeds> opt = Optional.of(catsBreeds);
		BDDMockito.given(catsBreedsRepository.findById(Mockito.any())).willAnswer(invocation -> {
			throw new TestException("abc msg");
		});
		getCatByIdProcessor.process(exchange);
		assertEquals(exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE), 400,
				"erro no tratamento de excecao");
	}
}
