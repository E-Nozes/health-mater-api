package br.com.fiap.healthmater.resource;

import br.com.fiap.healthmater.dto.PostDTO;
import br.com.fiap.healthmater.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Define all the {@link Post} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface PostResource {

    @GetMapping
    public ResponseEntity<Page<PostDTO>> findAll(Pageable pageable);

    @PostMapping
    public ResponseEntity<HttpStatus> post(@RequestBody @Valid Post post);

}
