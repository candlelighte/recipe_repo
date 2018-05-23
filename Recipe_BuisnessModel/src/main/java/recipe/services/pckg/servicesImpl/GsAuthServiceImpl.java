package recipe.services.pckg.servicesImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recipe.buisnessobject.pkg.User;
import recipe.dao.pckg.UserDAO;
import recipe.services.pckg.GsAuthentificationService;

@Transactional
@Service
public class GsAuthServiceImpl implements GsAuthentificationService {

	private UserDAO userDAO;

	@Override
	public User authUser(String login, String password) {
		return userDAO.findUserByNameAndPassword(login, password);
	}

}
