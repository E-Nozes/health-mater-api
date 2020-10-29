package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.Donation;

import java.util.List;

/**
 * Contract class to be implemented by the {@link Donation} validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface DonationValidator {

    List<String> validate(Donation donation);

}
