package br.com.fiap.healthmater.resource;

import br.com.fiap.healthmater.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Define all the {@link Post} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface PostResource {

    @GetMapping
    public ResponseEntity<Page<Post>> findAll(Pageable pageable);

}
