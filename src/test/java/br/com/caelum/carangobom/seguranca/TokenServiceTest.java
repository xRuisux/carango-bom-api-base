package br.com.caelum.carangobom.seguranca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"carangobom.jwt.secret: my-secret"})
public class TokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Test
    void deverRetornarFalseQuandoTokenInvalidoePassado() throws Exception {
        String tokeninvalido = "eyJhbGciOiJIUzI1NiJ9.";
        assertEquals(false, tokenService.isTokenValido(tokeninvalido));
    }
}
