package br.com.itau.cre.thecat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableElasticsearchRepositories
public class ThecatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThecatApplication.class, args);
	}

}
