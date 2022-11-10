package com.hackbright.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hackbright.models.Recipe;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	List<Recipe>findAll();
	
	Optional<Recipe> findById(Long id);
	
	void deleteById(Long id);
	
	//Recipe save(Recipe recipe); for create and update
}
