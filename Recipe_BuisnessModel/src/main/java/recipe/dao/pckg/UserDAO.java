package recipe.dao.pckg;

import basemodel.agreement.GenericModelAgreement;
import recipe.buisnessobject.pkg.User;

public interface UserDAO extends GenericModelAgreement<User, Long> {

	User findUserByNameAndPassword(String login, String password);

}
