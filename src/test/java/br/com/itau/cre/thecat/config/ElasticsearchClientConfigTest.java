package br.com.itau.cre.thecat.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ElasticsearchClientConfigTest {

	private ElasticsearchClientConfig elasticsearchClientConfig;

	@Test
	public void elasticsearchTest() {
		elasticsearchClientConfig = new ElasticsearchClientConfig();
		assertNotNull(elasticsearchClientConfig.elasticsearchClient(), "erro no retorno do client");
		assertNotNull(elasticsearchClientConfig.elasticsearchTemplate(), "erro no retorno do template");
	}

}
