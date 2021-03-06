package br.com.itau.cre.thecat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "thecatlog", createIndex = true)
public class LogMessage {

	@Id
	private String id;
	private String dateTime;
	private String logLevel;
	private String sourceClass;
	private String message;

}
