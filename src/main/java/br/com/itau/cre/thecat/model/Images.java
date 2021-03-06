package br.com.itau.cre.thecat.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Images implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Breed[] breeds;
	private String id;
	private String url;
	private int width;
	private int height;

}
