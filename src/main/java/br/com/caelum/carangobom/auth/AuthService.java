package br.com.caelum.carangobom.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.caelum.carangobom.users.UsersService;
import javassist.NotFoundException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthService implements UserDetailsService {

    @Autowired
    UsersService usersService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            return this.usersService.findByEmail(username);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Dados inválidos!");        
        }
    }
}
