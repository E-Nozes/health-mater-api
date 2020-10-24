package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.Like;

import java.util.List;

/**
 * Contract class to be implemented by the {@link Like} validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface LikeValidator {

    List<String> validate(Like like);

}
