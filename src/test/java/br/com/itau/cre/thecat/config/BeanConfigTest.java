package br.com.itau.cre.thecat.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeanConfigTest {

	private BeanConfig beanConfig = new BeanConfig();

	@Test
	public void beanConfigTest() {

		Assertions.assertThatCode(() -> beanConfig.objectMapperBean()).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> beanConfig.restTemplateBean()).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> beanConfig.getCatByIdProcessorBean()).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> beanConfig.getCatByOriginProcessorBean()).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> beanConfig.getCatByTemperamentProcessorBean()).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> beanConfig.listAllCatsProcessorBean()).doesNotThrowAnyException();

	}

}
