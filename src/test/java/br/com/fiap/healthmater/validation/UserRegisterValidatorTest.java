package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.fiap.healthmater.builder.EntityBuilder.buildUser;

@Component
@SpringBootTest
class UserRegisterValidatorTest {

    @Autowired
    private UserValidator validator;

    private final User user = buildUser();

    private static final Logger log = LoggerFactory.getLogger(UserRegisterValidatorTest.class);

    @Test
    void validateHappyPath() {
        List<String> errors = this.validator.validate(user);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void validateInvalidEmail() {
        user.setEmail("professor@fiap.com");

        List<String> errors = this.validator.validate(user);

        log.info(errors.get(0));

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void validateInvalidPassword() {
        user.setPassword("1234567");

        List<String> errors = this.validator.validate(user);

        log.info(errors.get(0));

        Assertions.assertEquals(1, errors.size());
    }

    @Test
    void validateInvalidProfiles() {
        user.getProfiles().iterator().next().setId(999);

        List<String> errors = this.validator.validate(user);

        log.info(errors.get(0));

        Assertions.assertEquals(1, errors.size());
    }

}
