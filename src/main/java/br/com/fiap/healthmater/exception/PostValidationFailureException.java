package br.com.fiap.healthmater.exception;

import br.com.fiap.healthmater.entity.Post;

import java.util.List;

/**
 * Custom exception class for {@link Post} payload validation failure.
 *
 * @author Gabriel Oliveira
 */
public class PostValidationFailureException extends ValidationFailure {

    public PostValidationFailureException(List<String> validationMessages) {
        super(validationMessages);
    }

}
