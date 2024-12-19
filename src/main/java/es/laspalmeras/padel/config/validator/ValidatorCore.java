package es.laspalmeras.padel.config.validator;

import java.util.List;


import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import es.laspalmeras.padel.presentation.config.exception.BadRequestException;

public interface ValidatorCore<T> extends Validator<T> {
	 default void validateOrThrow(T instance){
	        ValidationResult result =  validate(instance);
	        if(!result.isValid()){
	            List<ErrorResponse> errors =  result.getErrors()
	                .stream()
	                .map(e->new ErrorResponse(e.getField(), e.getMessage()))
	                .toList();
	            throw new BadRequestException(errors);
	        }
	    }    
}
