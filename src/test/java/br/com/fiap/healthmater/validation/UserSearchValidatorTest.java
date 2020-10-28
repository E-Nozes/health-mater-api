package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.ResourceNotFoundException;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@Component
@SpringBootTest
class UserSearchValidatorTest {

    @Autowired
    private UserSearchValidator validator;

    private static final Logger log = LoggerFactory.getLogger(UserSearchValidatorTest.class);

    @Test
    void validateVerifyIfExists_Success() {
        User user = this.validator.verifyIfExists(30);

        Assertions.assertNotNull(user);
    }

    @Test
    void validateVerifyIfExistsByEmail_Success() {
        User user = this.validator.verifyIfExistsByEmail("professor@fiap.com");

        Assertions.assertNotNull(user);
    }

    @Test
    void validateDuplicatedEmail() {
        String error = this.validator.validateDuplicatedEmail("professor@fiap.com");

        log.info(error);

        Assertions.assertNotNull(error);
    }

    @Test
    void validateVerifyIfExists_Fail() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> this.validator.verifyIfExists(999));
    }

    @Test
    void validateVerifyIfExistsByEmail_Fail() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> this.validator.verifyIfExistsByEmail("invalid@email.com"));
    }

}
