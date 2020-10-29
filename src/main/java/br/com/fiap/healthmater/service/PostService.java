package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.dto.PostDTO;
import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.exception.PostValidationFailureException;
import br.com.fiap.healthmater.repository.PostRepository;
import br.com.fiap.healthmater.util.UserUtil;
import br.com.fiap.healthmater.validation.PostValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
    private PostValidator postValidator;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PostDTO> findAll(Pageable pageable) {
        Page<Post> posts = this.postRepository.findAll(pageable);

        List<PostDTO> postsDTO = posts.stream()
                .map(this::convertToDto)
                .collect(toList());

        return new PageImpl<>(postsDTO);
    }

    public void create(Post post) {
        validatePostPayload(post);

        post.setDateTime(LocalDateTime.now());
        post.setAuthor(this.userUtil.findLoggedUser());

        this.postRepository.save(post);
    }

    private PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    private void validatePostPayload(Post post) {
        List<String> validationMessages = this.postValidator.validate(post);

        PostValidationFailureException validationFailure = new PostValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }
    }

}
