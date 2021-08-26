package br.com.caelum.carangobom.marca;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrandMapper {
    private String name;
    private Long id;

    public BrandMapper(Brand brand) {
        this.name = brand.getName();
        this.id = brand.getId();
    }
    
	public static List<BrandMapper> converter(List<Brand> brands) {
		return brands.stream().map(BrandMapper::new).collect(Collectors.toList());
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
