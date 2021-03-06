package br.com.itau.cre.thecat.service.impl;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.itau.cre.thecat.model.Breed;
import br.com.itau.cre.thecat.model.CatsBreeds;
import br.com.itau.cre.thecat.model.CatsImages;
import br.com.itau.cre.thecat.model.Images;
import br.com.itau.cre.thecat.repository.CatsBreedsRepository;
import br.com.itau.cre.thecat.repository.CatsImagesRepository;
import br.com.itau.cre.thecat.service.DataLoader;
import br.com.itau.cre.thecat.service.utils.LoggerUtils;
import br.com.itau.cre.thecat.service.utils.StringUtils;

@Service
public class DataLoaderImpl implements DataLoader {

	@Value("${cats.breeds.url:}")
	private String catsBreedsUrl;

	@Value("${cats.images.url:}")
	private String catsImagesUrl;

	@Value("${cats.categories.hats:}")
	private String hatsCategory;

	@Value("${cats.categories.sunglasses:}")
	private String sunglassesCategory;

	@Value("${cats.images.limit.nocategory:}")
	private String noCategoryImgLimit;

	@Value("${cats.images.limit.hats:}")
	private String hatsImgLimit;

	@Value("${cats.images.limit.sunglasses:}")
	private String sunglassesImgLimit;

	private HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CatsBreedsRepository catsBreedsRepository;

	@Autowired
	private CatsImagesRepository catsImagesRepository;

	@Autowired
	private LoggerUtils loggerUtils;

	/**
	 * Realiza a carga de raças de gatos e imagens relacionados a elas em suas respectivas tabelas.
	 */
	public boolean catsBreedsLoad() {
		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Inicio da carga das racas de gatos.");
		try {
			ResponseEntity<Breed[]> response = restTemplate.getForEntity(catsBreedsUrl, Breed[].class);
			Breed[] breedArray = response.getBody();

			if (breedArray.length == 0) {
				loggerUtils.log(Level.WARN, DataLoaderImpl.class.toString(),
						"Nao ha dados para a carga de racas de gatos");
			}
			for (Breed it : breedArray) {
				catsBreedsRepository
						.save(CatsBreeds.builder().catId(it.getId()).name(it.getName()).origin(it.getOrigin())
								.temperament(it.getTemperament()).description(it.getDescription()).build());

				this.catsImgWithNoCategoryLoad(it.getId());

			}
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, DataLoaderImpl.class.toString(),
					"Erro na carga das racas de gatos: " + StringUtils.exceptionToString(e));
			return false;
		}

		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Fim da carga das racas de gatos.");
		return true;

	}

	/**
	 * Realiza a carga de imagens por raça na tabela de imagens.
	 */
	public boolean catsImgWithNoCategoryLoad(String breedId) {
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(catsImagesUrl).queryParam("breed_id", breedId)
				.queryParam("limit", noCategoryImgLimit).build();

		try {
			ResponseEntity<Images[]> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					Images[].class);

			Images[] breedArray = response.getBody();
			if (breedArray.length == 0) {
				loggerUtils.log(Level.WARN, DataLoaderImpl.class.toString(), "Nao ha imagens para a raca: " + breedId);
			}
			for (Images it : breedArray) {
				catsImagesRepository
						.save(CatsImages.builder().imageId(it.getId()).catId(breedId).url(it.getUrl()).build());

			}
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, DataLoaderImpl.class.toString(),
					"Erro da carga das imagens sem categoria: " + StringUtils.exceptionToString(e));
			return false;
		}
		return true;
	}

	/**
	 * Realiza a carga de imagens de gatos utilizando chapeu na tabela de imagens.
	 */
	public boolean catsImgWithHatsLoad() {
		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Inicio da carga das imagens com chapeus.");
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(catsImagesUrl).queryParam("category_ids", hatsCategory)
				.queryParam("limit", hatsImgLimit).build();
		try {
			ResponseEntity<Images[]> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					Images[].class);

			Images[] breedArray = response.getBody();
			for (Images it : breedArray) {
				catsImagesRepository.save(
						CatsImages.builder().imageId(it.getId()).url(it.getUrl()).categoryId(hatsCategory).build());

			}

		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, DataLoaderImpl.class.toString(),
					"Erro da carga das imagens com chapeus: " + StringUtils.exceptionToString(e));
			return false;
		}
		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Fim da carga das imagens com chapeus.");
		return true;

	}

	/**
	 * Realiza a carga de imagens de gatos utilizando oculos de sol na tabela de imagens.
	 */
	public boolean catsImgWithSunglassesLoad() {
		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Inicio da carga das imagens com oculos.");
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(catsImagesUrl)
				.queryParam("category_ids", sunglassesCategory).queryParam("limit", sunglassesImgLimit).build();

		try {

			ResponseEntity<Images[]> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					Images[].class);
			Images[] breedArray = response.getBody();
			for (Images it : breedArray) {
				catsImagesRepository.save(CatsImages.builder().imageId(it.getId()).url(it.getUrl())
						.categoryId(sunglassesCategory).build());
			}
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, DataLoaderImpl.class.toString(),
					"Erro da carga das imagens com oculos: " + StringUtils.exceptionToString(e));
			return false;
		}
		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Fim da carga das imagens com oculos.");
		return true;

	}

	/**
	 * Realiza o expurgo da tabela de imagens.
	 */
	public boolean truncateCatsImages() {
		try {
			loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Inicio no expurgo das imagens.");
			catsImagesRepository.deleteAll();
		} catch (Exception e) {
			loggerUtils.log(Level.ERROR, DataLoaderImpl.class.toString(),
					"Erro no expurgo das imagens: " + StringUtils.exceptionToString(e));
			return false;
		}
		loggerUtils.log(Level.DEBUG, DataLoaderImpl.class.toString(), "Fim no expurgo das imagens.");
		return true;
	}

}
