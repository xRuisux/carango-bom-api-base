package br.com.caelum.carangobom.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public Users findByEmail(String email) throws NotFoundException {
        Optional<Users> user = this.repository.findByEmail(email);
        if (!user.isPresent()) {
            throw new NotFoundException("user not found");
        }
        return user.get();
    }

    public Users findById(Long id) throws NotFoundException {
        Optional<Users> user = this.repository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("user not found");
        }
        return user.get();
    }
}
