package br.com.fiap.healthmater.service;

import br.com.fiap.healthmater.entity.Address;
import br.com.fiap.healthmater.entity.City;
import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.exception.UserValidationFailureException;
import br.com.fiap.healthmater.repository.AddressRepository;
import br.com.fiap.healthmater.repository.CityRepository;
import br.com.fiap.healthmater.repository.UserRepository;
import br.com.fiap.healthmater.validation.UserRegisterValidator;
import br.com.fiap.healthmater.validation.UserSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRegisterValidator registerValidator;

    @Autowired
    private UserSearchValidator searchValidator;

    public User findById(Integer id) {
        User user = this.searchValidator.verifyIfExists(id);
        user.setPassword(null);

        return user;
    }

    public User create(User user) {
        User validUser = this.validateRegistrationPayload(user);

        User persistentUser = this.userRepository.save(validUser);

        persistentUser.setPassword(null);

        return persistentUser;
    }

    public User validateRegistrationPayload(User user) {
        List<String> validationMessages = this.registerValidator.validate(user);

        UserValidationFailureException validationFailure = new UserValidationFailureException(validationMessages);

        if (validationFailure.hasValidationFailures()) {
            throw validationFailure;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return user;
    }

}
