package br.com.fiap.healthmater.exception;

import java.util.List;

/**
 * Custom exception class for user-payload validation failure.
 *
 * @author Gabriel Oliveira
 */
public class UserValidationFailureException extends ValidationFailure {

    public UserValidationFailureException(List<String> validationMessages) {
        super(validationMessages);
    }

}
