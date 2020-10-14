package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.UserValidationFailureException;
import br.com.fiap.healthmater.repository.UserRepository;
import br.com.fiap.healthmater.validation.UserRegisterValidator;
import br.com.fiap.healthmater.validation.UserSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for {@link User} with business rules.
 *
 * @author Gabriel Oliveira
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final UserRegisterValidator registerValidator = new UserRegisterValidator(userRepository);
    private final UserSearchValidator searchValidator = new UserSearchValidator(userRepository);

    public User findById(Integer id) {
        return this.searchValidator.verifyIfExists(id);
    }

    public User create(User user) {
        User persistentUser = this.validateRegistrationPayload(user);

        persistentUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    public User findLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        return this.searchValidator.verifyIfExistsByEmail(email);
    }

    public User validateRegistrationPayload(User user) {
        List<String> validationMessages = this.registerValidator.validate(user);

        UserValidationFailureException validationFailure = new UserValidationFailureException(validationMessages);

        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        return user;
    }

}
