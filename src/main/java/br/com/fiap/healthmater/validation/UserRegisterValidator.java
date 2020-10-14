package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.UserValidationFailureException;
import br.com.fiap.healthmater.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for {@link User} register payload.
 *
 * @author Gabriel Oliveira
 */
@Component
public class UserRegisterValidator implements UserValidator {

    private static final String INVALID_EMAIL_MESSAGE_TEMPLATE = "The e-mail '%s' has already been taken. Please choose another one for the User [%s]";
    private static final String INVALID_PASSWORD_MESSAGE_TEMPLATE = "The password must be the size between 8 and 16 characters. Please choose another one for the User [%s]";

    private final UserRepository userRepository;

    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> validate(User user) {
        Stream<String> validEmail = validateEmail(user).stream();
        Stream<String> validPassword = validatePassword(user).stream();

        return Stream.of(validEmail, validPassword)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateEmail(User user) {
        String email = user.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            return Collections.singletonList(generateErrorMessage(INVALID_EMAIL_MESSAGE_TEMPLATE, email, user));
        } else {
            return Collections.emptyList();
        }
    }

    private List<String> validatePassword(User user) {
        String password = user.getPassword();

        if (password.length() < 8 || password.length() > 16) {
            return Collections.singletonList(generateErrorMessage(INVALID_PASSWORD_MESSAGE_TEMPLATE, user));
        } else {
            return Collections.emptyList();
        }
    }

    private String generateErrorMessage(String template, String email, User user) {
        String displayEmail = email != null ? email : "<none>";
        return String.format(template,
                displayEmail,
                user.toString());
    }

    private String generateErrorMessage(String template, User user) {
        return String.format(template,
                user.toString());
    }

}
