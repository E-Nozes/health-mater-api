package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.UserValidationFailureException;
import br.com.fiap.healthmater.dto.PasswordUpdateDTO;
import br.com.fiap.healthmater.repository.UserRepository;
import br.com.fiap.healthmater.util.UserUtils;
import br.com.fiap.healthmater.validation.register.UserRegisterValidator;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import br.com.fiap.healthmater.validation.update.PasswordUpdateValidator;
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
    private UserRegisterValidator userRegisterValidator;

    @Autowired
    private UserSearchValidator userSearchValidator;

    @Autowired
    private PasswordUpdateValidator passwordUpdateValidator;

    @Autowired
    private UserUtils userUtils;

    public User findById(Integer id) {
        User user = this.userSearchValidator.verifyIfExists(id);
        user.setPassword(null);

        return user;
    }

    public User create(User user) {
        User validUser = validateRegistrationPayload(user);

        if (validUser.getAddress() != null) {
            validUser = this.addressService.persistAddress(validUser);
        }

        validUser = this.profileService.addDefaultProfile(validUser);

        User persistentUser = this.userRepository.save(validUser);

        persistentUser.setPassword(null);

        return persistentUser;
    }

    public void updatePassword(PasswordUpdateDTO passwordUpdateDTO) {
        List<String> validationMessages = this.passwordUpdateValidator.validate(passwordUpdateDTO);

        UserValidationFailureException validationFailure = new UserValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        User user = this.userUtils.findLoggedUser();

        user.setPassword(new BCryptPasswordEncoder().encode(passwordUpdateDTO.getNewPassword()));

        this.userRepository.save(user);
    }

    private User validateRegistrationPayload(User user) {
        List<String> validationMessages = this.userRegisterValidator.validate(user);

        UserValidationFailureException validationFailure = new UserValidationFailureException(validationMessages);
        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return user;
    }

}
