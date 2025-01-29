package es.laspalmeras.padel.presentation.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class DNIDuplicadoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DNIDuplicadoException(String message) {
		super(message);
	}

	public DNIDuplicadoException(String string, String dni) {
		super(string + ' ' + dni);
	}
}
