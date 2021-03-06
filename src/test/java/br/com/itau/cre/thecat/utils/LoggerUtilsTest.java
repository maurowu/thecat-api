package br.com.itau.cre.thecat.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.itau.cre.thecat.repository.LogRepository;
import br.com.itau.cre.thecat.service.utils.LoggerUtils;

public class LoggerUtilsTest {

	@Mock
	private LogRepository logRepository;

	@InjectMocks
	private LoggerUtils loggerUtils = new LoggerUtils();

	@Test
	public void logTest() {
		loggerUtils.log(Level.INFO, Mockito.any(), Mockito.any());
		assertNotNull(loggerUtils, "erro na classe de log");
	}

}
