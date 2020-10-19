package br.com.fiap.healthmater.validation.validator;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.model.PasswordUpdateModel;

import java.util.List;

/**
 * Contract class to be implemented by the PasswordUpdate validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface PasswordValidator {

    List<String> validate(PasswordUpdateModel passwordUpdateModel);

}
