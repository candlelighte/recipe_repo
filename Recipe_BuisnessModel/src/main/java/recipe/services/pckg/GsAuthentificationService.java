package recipe.services.pckg;

import recipe.buisnessobject.pkg.User;

public interface GsAuthentificationService {
	
	User authUser(String login, String password);
	

}
