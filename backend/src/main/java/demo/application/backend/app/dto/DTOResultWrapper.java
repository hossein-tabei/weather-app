package demo.application.backend.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOResultWrapper<T> {

	private String message;
	private T info;
}
