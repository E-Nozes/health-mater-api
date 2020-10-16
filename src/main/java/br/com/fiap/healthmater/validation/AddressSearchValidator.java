package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
