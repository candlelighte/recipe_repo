package recipe.dao.pckg.daoImpl;

import org.springframework.stereotype.Repository;

import basemodel.agreement.impl.GenericModelAgreementImpl_HiberDAOSup;
import recipe.buisnessobject.pkg.RecipeCategory;
import recipe.dao.pckg.RecipeCategoryDAO;

@Repository
public class RecipeCategoryDAOImpl extends GenericModelAgreementImpl_HiberDAOSup<RecipeCategory, Long>
		implements RecipeCategoryDAO {

	public RecipeCategoryDAOImpl() {
		super(RecipeCategory.class);
	}

}
