package br.com.fiap.healthmater.validation;

import br.com.fiap.healthmater.entity.Address;
import br.com.fiap.healthmater.entity.City;
import br.com.fiap.healthmater.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class AddressRegisterValidator implements AddressValidator {

    private static final String MISSING_CITY_MESSAGE_TEMPLATE = "The City value must not be null. Please fulfill the payload";
    private static final String MISSING_STATE_MESSAGE_TEMPLATE = "The State value must not be null. Please fulfill the payload";

    @Autowired
    private StateSearchValidator stateSearchValidator;

    @Override
    public List<String> validate(Address address) {
        Stream<String> validCity = validateCity(address).stream();
        Stream<String> validState = validateState(address).stream();

        return Stream.of(validCity, validState)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateCity(Address address) {
        City city = address.getCity();
        if (city == null || city.getName().isEmpty()) {
            return Collections.singletonList(generateErrorMessage(MISSING_CITY_MESSAGE_TEMPLATE));
        }

        return Collections.emptyList();
    }

    private List<String> validateState(Address address) {
        State state = address.getCity().getState();

        if (state == null || state.getId() == null) {
            return Collections.singletonList(generateErrorMessage(MISSING_STATE_MESSAGE_TEMPLATE));
        }

        String validationMessage = stateSearchValidator.validateId(state.getId());

        if (validationMessage == null) {
            return Collections.emptyList();
        }

        return Collections.singletonList(validationMessage);
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
