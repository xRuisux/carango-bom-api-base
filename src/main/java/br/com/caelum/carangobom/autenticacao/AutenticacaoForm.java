package br.com.caelum.carangobom.autenticacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AutenticacaoForm {
    
    @Email
    private String email;
    @Size(min = 6, message = "Deve ter {min} ou mais caracteres.")
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

}
