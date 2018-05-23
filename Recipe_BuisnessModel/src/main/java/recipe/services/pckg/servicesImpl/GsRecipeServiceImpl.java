package recipe.services.pckg.servicesImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recipe.buisnessobject.pkg.Recipe;
import recipe.dao.pckg.RecipeDAO;
import recipe.services.pckg.GsRecipeService;

@Transactional
@Service
public class GsRecipeServiceImpl implements GsRecipeService {

	@Autowired
	private RecipeDAO recipeDAO;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private final String SERVICE_NAME = "RECIPE-SERVCE";

	@PostConstruct
	public void init() {
		LOGGER.debug(SERVICE_NAME + " ---> Created.");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.debug(SERVICE_NAME + " ---> Destroyed.");
	}

	@Override
	public Long addRecipe(Recipe new_recipe) {
		return recipeDAO.add(new_recipe);
	}

	@Override
	public void updateRecipe(Recipe update_recipe) {
		recipeDAO.update(update_recipe);

	}

	@Override
	public List<Recipe> listAllRecipe() {
		return recipeDAO.getAll();
	}

	@Override
	public void deleteRecipe(Recipe recipe) {
		recipeDAO.remove(recipe);
	}

	@Override
	public void deleteRecipeById(Long identifer) {
		recipeDAO.removeById(identifer);

	}

	@Override
	public Recipe findRecipeById(Long identifer) {
		return recipeDAO.findById(identifer);
	}

}
