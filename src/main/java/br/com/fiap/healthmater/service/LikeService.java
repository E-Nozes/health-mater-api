package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Like;
import br.com.fiap.healthmater.exception.LikeValidationFailureException;
import br.com.fiap.healthmater.repository.LikeRepository;
import br.com.fiap.healthmater.util.UserUtils;
import br.com.fiap.healthmater.validation.register.LikeRegisterValidator;
import br.com.fiap.healthmater.validation.search.PostSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for {@link Like} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostSearchValidator postSearchValidator;

    @Autowired
    private LikeRegisterValidator likeRegisterValidator;

    @Autowired
    private UserUtils userUtils;

    public void create(Like like) {
        this.postSearchValidator.verifyIfExists(like.getPost().getId());

        List<String> validationMessages = this.likeRegisterValidator.validate(like);

        LikeValidationFailureException validationFailure = new LikeValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        like.setUser(userUtils.findLoggedUser());

        this.likeRepository.save(like);
    }

}
