package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.ResourceNotFoundException;
import br.com.fiap.healthmater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link User} searching methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class UserSearchValidator {

    private static final String INVALID_ID_MESSAGE_TEMPLATE = "User not found for the given ID [%s]";
    private static final String INVALID_EMAIL_MESSAGE_TEMPLATE = "User not found for the given e-mail [%s]";
    private static final String DUPLICATE_EMAIL_MESSAGE_TEMPLATE = "The given e-mail '%s' is already taken. Please choose another one";

    @Autowired
    private UserRepository userRepository;

    public User verifyIfExists(Integer id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(generateErrorMessage(INVALID_ID_MESSAGE_TEMPLATE, id)));
    }

    public User verifyIfExistsByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException(generateErrorMessage(INVALID_EMAIL_MESSAGE_TEMPLATE, email)));
    }

    public String validateEmail(String email) {
        if (this.userRepository.findByEmail(email).isPresent()) {
            return generateErrorMessage(DUPLICATE_EMAIL_MESSAGE_TEMPLATE, email);
        }

        return null;
    }

    private String generateErrorMessage(String template, Integer id) {
        return String.format(template, id);
    }

    private String generateErrorMessage(String template, String email) {
        return String.format(template, email);
    }

}
