package basemodel.utils.pckg;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basemodel.exception.datalayer.pckg.DataLayerExceptionHundler;

public class SessionFactoryHibernate4Builder {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactoryHibernate4Builder.class);

	private static SessionFactory sessionFactory;

	public SessionFactoryHibernate4Builder() {
		// TODO Auto-generated constructor stub
		LOGGER.debug(getClass() + " init .... ");
	}

	public static SessionFactory buildSessionFactory() throws DataLayerExceptionHundler {

		// create session factory from
		Configuration configuration = new Configuration();
		configuration.configure();

		LOGGER.debug("Hibernate configuration finished  ");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		LOGGER.debug("Hibernate service registry created.");

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		LOGGER.debug("Hibernate SessionFactory created.");

		return sessionFactory;
	}

}
