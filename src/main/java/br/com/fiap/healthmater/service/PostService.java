package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for {@link Post} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<Post> findAll(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

}
