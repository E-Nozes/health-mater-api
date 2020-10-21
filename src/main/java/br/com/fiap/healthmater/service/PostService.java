package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.dto.PostDTO;
import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.exception.PostValidationFailureException;
import br.com.fiap.healthmater.repository.PostRepository;
import br.com.fiap.healthmater.util.UserUtils;
import br.com.fiap.healthmater.validation.register.PostRegisterValidator;
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
    private PostRegisterValidator postRegisterValidator;

    @Autowired
    private UserUtils userUtils;

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
        List<String> validationMessages = this.postRegisterValidator.validate(post);

        PostValidationFailureException validationFailure = new PostValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        post.setDateTime(LocalDateTime.now());
        post.setAuthor(this.userUtils.findLoggedUser());

        this.postRepository.save(post);
    }

    private PostDTO convertToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

}
