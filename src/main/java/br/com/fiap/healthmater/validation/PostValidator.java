package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.Post;

import java.util.List;

/**
 * Contract class to be implemented by the {@link Post} validation service classes.
 *
 * @author Gabriel Oliveira
 */
public interface PostValidator {

    List<String> validate(Post post);

}
