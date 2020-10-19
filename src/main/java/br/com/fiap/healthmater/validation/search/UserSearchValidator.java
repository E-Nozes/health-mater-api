package br.com.fiap.healthmater.validation.search;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.ResourceNotFoundException;
import br.com.fiap.healthmater.repository.UserRepository;
import br.com.fiap.healthmater.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link User} searching methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class UserSearchValidator {

    private static final String INVALID_ID_MESSAGE_TEMPLATE = "User not found for the given ID '%s'";
    private static final String INVALID_EMAIL_MESSAGE_TEMPLATE = "User not found for the given e-mail '%s'";
    private static final String DUPLICATE_EMAIL_MESSAGE_TEMPLATE = "The given e-mail '%s' is already taken. Please choose another one";
    private static final String INVALID_PASSWORD_MESSAGE_TEMPLATE = "The given old password is incorrect";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    public User verifyIfExists(Integer id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(generateErrorMessage(INVALID_ID_MESSAGE_TEMPLATE, id)));
    }

    public User verifyIfExistsByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException(generateErrorMessage(INVALID_EMAIL_MESSAGE_TEMPLATE, email)));
    }

    public String validateDuplicatedEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent()
                ? generateErrorMessage(DUPLICATE_EMAIL_MESSAGE_TEMPLATE, email)
                : null;
    }

    public String validatePassword(String password) {
        User user = this.userUtils.findLoggedUser();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(password, user.getPassword())
                ? null
                : generateErrorMessage(INVALID_PASSWORD_MESSAGE_TEMPLATE);
    }

    private String generateErrorMessage(String template) {
        return template;
    }

    private String generateErrorMessage(String template, Integer id) {
        return String.format(template, id);
    }

    private String generateErrorMessage(String template, String email) {
        return String.format(template, email);
    }

}
