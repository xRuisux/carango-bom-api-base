package br.com.caelum.carangobom.autenticacao;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.carangobom.seguranca.TokenService;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<AutenticacaoDto> autenticar(@RequestBody @Valid AutenticacaoForm form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.converter();
        Authentication authentication = authenticationManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(authentication);
        return ResponseEntity.ok(new AutenticacaoDto(token, "Bearer"));
    }
}
        

    

