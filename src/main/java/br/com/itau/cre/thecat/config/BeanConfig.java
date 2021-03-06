package br.com.itau.cre.thecat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.itau.cre.thecat.processor.GetCatByIdProcessor;
import br.com.itau.cre.thecat.processor.GetCatByOriginProcessor;
import br.com.itau.cre.thecat.processor.GetCatByTemperamentProcessor;
import br.com.itau.cre.thecat.processor.ListAllCatsProcessor;

@Configuration
public class BeanConfig {

	@Bean
	public ObjectMapper objectMapperBean() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return new ObjectMapper();
	}

	@Bean
	public RestTemplate restTemplateBean() {
		return new RestTemplate();
	}
	
	@Bean
	public GetCatByIdProcessor getCatByIdProcessorBean() {
		return new GetCatByIdProcessor();
	}
	
	@Bean
	public GetCatByOriginProcessor getCatByOriginProcessorBean() {
		return new GetCatByOriginProcessor();
	}
	
	@Bean
	public GetCatByTemperamentProcessor getCatByTemperamentProcessorBean() {
		return new GetCatByTemperamentProcessor();
	}

	@Bean
	public ListAllCatsProcessor listAllCatsProcessorBean() {
		return new ListAllCatsProcessor();
	}
	


}
