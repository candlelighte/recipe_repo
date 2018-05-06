package basemodel.exception.datalayer.pckg;

public class DataLayerExceptionHundler extends RuntimeException  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataLayerExceptionHundler( String msgError ) {
		super( msgError ); 
	}
	
	public DataLayerExceptionHundler( String msgError, Throwable error) {
		super( msgError, error );
	}
	
	public DataLayerExceptionHundler( Throwable error ) {
		super( error );
	}

}
