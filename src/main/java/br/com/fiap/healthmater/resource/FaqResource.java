package br.com.fiap.healthmater.resource;

import br.com.fiap.healthmater.entity.Faq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Define all the {@link Faq} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface FaqResource {

    @GetMapping
    public ResponseEntity<List<Faq>> findAll();

}
