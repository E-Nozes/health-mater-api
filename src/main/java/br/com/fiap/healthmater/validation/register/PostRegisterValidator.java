package br.com.fiap.healthmater.validation.register;

import br.com.fiap.healthmater.entity.Post;
import br.com.fiap.healthmater.validation.PostValidator;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Validator class for {@link Post} register payload.
 *
 * @author Gabriel Oliveira
 */
@Component
public class PostRegisterValidator implements PostValidator {

    private static final PolicyFactory DISALLOW_ALL = new HtmlPolicyBuilder().toFactory();

    private static final String INVALID_CONTENT_MESSAGE_TEMPLATE = "Invalid or malicious content provided. Please enter only letters, numbers, and spaces";

    @Override
    public List<String> validate(Post post) {
        Stream<String> validContent = validateContent(post).stream();

        return Stream.of(validContent)
                .flatMap(s -> s)
                .collect(toList());
    }

    private List<String> validateContent(Post post) {
        String sanitized = DISALLOW_ALL.sanitize(post.getContent());

        return sanitized.equals(post.getContent())
                ? Collections.emptyList()
                : Collections.singletonList(generateErrorMessage(INVALID_CONTENT_MESSAGE_TEMPLATE));
    }

    private String generateErrorMessage(String template) {
        return template;
    }

}
