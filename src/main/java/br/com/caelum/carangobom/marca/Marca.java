package br.com.caelum.carangobom.marca;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "marca")
public class Marca {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(min = 2, message = "Deve ter {min} ou mais caracteres.")
    private String nome;

    public Marca() {

    }

    public Marca(String nome) {
        this(null, nome);
    }

    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
