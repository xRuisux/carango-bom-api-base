package br.com.caelum.carangobom.marca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrandFormTest {

	@BeforeEach
    public void configuraMock() {
        openMocks(this);    
    }
	 
	// @Test
	// void deveConverterMarcaParaMarcaForm() {
	// 	Brand brand = new Brand(1L, "BMW");
	// 	BrandForm brandForm = new BrandForm("BMW");
	// 	Brand brandConvertido = brandForm.convertToBrand();
		
	// 	assertEquals(brand.getName(), brandConvertido.getName());
	// }
}
