package br.com.itau.cre.thecat.service;

public interface DataLoader {

	public boolean catsBreedsLoad();

	public boolean catsImgWithNoCategoryLoad(String breedId);

	public boolean catsImgWithHatsLoad();

	public boolean catsImgWithSunglassesLoad();

	public boolean truncateCatsImages();
}
