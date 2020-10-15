package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.Profile;
import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.repository.UserRepository;
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

    private static final String INVALID_EMAIL_MESSAGE_TEMPLATE = "The e-mail '%s' has already been taken. Please choose another one.";
    private static final String INVALID_PASSWORD_MESSAGE_TEMPLATE = "The password must be the size between 8 and 16 characters. Please choose another one.";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileSearchValidator profileSearchValidator;

    @Override
    public List<String> validate(User user) {
        Stream<String> validEmail = validateEmail(user).stream();
        Stream<String> validPassword = validatePassword(user).stream();
        Stream<String> validProfiles = validateProfiles(user).stream();

        return Stream.of(validEmail, validPassword, validProfiles)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateEmail(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            return Collections.singletonList(generateErrorMessage(INVALID_EMAIL_MESSAGE_TEMPLATE, email));
        } else {
            return Collections.emptyList();
        }
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
        return user.getProfiles().stream()
                .map(Profile::getId)
                .map(id -> profileSearchValidator.verifyIfExists(id))
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private String generateErrorMessage(String template, String email) {
        String displayEmail = email != null ? email : "<none>";
        return String.format(template,
                displayEmail);
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
