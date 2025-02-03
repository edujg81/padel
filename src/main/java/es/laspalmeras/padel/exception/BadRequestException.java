package es.laspalmeras.padel.exception;

import java.util.List;


@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

	private final List<ErrorResponse> errors;
    public BadRequestException(List<ErrorResponse> errors){
        this.errors = errors;
    }
    public List<ErrorResponse> getErrors(){
        return errors;
    }
}