package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.dto.PasswordUpdateDTO;
import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.UserValidationFailureException;
import br.com.fiap.healthmater.repository.UserRepository;
import br.com.fiap.healthmater.util.UserUtil;
import br.com.fiap.healthmater.validation.PasswordValidator;
import br.com.fiap.healthmater.validation.UserValidator;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AddressService addressService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserSearchValidator userSearchValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserUtil userUtil;

    public User findById(Integer id) {
        User user = this.userSearchValidator.verifyIfExists(id);
        user.setPassword(null);

        return user;
    }

    public User create(User user) {
        validateRegistrationPayload(user);

        if (user.getAddress() != null) {
            user = this.addressService.persistAddress(user);
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        user = this.profileService.addDefaultProfile(user);

        User persistentUser = this.userRepository.save(user);

        persistentUser.setPassword(null);

        return persistentUser;
    }

    public void updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
        List<String> validationMessages = this.passwordValidator.validate(passwordUpdateDTO);

        UserValidationFailureException validationFailure = new UserValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        User user = this.userUtil.findLoggedUser();

        user.setPassword(new BCryptPasswordEncoder().encode(passwordUpdateDTO.getNewPassword()));

        this.userRepository.save(user);
    }

    private void validateRegistrationPayload(User user) {
        List<String> validationMessages = this.userValidator.validate(user);

        UserValidationFailureException validationFailure = new UserValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }
    }

}
