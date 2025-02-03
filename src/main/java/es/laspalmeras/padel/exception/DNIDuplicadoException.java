package es.laspalmeras.padel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class DNIDuplicadoException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DNIDuplicadoException(String dni) {
		super("El DNI " + dni + " ya está registrado.");
	}

	public DNIDuplicadoException(String message, String dni) {
		super(message + ' ' + dni);
	}
}
