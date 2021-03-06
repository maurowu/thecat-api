package br.com.itau.cre.thecat.service.impl;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.itau.cre.thecat.service.DataLoader;
import br.com.itau.cre.thecat.service.utils.LoggerUtils;

@Component
public class ScheduledTasksImpl {

	@Autowired
	private DataLoader dataLoader;

	@Autowired
	private LoggerUtils loggerUtils;

	@Value("${camel.component.servlet.mapping.contextPath:}")
	private String valor;

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron = "${process.scheduled.dataloadcron:0 */30 * ? * *}")
	public void databaseDataLoad() {
		loggerUtils.log(Level.INFO, ScheduledTasksImpl.class.toString(), "Inicio da carga do banco de dados.");
		dataLoader.truncateCatsImages();
		dataLoader.catsBreedsLoad();
		dataLoader.catsImgWithHatsLoad();
		dataLoader.catsImgWithSunglassesLoad();
		loggerUtils.log(Level.INFO, ScheduledTasksImpl.class.toString(), "Fim da carga do banco de dados.");
	}

}
