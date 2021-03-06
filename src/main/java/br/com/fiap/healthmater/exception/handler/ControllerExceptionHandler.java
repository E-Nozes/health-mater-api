package br.com.fiap.healthmater.exception.handler;

import br.com.fiap.healthmater.exception.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Treat all exceptions from the Controller layer.
 *
 * @author Gabriel Oliveira
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> userMessages = Collections.singletonList(messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale()));
        String completeMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        log.error(completeMessage);
        return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errorList = createErrorList(ex.getBindingResult());

        return handleExceptionInternal(ex, errorList, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        List<String> userMessages = Collections.singletonList(messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale()));
        String completeMessage = ex.toString();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        log.error(completeMessage);
        return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        List<String> userMessages = Collections.singletonList(messageSource.getMessage("resource.operation-not-allowed", null, LocaleContextHolder.getLocale()));
        String completeMessage = ExceptionUtils.getRootCauseMessage(ex);
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        log.error(completeMessage);
        return handleExceptionInternal(ex, errorList, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        List<String> userMessages = Collections.singletonList(messageSource.getMessage("error.unexpected", null, LocaleContextHolder.getLocale()));
        String completeMessage = ex.getMessage();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        log.error(completeMessage);
        return new ResponseEntity<>(errorList, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        List<String> userMessages = Collections.singletonList(ex.getMessage());
        String completeMessage = ex.getMessage();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        log.error(completeMessage);
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        List<String> userMessages = Collections.singletonList(messageSource.getMessage("error.access-denied", null, LocaleContextHolder.getLocale()));
        String completeMessage = ex.getMessage();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        log.error(completeMessage);
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserValidationFailureException.class})
    public ResponseEntity<Object> handleUserValidationFailureException(UserValidationFailureException ex) {
        List<String> userMessages = ex.getValidationMessages();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PostValidationFailureException.class})
    public ResponseEntity<Object> handlePostValidationFailureException(PostValidationFailureException ex) {
        List<String> userMessages = ex.getValidationMessages();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({LikeValidationFailureException.class})
    public ResponseEntity<Object> handleLikeValidationFailureException(LikeValidationFailureException ex) {
        List<String> userMessages = ex.getValidationMessages();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DonationValidationFailureException.class})
    public ResponseEntity<Object> handleDonationValidationFailureException(DonationValidationFailureException ex) {
        List<String> userMessages = ex.getValidationMessages();
        List<Error> errorList = Collections.singletonList(new Error(userMessages));

        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    private List<Error> createErrorList(BindingResult bindingResult) {
        List<Error> errorList = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            List<String> userMessages = Collections.singletonList(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            String completeMessage = fieldError.toString();

            log.error(completeMessage);

            errorList.add(new Error(userMessages));
        });

        return errorList;
    }

    public static class Error {

        private final List<String> userMessages;

        public Error(List<String> userMessages) {
            this.userMessages = userMessages;
        }

        public List<String> getUserMessage() {
            return userMessages;
        }

    }

}
