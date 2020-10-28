package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.validation.register.UserRegisterValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.com.fiap.healthmater.builder.EntityBuilder.buildUser;

@SpringBootTest
class UserRegisterValidatorTest {

    @Autowired
    private UserRegisterValidator validator;

    @Test
    void validateHappyPath() {
        User user = buildUser();

        List<String> errors = this.validator.validate(user);

        Assertions.assertEquals(0, errors.size());
    }

}
