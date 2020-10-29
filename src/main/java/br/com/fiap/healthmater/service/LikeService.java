package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Like;
import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.exception.LikeValidationFailureException;
import br.com.fiap.healthmater.repository.LikeRepository;
import br.com.fiap.healthmater.util.UserUtil;
import br.com.fiap.healthmater.validation.LikeValidator;
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
    private LikeValidator likeValidator;

    @Autowired
    private UserUtil userUtil;

    public void create(Like like) {
        validateLikePayload(like);

        like.setUser(userUtil.findLoggedUser());

        this.likeRepository.save(like);
    }

    public void delete(Integer postId) {
        Post post = this.postSearchValidator.verifyIfExists(postId);

        post.getLikes().forEach(like -> {
            if (like.getUser().equals(this.userUtil.findLoggedUser())) {
                this.likeRepository.delete(like);
            }
        });
    }

    private void validateLikePayload(Like like) {
        this.postSearchValidator.verifyIfExists(like.getPost().getId());

        List<String> validationMessages = this.likeValidator.validate(like);

        LikeValidationFailureException validationFailure = new LikeValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }
    }

}
