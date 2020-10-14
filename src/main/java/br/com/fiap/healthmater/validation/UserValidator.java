package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;

import java.util.List;

/**
 * Contract class to be implemented by the {@link User} validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface UserValidator {

    List<String> validate(User user);

}
