package br.com.fiap.healthmater.validation.update;

import br.com.fiap.healthmater.model.PasswordUpdateModel;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import br.com.fiap.healthmater.validation.validator.PasswordValidator;
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

    private static final String INVALID_PASSWORD_MESSAGE_TEMPLATE = "The password must be the size between 8 and 16 characters. Please choose another one";
    private static final String PASSWORD_MISMATCH_MESSAGE_TEMPLATE = "The given new passwords don't match";

    @Autowired
    private UserSearchValidator userSearchValidator;

    @Override
    public List<String> validate(PasswordUpdateModel passwordUpdateModel) {
        Stream<String> validPassword = validatePassword(passwordUpdateModel).stream();
        Stream<String> validCurrentPassword = validateCurrentPassword(passwordUpdateModel).stream();
        Stream<String> passwordsMatch = validateMatch(passwordUpdateModel).stream();

        return Stream.of(validPassword, validCurrentPassword, passwordsMatch)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validatePassword(PasswordUpdateModel passwordUpdateModel) {
        String password = passwordUpdateModel.getNewPassword();

        return password.length() < 8 || password.length() > 16
                ? Collections.singletonList(generateErrorMessage(INVALID_PASSWORD_MESSAGE_TEMPLATE))
                : Collections.emptyList();
    }

    private List<String> validateCurrentPassword(PasswordUpdateModel passwordUpdateModel) {
        String validationMessage = this.userSearchValidator.validatePassword(passwordUpdateModel.getOldPassword());

        return validationMessage == null ? Collections.emptyList() : Collections.singletonList(validationMessage);
    }

    private List<String> validateMatch(PasswordUpdateModel passwordUpdateModel) {
        return passwordUpdateModel.getNewPassword().equals(passwordUpdateModel.getRepeatPassword())
                ? Collections.emptyList()
                : Collections.singletonList(generateErrorMessage(PASSWORD_MISMATCH_MESSAGE_TEMPLATE));
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
