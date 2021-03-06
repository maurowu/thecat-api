package br.com.itau.cre.thecat.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.itau.cre.thecat.model.LogMessage;

public interface LogRepository extends ElasticsearchRepository<LogMessage, String> {

}
