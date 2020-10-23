package br.com.fiap.healthmater.validation.search;

import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.exception.ResourceNotFoundException;
import br.com.fiap.healthmater.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link Post} searching methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class PostSearchValidator {

    private static final String INVALID_ID_MESSAGE_TEMPLATE = "Post not found for the given ID '%s'";

    @Autowired
    private PostRepository postRepository;

    public void verifyIfExists(Integer id) {
        this.postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(generateErrorMessage(INVALID_ID_MESSAGE_TEMPLATE, id)));
    }

    private String generateErrorMessage(String template, Integer id) {
        return String.format(template, id);
    }

}
