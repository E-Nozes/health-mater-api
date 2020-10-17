package br.com.fiap.healthmater.validation.search;

import br.com.fiap.healthmater.entity.City;
import br.com.fiap.healthmater.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link City} searching methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class StateSearchValidator {

    private static final String INVALID_ID_MESSAGE_TEMPLATE = "State not found for the given ID '%s'";

    @Autowired
    private StateRepository stateRepository;

    public String validateId(Integer id) {
        if (!stateRepository.findById(id).isPresent()) {
            return generateErrorMessage(INVALID_ID_MESSAGE_TEMPLATE, id);
        }

        return null;
    }

    private String generateErrorMessage(String template, Integer id) {
        return String.format(template, id);
    }

}
