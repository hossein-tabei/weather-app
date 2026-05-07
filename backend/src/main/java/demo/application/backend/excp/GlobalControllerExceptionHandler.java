package demo.application.backend.excp;

import demo.application.backend.model.DTOResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({WebClientResponseException.class})
	protected ResponseEntity<Object> handleRemoteApiCallException(WebClientResponseException ex, WebRequest request) {
		DTOResultWrapper<Object> result = new DTOResultWrapper<>(ex.getMessage(), null);
		return handleExceptionInternal(ex, result, new HttpHeaders(), ex.getStatusCode(), request);
	}

	@ExceptionHandler({AppJsonProcessingException.class})
	protected ResponseEntity<Object> handleJsonProcessingException(AppJsonProcessingException ex, WebRequest request) {
		return handleInternalException(ex, request);
	}

	@ExceptionHandler({UnhandledException.class})
	protected ResponseEntity<Object> handleJsonProcessingException(UnhandledException ex, WebRequest request) {
		return handleInternalException(ex, request);
	}

	private ResponseEntity<Object> handleInternalException(RuntimeException ex, WebRequest request) {
		DTOResultWrapper<Object> result = new DTOResultWrapper<>(ex.getMessage(), null);
		return handleExceptionInternal(ex, result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
}
