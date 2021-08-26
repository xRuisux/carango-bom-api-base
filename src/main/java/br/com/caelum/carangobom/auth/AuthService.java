package br.com.caelum.carangobom.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.user.UserService;


@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return this.usersService.findByEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Dados inválidos!");
        }
    }

}
