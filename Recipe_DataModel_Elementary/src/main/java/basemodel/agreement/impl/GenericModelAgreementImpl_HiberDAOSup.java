package basemodel.agreement.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import basemodel.agreement.GenericModelAgreement;
import basemodel.exception.datalayer.pckg.EntityNotFoundException;



public class GenericModelAgreementImpl_HiberDAOSup<Subject, Identifer extends Serializable> extends HibernateDaoSupport
		implements GenericModelAgreement<Subject, Identifer> {

	private static Logger LOGGER;

	private Class<Subject> objectToPersist;

	public GenericModelAgreementImpl_HiberDAOSup(Class<Subject> subject) {

		this.objectToPersist = subject;

		LOGGER = LoggerFactory.getLogger(objectToPersist);

		LOGGER.debug("GenericModelAgreementIml_HiberDaoSuper Init for Class :: " + subject);

	}
	
	@Autowired
	public void autowireSessionFactory(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public Identifer add(Subject subject) {
		LOGGER.debug("Method :: Identifer add(Subject subject) --> Called. ");
		@SuppressWarnings("unchecked")
		Identifer id = (Identifer) getHibernateTemplate().save(subject);
		LOGGER.debug("Object :: " + subject.toString() + " with identifer :: " + id + " --> Added.");
		return id;
	}

	public void update(Subject subject) {
		LOGGER.debug("Method :: void update(Subject subject) --> Called. ");
		getHibernateTemplate().update(subject);
		LOGGER.debug("Object :: " + subject.toString() + " --> Updated.");

	}

	public List<Subject> getAll() {
		LOGGER.debug("Method :: List<Subject> getAll() --> Called. ");
		List<Subject> listSubjects = getHibernateTemplate().loadAll(objectToPersist);
		LOGGER.debug("List of :: " + objectToPersist.getClass() + " --> Loaded. ");
		return listSubjects;
	}

	public void remove(Subject subject) throws EntityNotFoundException {
		LOGGER.debug("Method :: void remove(Subject subject) --> Called. ");
		getHibernateTemplate().delete(subject);
		LOGGER.debug("Subject :: " + subject.toString() + " --> Deleted. ");

	}

	public void removeById(Identifer identifer) throws EntityNotFoundException {
		LOGGER.debug("Method :: vvoid removeById(Identifer identifer)  --> Called. ");
		Subject subject = null;
		subject = findById(identifer);
		getHibernateTemplate().delete(subject);
		LOGGER.debug("Subject :: " + subject.toString() + " --> Deleted. ");

	}

	public Subject findById(Identifer identifer) throws EntityNotFoundException {
		LOGGER.debug("Method :: Subject findById(Identifer identifer) --> Called. ");
		Subject subject = null;

		subject = getHibernateTemplate().get(objectToPersist, identifer);

		if (subject == null) {
			LOGGER.debug("Subject with identifer :: " + identifer + " Not Found. ");
			throw new EntityNotFoundException("Entity with id :: " + identifer + " not found.");
		}

		return subject;
	}

}
