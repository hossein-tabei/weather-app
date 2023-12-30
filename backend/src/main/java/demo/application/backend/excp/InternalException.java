package demo.application.backend.excp;

public class InternalException extends Exception {

	private static final long serialVersionUID = 2344989390044128553L;
	
	public InternalException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
	
}
