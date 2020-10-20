package br.com.fiap.healthmater.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class for password encryption.
 *
 * @author Gabriel Oliveira
 */
public class CryptPasswordGeneratorUtil {

    private static final Logger log = LoggerFactory.getLogger(CryptPasswordGeneratorUtil.class);

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String output = String.format("Output: %s", encoder.encode("test"));

        log.info(output);
    }

}
