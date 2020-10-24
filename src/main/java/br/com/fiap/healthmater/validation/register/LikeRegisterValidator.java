package br.com.fiap.healthmater.validation.register;

import br.com.fiap.healthmater.entity.Like;
import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.repository.PostRepository;
import br.com.fiap.healthmater.util.UserUtils;
import br.com.fiap.healthmater.validation.LikeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for {@link Like} register payload.
 *
 * @author Gabriel Oliveira
 */
@Component
public class LikeRegisterValidator implements LikeValidator {

    private static final String DUPLICATED_LIKE_MESSAGE_TEMPLATE = "The current user already liked this post";

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserUtils userUtils;

    @Override
    public List<String> validate(Like like) {
        Stream<String> validLike = validateDuplicatedLike(like).stream();

        return Stream.of(validLike)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateDuplicatedLike(Like like) {
        Post post = this.postRepository.findById(like.getPost().getId()).get();

        AtomicBoolean duplicated = new AtomicBoolean(false);

        post.getLikes().forEach(lk -> {
            if (lk.getUser().equals(this.userUtils.findLoggedUser())) {
                duplicated.set(true);
            }
        });

        return duplicated.get()
                ? Collections.singletonList(generateErrorMessage(DUPLICATED_LIKE_MESSAGE_TEMPLATE))
                : Collections.emptyList();
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
