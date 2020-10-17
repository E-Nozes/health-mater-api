package br.com.fiap.healthmater.validation.register;

import br.com.fiap.healthmater.entity.Profile;
import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.validation.contract.UserValidator;
import br.com.fiap.healthmater.validation.register.AddressRegisterValidator;
import br.com.fiap.healthmater.validation.search.ProfileSearchValidator;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for {@link User} register payload.
 *
 * @author Gabriel Oliveira
 */
@Component
public class UserRegisterValidator implements UserValidator {

    private static final String INVALID_PASSWORD_MESSAGE_TEMPLATE = "The password must be the size between 8 and 16 characters. Please choose another one";

    @Autowired
    private UserSearchValidator userSearchValidator;

    @Autowired
    private ProfileSearchValidator profileSearchValidator;

    @Autowired
    private AddressRegisterValidator addressRegisterValidator;

    @Override
    public List<String> validate(User user) {
        Stream<String> validEmail = validateEmail(user).stream();
        Stream<String> validPassword = validatePassword(user).stream();
        Stream<String> validProfiles = validateProfiles(user).stream();
        Stream<String> validAddress = validateAddress(user).stream();

        return Stream.of(validEmail, validPassword, validProfiles, validAddress)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateEmail(User user) {
        String validationMessage = this.userSearchValidator.validateEmail(user.getEmail());

        if (validationMessage == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(validationMessage);
    }

    private List<String> validatePassword(User user) {
        String password = user.getPassword();

        if (password.length() < 8 || password.length() > 16) {
            return Collections.singletonList(generateErrorMessage(INVALID_PASSWORD_MESSAGE_TEMPLATE));
        } else {
            return Collections.emptyList();
        }
    }

    private List<String> validateProfiles(User user) {
        if (user.getProfiles() != null && !user.getProfiles().isEmpty()) {
            return user.getProfiles().stream()
                    .map(Profile::getId)
                    .map(id -> this.profileSearchValidator.validateId(id))
                    .filter(Objects::nonNull)
                    .collect(toList());
        }

        return Collections.emptyList();
    }

    private List<String> validateAddress(User user) {
        if (user.getAddress() != null) {
            return this.addressRegisterValidator.validate(user.getAddress());
        }

        return Collections.emptyList();
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
