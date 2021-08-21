package br.com.caelum.carangobom.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findUserByEmail(String email) throws NotFoundException {
        Optional<User> usuario = this.repository.findByEmail(email);
        if (!usuario.isPresent()) {
            throw new NotFoundException("user not found");
        }
        return usuario.get();

    }
}
