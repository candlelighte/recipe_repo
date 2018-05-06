package basemodel.agreement;

import java.io.Serializable;
import java.util.List;

import basemodel.exception.datalayer.pckg.EntityNotFoundException;

public interface GenericModelAgreement<Subject, Identifer extends Serializable > {

	Identifer add(Subject subject);

	void update(Subject subject);

	List<Subject> getAll();

	void remove(Subject subject) throws EntityNotFoundException;

	void removeById(Identifer identifer) throws EntityNotFoundException;
	
	Subject findById( Identifer identifer ) throws EntityNotFoundException;

}
