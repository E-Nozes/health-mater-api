package br.com.fiap.healthmater.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility class for password encryption.
 *
 * @author Gabriel Oliveira
 */
public class CryptPasswordGeneratorUtils {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("your_password"));
    }

}
