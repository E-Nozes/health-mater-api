package br.com.fiap.healthmater.resource;

import br.com.fiap.healthmater.entity.Donation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Define all the {@link Donation} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface DonationResource {

    @PostMapping
    public ResponseEntity<Donation> donate(@RequestBody @Valid Donation donation);

}
