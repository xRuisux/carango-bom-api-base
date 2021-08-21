package br.com.caelum.carangobom.seguranca;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.usuario.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${carangobom.jwt.expiration}")
    private String expiration;
    @Value("${carangobom.jwt.secret}")
    private String secret;

    public String generate(Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder().setIssuer("API Carangobom").setSubject(loggedUser.getId().toString()).setIssuedAt(today)
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean check(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getIdUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
