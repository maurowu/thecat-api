package br.com.itau.cre.thecat.service.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtils {

	/**
	 * Transforma texto de excessao em String.
	 * @param e
	 * @return
	 */
	public static String exceptionToString(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

}
