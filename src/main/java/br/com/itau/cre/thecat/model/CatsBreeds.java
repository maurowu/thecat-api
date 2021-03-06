package br.com.itau.cre.thecat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATS_BREEDS")
public class CatsBreeds {

	@Id
	@Column(name = "cat_id")
	private String catId;

	@Column(name = "name")
	private String name;

	@Column(name = "origin")
	private String origin;

	@Column(name = "temperament")
	private String temperament;

	@Column(name = "description")
	private String description;

}
