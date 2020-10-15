package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link User} searching methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class ProfileSearchValidator {

    private static final String INVALID_ID_MESSAGE_TEMPLATE = "Profile not found for the given ID [%s]";

    @Autowired
    private ProfileRepository profileRepository;

    public String validateId(Integer id) {
        if (!profileRepository.findById(id).isPresent()) {
            return generateErrorMessage(INVALID_ID_MESSAGE_TEMPLATE, id);
        }

        return null;
    }

    private String generateErrorMessage(String template, Integer id) {
        return String.format(template, id);
    }

}
