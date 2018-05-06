package recipe.services.pckg;

import java.util.List;

import recipe.buisnessobject.pkg.User;

public interface GsUserService {

	Long addUser(User new_user);

	void updateUser(User update_user);

	List<User> listAllUser();

	void deleteUser(User user);

	void deleteUserById(Long identifer);

	User findUserById(Long identifer);
	
}
