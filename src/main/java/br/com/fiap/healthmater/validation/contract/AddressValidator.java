package br.com.fiap.healthmater.validation.contract;

import br.com.fiap.healthmater.entity.Address;

import java.util.List;

/**
 * Contract class to be implemented by the {@link Address} validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface AddressValidator {

    List<String> validate(Address address);

}
