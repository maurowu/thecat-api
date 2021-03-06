package br.com.itau.cre.thecat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.cre.thecat.model.CatsBreeds;

public interface CatsBreedsRepository extends CrudRepository<CatsBreeds, String> {

	List<CatsBreeds> findByOrigin(String origin);

	List<CatsBreeds> findByTemperamentContains(String temperament);

}
