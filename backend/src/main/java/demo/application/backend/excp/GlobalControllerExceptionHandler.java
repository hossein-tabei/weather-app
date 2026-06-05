package demo.application.backend.excp;

import com.google.gson.JsonObject;
import demo.application.backend.infra.AppJsonProcessingException;
import demo.application.backend.infra.UnhandledException;
import demo.application.backend.weather.repository.NotFoundException;
import jakarta.validation.ConstraintViolationException;
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
		JsonObject joError = new JsonObject();
		joError.addProperty("status", ex.getStatusCode().value());
		joError.addProperty("message", ex.getMessage());
		return handleExceptionInternal(ex, joError.toString(), new HttpHeaders(), ex.getStatusCode(), request);
	}

	@ExceptionHandler({AppJsonProcessingException.class})
	protected ResponseEntity<Object> handleJsonProcessingException(AppJsonProcessingException ex, WebRequest request) {
		return handleServerException(ex, request);
	}

	@ExceptionHandler({UnhandledException.class})
	protected ResponseEntity<Object> handleJsonProcessingException(UnhandledException ex, WebRequest request) {
		return handleServerException(ex, request);
	}

	@ExceptionHandler({NotFoundException.class})
	protected ResponseEntity<Object> handleClientException(NotFoundException ex, WebRequest request) {
		return handleClientException(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	protected ResponseEntity<Object> handleInputValidationException(ConstraintViolationException ex, WebRequest request) {
		return handleClientException(ex, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity<Object> handleServerException(RuntimeException ex, WebRequest request) {
		JsonObject joError = new JsonObject();
		joError.addProperty("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		joError.addProperty("message", ex.getMessage());
		return handleExceptionInternal(ex, joError.toString(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private ResponseEntity<Object> handleClientException(RuntimeException ex, HttpStatus httpStatus, WebRequest request) {
		JsonObject joError = new JsonObject();
		joError.addProperty("status", httpStatus.value());
		joError.addProperty("message", ex.getMessage());
		return handleExceptionInternal(ex, joError.toString(), new HttpHeaders(), httpStatus, request);
	}


	
}
