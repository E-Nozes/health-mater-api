package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.exception.PostValidationFailureException;
import br.com.fiap.healthmater.repository.PostRepository;
import br.com.fiap.healthmater.util.UserUtils;
import br.com.fiap.healthmater.validation.register.PostRegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for {@link Post} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostRegisterValidator postRegisterValidator;

    @Autowired
    private UserUtils userUtils;

    public Page<Post> findAll(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

    public void create(Post post) {
        List<String> validationMessages = this.postRegisterValidator.validate(post);

        PostValidationFailureException validationFailure = new PostValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        post.setDateTime(LocalDateTime.now());
        post.setAuthor(this.userUtils.findLoggedUser());

        this.postRepository.save(post);
    }

}
