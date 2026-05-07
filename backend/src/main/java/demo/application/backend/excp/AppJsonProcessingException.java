package demo.application.backend.excp;

public class AppJsonProcessingException extends RuntimeException {

	public AppJsonProcessingException(String message) {
		super(message);
	}

	public AppJsonProcessingException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
