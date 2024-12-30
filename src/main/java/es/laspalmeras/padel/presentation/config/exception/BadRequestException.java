package es.laspalmeras.padel.presentation.config.exception;

import java.util.List;

import es.laspalmeras.padel.config.validator.ErrorResponse;


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