package br.com.caelum.carangobom.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findByEmail(String email) throws ResponseStatusException {
        Optional<User> user = this.repository.findByEmail(email);
        return this.getUserIfItExists(user);
    }

    public User findById(Long id) throws ResponseStatusException {
        Optional<User> user  = this.repository.findById(id);
        return this.getUserIfItExists(user);
    }

    public List<User> findAllByOrderById()  {
        return this.repository.findAllByOrderById();
    }
    
    private User getUserIfItExists(Optional<User> user) throws ResponseStatusException{
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return user.get();
    }
    @Transactional
    public User deleteById(Long id) throws ResponseStatusException{
        User user = this.findById(id);
        this.repository.delete(user);
        return user;
    }
}
