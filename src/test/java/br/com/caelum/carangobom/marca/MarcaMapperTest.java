package br.com.caelum.carangobom.marca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarcaMapperTest {

    @BeforeEach
    public void configuraMock() {
        openMocks(this);
    }
    
	@Test
	void deveRetornarUmaListaDeMarcaMappers() {
		List<Marca> listMarcas = List.of(
				new Marca(1L, "BMW"),
				new Marca(2L, "Fiat")
				);	
		List<MarcaMapper> listMarcasMappers = MarcaMapper.converter(listMarcas);
		
		assertEquals(2, listMarcasMappers.size());
	}

}
