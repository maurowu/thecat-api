package br.com.itau.cre.thecat.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CatsDataRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:list-breed").process("listAllCatsProcessorBean").end();

		from("direct:get-breed-info").process("getCatByIdProcessorBean").end();

		from("direct:breed-by-temperament").process("getCatByTemperamentProcessorBean").end();

		from("direct:breed-by-origin").process("getCatByOriginProcessorBean").end();

	}

}
