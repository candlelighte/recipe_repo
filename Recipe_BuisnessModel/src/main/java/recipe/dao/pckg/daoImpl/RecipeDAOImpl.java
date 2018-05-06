package recipe.dao.pckg.daoImpl;

import org.springframework.stereotype.Repository;

import basemodel.agreement.impl.GenericModelAgreementImpl_HiberDAOSup;
import recipe.buisnessobject.pkg.Recipe;
import recipe.dao.pckg.RecipeDAO;

@Repository
public class RecipeDAOImpl extends GenericModelAgreementImpl_HiberDAOSup<Recipe, Long> implements RecipeDAO {


	public RecipeDAOImpl() {
		super(Recipe.class);
	}

}
