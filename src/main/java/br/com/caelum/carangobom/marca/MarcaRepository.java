package br.com.caelum.carangobom.marca;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{

	List<Marca> findAllByOrderByNome();
}
