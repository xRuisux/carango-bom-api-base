package br.com.caelum.carangobom.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findByEmail(String email) throws NotFoundException {
        Optional<User> user = this.repository.findByEmail(email);
        if (!user.isPresent()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return user.get();
    }

    public User findById(Long id) throws NotFoundException {
        Optional<User> user  = this.repository.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return user.get();
    }

    public List<User> findAllByOrderById()  {
        return this.repository.findAllByOrderById();
    }

    @Transactional
    public User deleteById(Long id) throws NotFoundException{
        User user = this.findById(id);
        this.repository.delete(user);
        return user;
    }
}
