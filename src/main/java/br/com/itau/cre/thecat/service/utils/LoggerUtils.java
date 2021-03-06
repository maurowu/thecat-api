package br.com.itau.cre.thecat.service.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.itau.cre.thecat.model.LogMessage;
import br.com.itau.cre.thecat.repository.LogRepository;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class LoggerUtils {

	@Autowired
	private LogRepository logRepository;

	/**
	 * Envia log para o Elasticsearch.
	 * @param level
	 * @param sourceClass
	 * @param message
	 */
	@Async
	public void log(Level level, String sourceClass, String message) {
		try {
			logRepository
					.save(LogMessage.builder().id(UUID.randomUUID().toString()).dateTime(LocalDateTime.now().toString())
							.logLevel(level.toString()).sourceClass(sourceClass).message(message).build());
		} catch (Exception e) {
			// erro de log na ferramenta, realiza log no servico
			log.error("Erro no envio do log: " + e);
			log.error(level + " - " + sourceClass + " : " + message);
		}

	}

}
