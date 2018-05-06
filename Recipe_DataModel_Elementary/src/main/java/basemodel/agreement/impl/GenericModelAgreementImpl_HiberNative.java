package basemodel.agreement.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import basemodel.agreement.GenericModelAgreement;
import basemodel.exception.datalayer.pckg.DataLayerExceptionHundler;
import basemodel.exception.datalayer.pckg.EntityNotFoundException;
import basemodel.utils.pckg.SessionFactoryHibernate4Builder;

public class GenericModelAgreementImpl_HiberNative<Subject, Identifer extends Serializable>
		implements GenericModelAgreement<Subject, Identifer> {

	private static Logger LOGGER;

	private Class<Subject> objectToPersist;

	private SessionFactory sessionFactory = SessionFactoryHibernate4Builder.buildSessionFactory();

	public GenericModelAgreementImpl_HiberNative(final Class<Subject> subject) {

		objectToPersist = subject;

		LOGGER = LoggerFactory.getLogger(objectToPersist);

		LOGGER.debug("Class " + objectToPersist.getClass() + "initiliased.");

	}

	@SuppressWarnings("unchecked")
	public Identifer add(Subject subject) {

		LOGGER.debug("Method :: Identifer add(Subject subject) --> called. ");

		// get session
		Session session = this.getSession();

		Identifer identifer = null;

		// test if there is already a transaction in this this case :
		if (isActiveTransactionExist(session)) {
			// GenericModelAgreementImpl use existing session and transaction
			identifer = (Identifer) session.save(subject);

			LOGGER.debug(
					"Subject :: " + subject.toString() + " through Method :: void add(Subject subject) --> Added. ");

		} else {

			// GenericModelAgreementImpl create a new session and transaction

			Transaction transaction = null;

			try {

				transaction = session.beginTransaction();

				identifer = (Identifer) session.save(subject);

				transaction.commit();

				LOGGER.debug("Subject :: " + subject.toString()
						+ " through Method :: void add(Subject subject) --> Added. ");

			} catch (RuntimeException exception) {
				SessionErrorHundling(transaction, exception);
			} finally {
				closeSession(session);
			}

		}

		return identifer;
	}

	public void update(Subject subject) {

		LOGGER.debug("Method :: void update(Subject subject) --> called. ");

		// get session
		Session session = this.getSession();

		// test if there is already a transaction in this this case :
		if (isActiveTransactionExist(session)) {
			// GenericModelAgreementImpl use existing session and transaction
			session.update(subject);

			LOGGER.debug("Subject :: " + subject.toString()
					+ " through Method :: void update(Subject subject) --> Updated. ");

		} else {

			// GenericModelAgreementImpl create a new session and transaction

			Transaction transaction = null;

			try {

				transaction = session.beginTransaction();

				session.update(subject);

				transaction.commit();

				LOGGER.debug("Subject :: " + subject.toString()
						+ " through Method :: void update(Subject subject) --> Updated. ");

			} catch (RuntimeException exception) {
				SessionErrorHundling(transaction, exception);
			} finally {
				closeSession(session);
			}

		}

	}

	@SuppressWarnings("unchecked")
	public List<Subject> getAll() {

		LOGGER.debug("Method :: void update(Subject subject) --> Called. ");

		List<Subject> listsubject = new ArrayList<Subject>();

		// get session
		Session session = this.getSession();

		// test if there is already a transaction in this this case :
		if (isActiveTransactionExist(session)) {

			// GenericModelAgreementImpl use existing session and transaction
			listsubject = session.createCriteria(objectToPersist).list();

			LOGGER.debug("ListSubject :: " + listsubject.toString()
					+ " through Method :: List<Subject> getAll() --> Listed. ");

		} else {

			// GenericModelAgreementImpl create a new session and transaction

			Transaction transaction = null;

			try {

				transaction = session.beginTransaction();

				listsubject = session.createCriteria(objectToPersist).list();

				transaction.commit();

				LOGGER.debug("ListSubject :: " + listsubject.toString()
						+ "through Method :: List<Subject> getAll() --> Listed. ");

			} catch (RuntimeException exception) {
				SessionErrorHundling(transaction, exception);
			} finally {
				closeSession(session);
			}

		}

		return listsubject;
	}

	public void remove(Subject subject) throws EntityNotFoundException {
		LOGGER.debug("Method :: void remove(Subject subject) --> called. ");
		// hibernateTemplate.delete(subject);
	}

	public void removeById(Identifer identifer) throws EntityNotFoundException {
		LOGGER.debug("Method :: void removeById(Identifer identifer) --> called. ");

		Session session = this.getSession();

		Subject subject = findById(identifer);

		if (isActiveTransactionExist(session)) {
			session.delete(subject);
			LOGGER.debug("Subject with Identifer:: " + identifer + " --> " + subject.toString()
					+ " Droped through Method :: removeById(Identifer identifer) --> Deleted. ");
		} else {
			Transaction transaction = null;

			try {

				transaction = session.beginTransaction();

				session.delete(subject);

				transaction.commit();

				LOGGER.debug("Subject with Identifer:: " + identifer + " --> " + subject.toString()
						+ " Droped through Method :: removeById(Identifer identifer) --> Deleted. ");

			} catch (RuntimeException exception) {
				SessionErrorHundling(transaction, exception);
			} finally {
				closeSession(session);
			}

		}

	}

	@SuppressWarnings("unchecked")
	public Subject findById(Identifer identifer) throws EntityNotFoundException {
		LOGGER.debug("Method :: Subject findById(Identifer identifer) --> called. ");

		Subject subjectSearched = null;

		Session session = this.getSession();

		if (isActiveTransactionExist(session)) {

			subjectSearched = (Subject) session.get(this.objectToPersist, identifer);
			if (subjectSearched != null)
				LOGGER.debug("Subject Searched with Identifer:: " + identifer + " --> " + subjectSearched.toString()
						+ " Found through Method :: Subject findById(Identifer identifer) --> Listed. ");

		} else {
			Transaction transaction = null;

			try {

				transaction = session.beginTransaction();

				subjectSearched = (Subject) session.get(this.objectToPersist, identifer);

				transaction.commit();

				if (subjectSearched != null)
					LOGGER.debug("Subject Searched with Identifer:: " + identifer + " --> " + subjectSearched.toString()
							+ " Found through Method :: Subject findById(Identifer identifer) --> Listed ");

			} catch (RuntimeException exception) {
				SessionErrorHundling(transaction, exception);
			} finally {
				closeSession(session);
			}
		}

		if (identifer == null) {
			LOGGER.debug("Subject Searched with Identifer:: " + identifer
					+ " --> NOT Found through Method :: Subject findById(Identifer identifer) --> Listed ");
			throw new EntityNotFoundException("Searched identifer (" + identifer + " )of the instance is not found");
		}

		return subjectSearched;
	}

	/** Utilities Function */

	protected void closeSession(Session session) {
		LOGGER.debug("Method :: void closeSession(Session session) --> called. ");

		if (session != null && session.isOpen()) {
			session.close();
			LOGGER.debug("Hibernate Session :: State  --> Closed. ");
		}

	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected boolean isActiveTransactionExist(Session session) {

		if (session != null && session.getTransaction() != null)
			return session.getTransaction().isActive();

		return false;
	}

	protected void SessionErrorHundling(Transaction transaction, RuntimeException runtimeException) {
		if (transaction != null)
			transaction.rollback();

		LOGGER.error("Error message --> " + runtimeException);

		throw new DataLayerExceptionHundler(runtimeException);
	}

}
