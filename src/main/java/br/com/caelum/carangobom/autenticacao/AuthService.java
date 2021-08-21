package br.com.caelum.carangobom.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.caelum.carangobom.usuario.UserService;
import javassist.NotFoundException;

public class AuthService implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            return this.userService.findUserByEmail(username);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Dados inválidos!");        
        }
    }
}
