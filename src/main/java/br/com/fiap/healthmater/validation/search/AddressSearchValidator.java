package br.com.fiap.healthmater.validation.search;

import br.com.fiap.healthmater.entity.Address;
import br.com.fiap.healthmater.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Validator class for {@link Address} searching methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class AddressSearchValidator {

    private static final String INVALID_ID_MESSAGE_TEMPLATE = "Address not found for the given ID '%s'";

    @Autowired
    private AddressRepository addressRepository;

    public String validateId(Integer id) {
        if (!addressRepository.findById(id).isPresent()) {
            return generateErrorMessage(INVALID_ID_MESSAGE_TEMPLATE, id);
        }

        return null;
    }

    private String generateErrorMessage(String template, Integer id) {
        return String.format(template, id);
    }

}
