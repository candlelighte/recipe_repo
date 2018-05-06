package recipe.dao.pckg.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import basemodel.agreement.impl.GenericModelAgreementImpl_HiberDAOSup;
import recipe.buisnessobject.pkg.User;
import recipe.dao.pckg.UserDAO;

@Repository
@Transactional
public class UserDAOImpl extends GenericModelAgreementImpl_HiberDAOSup<User, Long> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User findUserByNameAndPassword(String login, String password) {

		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();

		Query query = session.createQuery("from User Where Login= :login and Password= :password");
		query.setString("login", login);
		query.setString("password", password);

		User user = (User) query.uniqueResult();

		return user;
	}

}
