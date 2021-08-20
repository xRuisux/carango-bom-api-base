package br.com.caelum.carangobom.marca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MarcaFormTest {

	@BeforeEach
    public void configuraMock() {
        openMocks(this);    
    }
	 
	@Test
	void deveConverterMarcaParaMarcaForm() {
		Marca marca = new Marca(1L, "BMW");
		MarcaForm marcaForm = new MarcaForm("BMW");
		Marca marcaConvertido = marcaForm.convertToMarca();
		
		assertEquals(marca.getNome(), marcaConvertido.getNome());
	}

}
