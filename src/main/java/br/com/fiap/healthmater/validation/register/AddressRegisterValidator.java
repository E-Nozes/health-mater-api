package br.com.fiap.healthmater.validation.register;

import br.com.fiap.healthmater.entity.Address;
import br.com.fiap.healthmater.entity.City;
import br.com.fiap.healthmater.entity.State;
import br.com.fiap.healthmater.validation.AddressValidator;
import br.com.fiap.healthmater.validation.search.StateSearchValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for {@link Address} register payload.
 *
 * @author Gabriel Oliveira
 */
@Component
public class AddressRegisterValidator implements AddressValidator {

    private static final String MISSING_CITY_MESSAGE_TEMPLATE = "The City value must not be null. Please fulfill the payload";
    private static final String MISSING_STATE_MESSAGE_TEMPLATE = "The State value must not be null. Please fulfill the payload";
    private static final String MISSING_REQUIRED_FIELD_MESSAGE_TEMPLATE = "The field '%s' must not be null. Please fulfill the payload";

    @Autowired
    private StateSearchValidator stateSearchValidator;

    @Override
    public List<String> validate(Address address) {
        Stream<String> validAddress = validateRequiredFields(address).stream();
        Stream<String> validCity = validateCity(address).stream();
        Stream<String> validState = validateState(address).stream();

        return Stream.of(validAddress, validCity, validState)
                .flatMap(s -> s)
                .collect(toList());
    }

    List<String> validateRequiredFields(Address address) {
        List<String> validationMessages = new ArrayList<>();

        if (address.getAddress() == null || StringUtils.isEmpty(address.getAddress())) {
            validationMessages.add(generateErrorMessage(MISSING_REQUIRED_FIELD_MESSAGE_TEMPLATE, "address"));
        }
        if (address.getNumber() == null || StringUtils.isEmpty(address.getNumber())) {
            validationMessages.add(generateErrorMessage(MISSING_REQUIRED_FIELD_MESSAGE_TEMPLATE, "number"));
        }
        if (address.getZipCode() == null || StringUtils.isEmpty(address.getZipCode())) {
            validationMessages.add(generateErrorMessage(MISSING_REQUIRED_FIELD_MESSAGE_TEMPLATE, "zipCode"));
        }

        return validationMessages;
    }

    private List<String> validateCity(Address address) {
        City city = address.getCity();
        if (city == null || city.getName() == null || StringUtils.isEmpty(city.getName())) {
            return Collections.singletonList(generateErrorMessage(MISSING_CITY_MESSAGE_TEMPLATE));
        }

        return Collections.emptyList();
    }

    private List<String> validateState(Address address) {
        State state = address.getCity().getState();

        if (state == null || state.getId() == null) {
            return Collections.singletonList(generateErrorMessage(MISSING_STATE_MESSAGE_TEMPLATE));
        }

        String validationMessage = this.stateSearchValidator.validateId(state.getId());

        if (validationMessage == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(validationMessage);
    }

    private String generateErrorMessage(String template) {
        return template;
    }

    private String generateErrorMessage(String template, String field) {
        return String.format(template, field);
    }

}
