package br.com.caelum.carangobom.brand;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrandMapperTest {

    @BeforeEach
    public void configuraMock() {
        openMocks(this);
    }
    
	@Test
	void deveRetornarUmaListaDeBrandMappers() {
		List<Brand> listBrands = List.of(
				new Brand(1L, "BMW"),
				new Brand(2L, "Fiat")
				);	
		List<BrandMapper> listBrandsMappers = BrandMapper.converter(listBrands);
		
		assertEquals(2, listBrandsMappers.size());
	}
}
