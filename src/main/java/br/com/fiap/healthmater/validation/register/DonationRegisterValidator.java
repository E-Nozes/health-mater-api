package br.com.fiap.healthmater.validation.register;

import br.com.fiap.healthmater.entity.Donation;
import br.com.fiap.healthmater.validation.DonationValidator;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for {@link Donation} register payload.
 *
 * @author Gabriel Oliveira
 */
@Component
public class DonationRegisterValidator implements DonationValidator {

    private static final PolicyFactory DISALLOW_ALL = new HtmlPolicyBuilder().toFactory();

    private static final String INVALID_AMOUNT_MESSAGE_TEMPLATE = "The given amount '%s' for donation is not valid. Please inform a value that is greater than 0.00";
    private static final String INVALID_DESCRIPTION_MESSAGE_TEMPLATE = "Invalid or malicious content provided. Please enter only letters, numbers, and spaces";

    @Override
    public List<String> validate(Donation donation) {
        Stream<String> validAmount = validateAmount(donation).stream();
        Stream<String> validDescription = validateDescription(donation).stream();

        return Stream.of(validAmount, validDescription)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateAmount(Donation donation) {
        Double amount = donation.getAmount();

        return amount <= 0
                ? Collections.singletonList(generateErrorMessage(INVALID_AMOUNT_MESSAGE_TEMPLATE, amount))
                : Collections.emptyList();
    }

    private List<String> validateDescription(Donation donation) {
        String description = donation.getDescription();

        if (description != null) {
            String sanitized = DISALLOW_ALL.sanitize(description);

            return sanitized.equals(description)
                    ? Collections.emptyList()
                    : Collections.singletonList(generateErrorMessage(INVALID_DESCRIPTION_MESSAGE_TEMPLATE));
        }

        return Collections.emptyList();
    }

    private String generateErrorMessage(String template, Double amount) {
        return String.format(template, amount);
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
