package br.com.fiap.healthmater.exception;

import br.com.fiap.healthmater.entity.Donation;

import java.util.List;

/**
 * Custom exception class for {@link Donation} payload validation failure.
 *
 * @author Gabriel Oliveira
 */
public class DonationValidationFailureException extends ValidationFailure {

    public DonationValidationFailureException(List<String> validationMessages) {
        super(validationMessages);
    }

}
