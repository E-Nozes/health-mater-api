package br.com.fiap.healthmater.resource;

import br.com.fiap.healthmater.entity.Like;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Define all the {@link Like} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface LikeResource {

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Like like);

}
