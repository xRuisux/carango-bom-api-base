package br.com.caelum.carangobom.usuario;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);

}