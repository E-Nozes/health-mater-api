package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserSearchValidatorTest {

    @Autowired
    private UserSearchValidator validator;

    @Test
    void verifyIfExists() {

        User user = this.validator.verifyIfExists(30);
        Assertions.assertNotEquals(null, user);
    }
}
