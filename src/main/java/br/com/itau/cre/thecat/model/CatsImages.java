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
@Table(name = "CATS_IMAGES")
public class CatsImages {
	
	@Id
	@Column(name = "image_id")
	private String imageId;

	@Column(name = "cat_id")
	private String catId;
	
	@Column(name = "category_id")
	private String categoryId;

	@Column(name = "image_url")
	private String url;



}
