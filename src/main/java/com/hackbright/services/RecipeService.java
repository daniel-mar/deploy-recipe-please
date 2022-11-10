package com.hackbright.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hackbright.models.Recipe;
import com.hackbright.repositories.RecipeRepository;

@Service
public class RecipeService {
	
	//using dependency injection rather than @Autowire
	private final RecipeRepository recipeRepo;
	
	//construct
	public RecipeService(RecipeRepository recipeRepo) {
		this.recipeRepo = recipeRepo;
	}

	//Create Recipe
	public Recipe createRecipe(Recipe recipe) {
		return recipeRepo.save(recipe);
	}
	
	//Update Recipe
		public Recipe updateRecipe(Recipe recipe) {
			return recipeRepo.save(recipe);
		}

	//Show all Recipe
	public List<Recipe> getAllRecipes(){
		return recipeRepo.findAll();
	}
	
	//Show or find one Recipe ---this is the way to do it! use optional
	public Recipe findOneRecipe(Long id) {
		Optional<Recipe>optionalRecipe = recipeRepo.findById(id);
		if(optionalRecipe.isPresent()) {
			return optionalRecipe.get();
		}else {
			return null;
		}
	}

	//Delete One
	public void deleteRecipe(Long id) {
		recipeRepo.deleteById(id);
	}
}