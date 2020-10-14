package br.com.fiap.healthmater.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidationFailure extends RuntimeException {

    private List<String> validationMessages = new ArrayList<>();

    public ValidationFailure() {
    }

    public ValidationFailure(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    public boolean hasValidationFailures() {
        return !validationMessages.isEmpty();
    }

    public List<String> getValidationMessages() {
        return validationMessages;
    }

}
