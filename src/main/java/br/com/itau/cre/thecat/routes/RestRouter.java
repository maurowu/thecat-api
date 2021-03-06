package br.com.itau.cre.thecat.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

@Component

public class RestRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration()
			.apiProperty("api.title", "The Cat API")
			.apiProperty("cors", "true")
			.contextPath("/thecat/api")
			.apiContextPath("/swagger") //swagger endpoint path
			.apiContextRouteId("thecat-api")
			.bindingMode(RestBindingMode.json);

		rest("/v1")
			.get("/breed")	
			.description("Get all breeds")
			.produces("application/json")
			.route()
			.to("micrometer:counter:breed.counter")
			.to("micrometer:timer:breed.timer?action=start")
			.to("direct:list-breed")
			.to("micrometer:timer:breed.timer?action=stop")
			.end()
		.endRest();
		
		rest("/v1")
			.get("/breed-info-by-id")
			.description("Find breed info by cat ID")
			.param()
				.name("catId")
				.type(RestParamType.query)
				.description("The id of the breed")
			.endParam()
			.produces("application/json")
			.route()
			.to("micrometer:counter:breed-info-by-id.counter")
			.to("micrometer:timer:breed-info-by-id.timer?action=start")
			.to("direct:get-breed-info")
			.to("micrometer:timer:breed-info-by-id.timer?action=stop")
			.end()
		.endRest();
		
		rest("/v1")
			.get("/breed-by-temperament")
			.description("Find breeds by temperament")
			.param()
				.name("temperament")
				.description("The value of the temperament")
				.type(RestParamType.query)
			.endParam()
			.produces("application/json")
			.route()
			.to("micrometer:counter:breed-info-by-temperament.counter")
			.to("micrometer:timer:breed-info-by-temperament.timer?action=start")
			.to("direct:breed-by-temperament")
			.to("micrometer:timer:breed-info-by-temperament.timer?action=stop")
			.end()
		.endRest();
		
		rest("/v1")
			.get("/breed-by-origin")
			.description("Find breeds by origin")
			.param()
				.name("origin")
				.type(RestParamType.query)
				.description("The value of the origin")
			.endParam()
			.produces("application/json")
			.route()
			.to("micrometer:counter:breed-info-by-origin.counter")
			.to("micrometer:timer:breed-info-by-origin.timer?action=start")
			.to("direct:breed-by-origin")
			.to("micrometer:timer:breed-info-by-origin.timer?action=stop")
			.end()
		.endRest();

	}

}
