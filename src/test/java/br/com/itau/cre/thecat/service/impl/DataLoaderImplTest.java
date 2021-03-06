package br.com.itau.cre.thecat.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import br.com.itau.cre.thecat.model.Breed;
import br.com.itau.cre.thecat.model.Images;
import br.com.itau.cre.thecat.repository.CatsBreedsRepository;
import br.com.itau.cre.thecat.repository.CatsImagesRepository;
import br.com.itau.cre.thecat.service.utils.LoggerUtils;

@ExtendWith(MockitoExtension.class)
public class DataLoaderImplTest {

	@InjectMocks
	private DataLoaderImpl dataLoaderImpl = new DataLoaderImpl();

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private CatsBreedsRepository catsBreedsRepository;

	@Mock
	private CatsImagesRepository catsImagesRepository;

	@Mock
	private LoggerUtils loggerUtils;

	@Test
	public void catsBreedsLoadTest() {
		ReflectionTestUtils.setField(dataLoaderImpl, "catsBreedsUrl", "https://www.google.com");
		ReflectionTestUtils.setField(dataLoaderImpl, "catsImagesUrl", "https://www.google.com");
		Breed[] breed = new Breed[] { Breed.builder().id("10").build() };
		ResponseEntity<Breed[]> responseBreed = ResponseEntity.ok(breed);
		Mockito.when(restTemplate.getForEntity("https://www.google.com", Breed[].class)).thenReturn(responseBreed);
		Images[] images = new Images[] { Images.builder().id("10").build() };
		ResponseEntity<Images[]> responseImages = ResponseEntity.ok(images);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<Images[]>>any())).thenReturn(responseImages);
		assertTrue(dataLoaderImpl.catsBreedsLoad(), "falha no teste de carga de racas");
	}

	@Test
	public void catsImgWithHatsLoadTest() {
		ReflectionTestUtils.setField(dataLoaderImpl, "catsImagesUrl", "https://www.google.com");
		Images[] images = new Images[] { Images.builder().id("10").build() };

		ResponseEntity<Images[]> response = ResponseEntity.ok(images);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<Images[]>>any())).thenReturn(response);

		assertTrue(dataLoaderImpl.catsImgWithHatsLoad(), "falha no teste de carga de imagens de gatos com chapeu");

	}

	@Test
	public void catsImgWithSunglassesLoadTest() {
		ReflectionTestUtils.setField(dataLoaderImpl, "catsImagesUrl", "https://www.google.com");
		Images[] images = new Images[] { Images.builder().id("10").build() };

		ResponseEntity<Images[]> response = ResponseEntity.ok(images);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<Images[]>>any())).thenReturn(response);

		assertTrue(dataLoaderImpl.catsImgWithSunglassesLoad(),
				"falha no teste de carga de imagens de gatos com óculos de sol");

	}

	@Test
	public void truncateCatsImagesTest() {
		assertTrue(dataLoaderImpl.truncateCatsImages(), "falha no teste de expurgo de imagens de gatos");
	}

}
