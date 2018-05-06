package recipe.services.pckg;

import java.util.List;

import recipe.buisnessobject.pkg.Recipe;

public interface GsRecipeService {
	
	Long addRecipe(Recipe new_recipe);

	void updateRecipe(Recipe update_recipe);

	List<Recipe> listAllRecipe();

	void deleteRecipe(Recipe recipe);

	void deleteRecipeById(Long identifer);

	Recipe findRecipeById(Long identifer);

}
