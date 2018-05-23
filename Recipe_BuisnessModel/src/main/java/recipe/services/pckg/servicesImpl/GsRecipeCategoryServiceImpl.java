package recipe.services.pckg.servicesImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recipe.buisnessobject.pkg.RecipeCategory;
import recipe.dao.pckg.RecipeCategoryDAO;
import recipe.services.pckg.GsRecipeCategoryService;

@Transactional
@Service
public class GsRecipeCategoryServiceImpl implements GsRecipeCategoryService{
	
	@Autowired
	private RecipeCategoryDAO recipeCategoryDAO;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private final String SERVICE_NAME = "RECIPE-CATEGORY-SERVCE";

	@PostConstruct
	public void init() {
		LOGGER.debug(SERVICE_NAME + " ---> Created.");
	}

	@PreDestroy
	public void destroy() {
		LOGGER.debug(SERVICE_NAME + " ---> Destroyed.");
	}


	@Override
	public Long addRecipeCategory(RecipeCategory new_recipecategory) {
		return recipeCategoryDAO.add(new_recipecategory) ;
	}

	@Override
	public void updateRecipeCategory(RecipeCategory update_recipecategory) {
		recipeCategoryDAO.update(update_recipecategory);
	}

	@Override
	public List<RecipeCategory> listAllRecipeCategory() {
		return recipeCategoryDAO.getAll();
	}

	@Override
	public void deleteRecipeCategory(RecipeCategory recipeCategory) {
		recipeCategoryDAO.remove(recipeCategory);
		
	}

	@Override
	public void deleteRecipeCategoryById(Long identifer) {
		recipeCategoryDAO.removeById(identifer);
	}

	@Override
	public RecipeCategory findRecipeCategoryById(Long identifer) {
		return recipeCategoryDAO.findById(identifer);
	}


}
