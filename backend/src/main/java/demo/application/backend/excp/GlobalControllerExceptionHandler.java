package demo.application.backend.excp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import demo.application.backend.model.DTOResultWrapper;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({InternalException.class})
	protected ResponseEntity<Object> handleInternalException(InternalException ex, WebRequest request) {
		DTOResultWrapper<Object> result = new DTOResultWrapper<>(ex.getMessage(), null);
		return handleExceptionInternal(ex, result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
}
