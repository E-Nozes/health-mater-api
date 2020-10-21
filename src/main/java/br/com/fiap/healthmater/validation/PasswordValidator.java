package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.dto.PasswordUpdateDTO;

import java.util.List;

/**
 * Contract class to be implemented by the PasswordUpdate validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface PasswordValidator {

    List<String> validate(PasswordUpdateDTO passwordUpdateDTO);

}
