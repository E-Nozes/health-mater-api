package br.com.fiap.healthmater.exception;

import br.com.fiap.healthmater.entity.Like;

import java.util.List;

/**
 * Custom exception class for {@link Like} payload validation failure.
 *
 * @author Gabriel Oliveira
 */
public class LikeValidationFailureException extends ValidationFailure {

    public LikeValidationFailureException(List<String> validationMessages) {
        super(validationMessages);
    }

}
