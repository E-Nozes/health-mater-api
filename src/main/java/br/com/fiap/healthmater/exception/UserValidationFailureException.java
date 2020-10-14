package br.com.fiap.healthmater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Custom exception class for user-payload validation failure.
 *
 * @author Gabriel Oliveira
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Payload Validation Failure")
public class UserValidationFailureException extends ValidationFailure {

    public UserValidationFailureException(List<String> validationMessages) {
        super(validationMessages);
    }

    public UserValidationFailureException() {
    }

}
