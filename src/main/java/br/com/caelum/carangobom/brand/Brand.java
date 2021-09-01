package br.com.caelum.carangobom.brand;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;

@Entity
public class Brand {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Brand() {

    }

    public Brand(String name) {
        this(null, name);
    }

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
