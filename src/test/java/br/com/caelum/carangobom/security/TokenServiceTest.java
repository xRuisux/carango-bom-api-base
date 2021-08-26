package br.com.caelum.carangobom.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
class TokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Test
    void shouldFailIfTokenIsInvalid() {
        String invalidToken = "eyJhbGciOiJIUzI1NiJ9.";
        assertEquals(false, tokenService.check(invalidToken));
    }
}
