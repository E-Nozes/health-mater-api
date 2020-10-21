package br.com.fiap.healthmater.validation.update;

import br.com.fiap.healthmater.dto.PasswordUpdateDTO;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import br.com.fiap.healthmater.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for password update methods.
 *
 * @author Gabriel Oliveira
 */
@Component
public class PasswordUpdateValidator implements PasswordValidator {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 16;

    private static final String INVALID_PASSWORD_MESSAGE_TEMPLATE = "The password must be the size between 8 and 16 characters. Please choose another one";
    private static final String PASSWORD_MISMATCH_MESSAGE_TEMPLATE = "The given new passwords don't match";

    @Autowired
    private UserSearchValidator userSearchValidator;

    @Override
    public List<String> validate(PasswordUpdateDTO passwordUpdateDTO) {
        Stream<String> validInput = validateInput(passwordUpdateDTO).stream();
        Stream<String> validCurrentPassword = validateCurrentPassword(passwordUpdateDTO).stream();
        Stream<String> passwordsMatch = validateMatch(passwordUpdateDTO).stream();

        return Stream.of(validInput, validCurrentPassword, passwordsMatch)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateInput(PasswordUpdateDTO passwordUpdateDTO) {
        String password = passwordUpdateDTO.getNewPassword();

        return password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH
                ? Collections.singletonList(generateErrorMessage(INVALID_PASSWORD_MESSAGE_TEMPLATE))
                : Collections.emptyList();
    }

    private List<String> validateCurrentPassword(PasswordUpdateDTO passwordUpdateDTO) {
        String validationMessage = this.userSearchValidator.validatePassword(passwordUpdateDTO.getOldPassword());

        return validationMessage == null ? Collections.emptyList() : Collections.singletonList(validationMessage);
    }

    private List<String> validateMatch(PasswordUpdateDTO passwordUpdateDTO) {
        return passwordUpdateDTO.getNewPassword().equals(passwordUpdateDTO.getRepeatPassword())
                ? Collections.emptyList()
                : Collections.singletonList(generateErrorMessage(PASSWORD_MISMATCH_MESSAGE_TEMPLATE));
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
