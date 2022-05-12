package org.mvnsearch;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class PasswordHashingTest {
    @Test
    public void testArgon2() throws Exception {
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(32, 64, 1, 15 * 1024, 2);
        String myPassword = "ThisIsMyPassword";
        final String encodedPassword = passwordEncoder.encode(myPassword);
        System.out.println(encodedPassword);
        boolean validPassword = passwordEncoder.matches(myPassword, encodedPassword);
        System.out.println(validPassword);
    }
}
