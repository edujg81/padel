package es.laspalmeras.padel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String string, String string2, Long partidoId) {
		super(string + ' ' + string2 + ' ' +  partidoId);
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no encontrado con %s : '%s'", resourceName, fieldName, fieldValue));
    }
}