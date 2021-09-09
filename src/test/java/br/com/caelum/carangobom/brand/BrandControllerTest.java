package br.com.caelum.carangobom.brand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class BrandControllerTest {

    private BrandController brandController;
    private UriComponentsBuilder uriBuilder;

    @Mock
    private BrandService brandService;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);

        brandController = new BrandController(brandService);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    }

    /*@Test
    void deveRetornarListaQuandoHouverResultados() {
        List<Brand> brands = List.of(
            new Brand(1L, "Audi"),
            new Brand(2L, "BMW"),
            new Brand(3L, "Fiat")
        );

        when(brandService.findAll())
            .thenReturn(brands);

        List<Brand> resultado = brandController.get(); 
        assertEquals(brands, resultado);
    }*/

    @Test
    void deveRetornarMarcaPeloId() throws Exception {
        Brand audi = new Brand(1L, "Audi");

        when(brandService.findById(1L))
            .thenReturn(audi);

        ResponseEntity<BrandMapper> resposta = brandController.getById(1L);
        assertEquals(audi.getName(), resposta.getBody().getName());
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    void deveRetornarNotFoundExceptionQuandoRecuperarBrandComIdInexistente() throws Exception {
        when(brandService.findById(anyLong()))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));

        assertThrows(ResponseStatusException.class,  () -> {
            brandController.getById(1L);
        });
    }

    @Test
    void deveResponderCreatedELocationQuandoCadastrarBrand() throws Exception {
        BrandForm novaDto = new BrandForm();
        novaDto.setName("Ferrari");

        when(brandService.save(any()))
            .thenAnswer(invocation -> {
                Brand marcaSalva = invocation.getArgument(0, Brand.class);
                marcaSalva.setId(1L);

                return marcaSalva;
            });
            
        ResponseEntity<BrandMapper> resposta = brandController.post(novaDto, uriBuilder);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        assertEquals("http://localhost:8080/brand/1", resposta.getHeaders().getLocation().toString());
    }

    @Test
    void deveAlterarNomeQuandoMarcaExistir() throws Exception{
        BrandForm audiDto = new BrandForm();
        audiDto.setName("NOVA Audi");

        when(brandService.change(1L, audiDto))
            .thenReturn(new Brand(1L, "NOVA Audi"));

        ResponseEntity<BrandMapper> resposta = brandController.put(1L, audiDto);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());

        BrandMapper brandAlterada = resposta.getBody();
        assertEquals("NOVA Audi", brandAlterada.getName());
    }

    @Test
    void naoDeveAlterarMarcaInexistente() throws Exception {
        BrandForm audiDto = new BrandForm();
        audiDto.setName("NOVA Audi");

        when(brandService.change(1L, audiDto))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));

        assertThrows(ResponseStatusException.class,  () -> {
            brandController.put(1L, audiDto);
        });
    }

    @Test
    void deveDeletarMarcaExistente() throws Exception {
        Brand audi = new Brand(1L, "Audi");

        when(brandService.delete(audi.getId()))
            .thenReturn(audi);

        ResponseEntity<BrandMapper> resposta = brandController.delete(1L);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
        verify(brandService).delete(audi.getId());
    }

    @Test
    void naoDeveDeletarBrandInexistente() throws Exception {
        when(brandService.delete(anyLong()))
            .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));

        assertThrows(ResponseStatusException.class,  () -> {
            brandController.delete(1L);
        });
    }
}