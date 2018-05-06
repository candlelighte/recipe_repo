package recipe.services.pckg.servicesImpl;

import recipe.buisnessobject.pkg.User;
import recipe.dao.pckg.UserDAO;
import recipe.services.pckg.GsAuthentificationService;

public class GsAuthServiceImpl implements GsAuthentificationService {

	private UserDAO userDAO;

	@Override
	public User authUser(String login, String password) {
		return userDAO.findUserByNameAndPassword(login, password);
	}

}
