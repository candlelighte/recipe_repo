package recipe.configuration.pckg;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "basemodel", "recipe" })
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
public class AppConf {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	protected Environment environment;

//	public AppConf() {
//		// TODO Auto-generated constructor stub
//		LOGGER.debug("Configuration loaded ... ");
//	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { environment.getProperty("package.model") });
		sessionFactory.setHibernateProperties(hibernateProperties());

		if (sessionFactory != null) {
			LOGGER.debug("Method :: LocalSessionFactoryBean sessionFactory() ---> sessionFactory Created.");
		}

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.driver_class"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.url"));
		dataSource.setUsername(environment.getRequiredProperty("mysql.username"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.password"));
		LOGGER.debug("Method :: DataSource dataSource() ---> dataSource Created.");
		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManagerH4(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		if (txManager != null)
			LOGGER.debug(
					"Method :: ransactionManagerH4( SessionFactory sessionFactory ) -- > Hibernate Transaction Manager ---> Created. ");
		return txManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//		properties.put("hibernate.hbm2ddl.import_files",
//				environment.getRequiredProperty("hibernate.hbm2ddl.import_files"));
		return properties;
	}

}
