package basemodel.exception.datalayer.pckg;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public EntityNotFoundException( String msgError ) {
		super( msgError ); 
	}
	
	public EntityNotFoundException( String msgError, Throwable error) {
		super( msgError, error );
	}
	
	public EntityNotFoundException( Throwable error ) {
		super( error );
	}
	
	
}
