package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.builder.EntityBuilder;
import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.validation.register.UserRegisterValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class UserRegisterValidatorTest {

    @Autowired
    private UserRegisterValidator validator;

    @Autowired
    private EntityBuilder builder;

    @Test
    public void validateHappyPath() {
        User user = this.builder.buildUser();

        List<String> errors = this.validator.validate(user);
        Assertions.assertEquals(0, errors.size());
    }


}
