package recipe.services.pckg.servicesImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recipe.buisnessobject.pkg.User;
import recipe.dao.pckg.UserDAO;
import recipe.dao.pckg.daoImpl.UserDAOImpl;
import recipe.services.pckg.GsUserService;

@Transactional
@Service
public class GsUserServiceImpl implements GsUserService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private final String SERVICE_NAME = "USER-SERVCE";
	
	@Autowired
	private UserDAO userDao;

	@PostConstruct
	public void init() {
		LOGGER.debug( SERVICE_NAME + " ---> Created." );
	}
	
	@PreDestroy
	public void destroy() {
		LOGGER.debug( SERVICE_NAME + " ---> Destroyed." );
	}
	
	@Override
	public Long addUser(User new_user) {
		userDao = new UserDAOImpl();
		return userDao.add(new_user);
	}

	@Override
	public void updateUser(User update_user) {
		userDao.update(update_user);

	}

	@Override
	public List<User> listAllUser() {
		return userDao.getAll();
	}

	@Override
	public void deleteUser(User user) {
		userDao.remove(user);

	}

	@Override
	public void deleteUserById(Long identifer) {
		userDao.removeById(identifer);
	}

	@Override
	public User findUserById(Long identifer) {
		return userDao.findById(identifer);
	}

}
