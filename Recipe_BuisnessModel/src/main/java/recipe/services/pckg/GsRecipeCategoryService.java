package recipe.services.pckg;

import java.util.List;

import recipe.buisnessobject.pkg.RecipeCategory;;

public interface GsRecipeCategoryService {
	
	Long addRecipeCategory(RecipeCategory new_recipecategory);

	void updateRecipeCategory(RecipeCategory update_recipe);

	List<RecipeCategory> listAllRecipeCategory();

	void deleteRecipeCategory(RecipeCategory recipe);

	void deleteRecipeCategoryById(Long identifer);

	RecipeCategory findRecipeCategoryById(Long identifer);

}
