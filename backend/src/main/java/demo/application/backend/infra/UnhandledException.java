package demo.application.backend.infra;

public class UnhandledException extends RuntimeException {

	public UnhandledException(String message) {
		super(message);
	}

	public UnhandledException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
