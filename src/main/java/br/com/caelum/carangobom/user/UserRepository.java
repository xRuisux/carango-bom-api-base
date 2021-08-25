package br.com.caelum.carangobom.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    Optional<User> delete(User user);
    List<User> findAllByOrderById();

}