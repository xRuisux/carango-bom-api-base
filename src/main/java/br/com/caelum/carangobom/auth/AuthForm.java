package br.com.caelum.carangobom.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AuthForm {
    
    @Email
    private String email;
    @Size(min = 6, message = "Deve ter {min} ou mais caracteres.")
    private String password;

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
