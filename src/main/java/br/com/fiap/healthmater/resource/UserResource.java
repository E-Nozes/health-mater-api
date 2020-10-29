package br.com.fiap.healthmater.resource;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.dto.PasswordUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Define all the {@link User} resources that will be exposed by the Controller classes.
 *
 * @author Gabriel Oliveira
 */
public interface UserResource {

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id);

    @PostMapping
    public ResponseEntity<User> register(@RequestBody @Valid User user);

    @PutMapping("password")
    public ResponseEntity<HttpStatus> updatePassword(@RequestBody @Valid PasswordUpdateDTO passwordUpdateDTO);

}
