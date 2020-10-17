package br.com.fiap.healthmater.util;

import br.com.fiap.healthmater.entity.User;
import br.com.fiap.healthmater.validation.search.UserSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Utility class for {@link User}.
 *
 * @author Gabriel Oliveira
 */
@Service
public class UserUtils {

    @Autowired
    private UserSearchValidator searchValidator;

    public User findLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        return this.searchValidator.verifyIfExistsByEmail(email);
    }

}
