package br.com.caelum.carangobom.marca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    // @Test
    // void deveRetornarListaQuandoHouverResultados() {
    //     List<Brand> brands = List.of(
    //         new Brand(1L, "Audi"),
    //         new Brand(2L, "BMW"),
    //         new Brand(3L, "Fiat")
    //     );

    //     when(brandService.findAll())
    //         .thenReturn(brands);

    //     List<Brand> resultado = brandController.lista(); 
    //     assertEquals(brands, resultado);
    // }

    // @Test
    // void deveRetornarMarcaPeloId() {
    //     Marca audi = new Marca(1L, "Audi");

    //     when(marcaService.findById(1L))
    //         .thenReturn(audi);

    //     ResponseEntity resposta = marcaController.id(1L);
    //     assertEquals(audi, resposta.getBody());
    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());
    // }

    // @Test
    // void deveRetornarNotFoundExceptionQuandoRecuperarMarcaComIdInexistente() {
    //     when(marcaService.findById(anyLong()))
    //             .thenReturn(NotFoundException.class);

    //     ResponseEntity<?> resposta = marcaController.id(1L);
    //     assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    // }

    // @Test
    // void deveResponderCreatedELocationQuandoCadastrarMarca() {
    //     Marca nova = new Marca("Ferrari");
    //     MarcaForm novaDto = new MarcaForm();
    //     novaDto.setNome(nova.getNome());

    //     when(marcaService.save(nova))
    //         .then(invocation -> {
    //             Marca marcaSalva = invocation.getArgument(0, Marca.class);
    //             marcaSalva.setId(1L);

    //             return marcaSalva;
    //         });

    //     ResponseEntity resposta = marcaController.cadastra(novaDto, uriBuilder);
    //     assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    //     assertEquals("http://localhost:8080/marcas/1", resposta.getHeaders().getLocation().toString());
    // }

    // @Test
    // void deveAlterarNomeQuandoMarcaExistir() {
    //     Marca audi = new Marca(1L, "Audi");
    //     MarcaForm audiDto = new MarcaForm();
    //     audiDto.setNome("NOVA Audi");

    //     when(marcaService.findById(1L))
    //         .thenReturn(Optional.of(audi));

    //     ResponseEntity resposta = marcaController.altera(1L, audiDto);
    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());

    //     MarcaMapper marcaAlterada = resposta.getBody();
    //     assertEquals("NOVA Audi", marcaAlterada.getNome());
    // }

    // @Test
    // void naoDeveAlterarMarcaInexistente() {
    //     when(marcaService.findById(anyLong()))
    //             .thenReturn(Optional.empty());

    //     MarcaForm audiDto = new MarcaForm();
    //     audiDto.setNome("NOVA Audi");

    //     ResponseEntity resposta = marcaController.altera(1L, audiDto);
    //     assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    // }

    // @Test
    // void deveDeletarMarcaExistente() {
    //     Marca audi = new Marca(1l, "Audi");

    //     when(marcaService.findById(1L))
    //         .thenReturn(Optional.of(audi));

    //     ResponseEntity resposta = marcaController.deleta(1L);
    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());
    //     verify(marcaService).delete(audi);
    // }

    // @Test
    // void naoDeveDeletarMarcaInexistente() {
    //     when(marcaService.findById(anyLong()))
    //             .thenReturn(Optional.empty());

    //     ResponseEntity resposta = marcaController.deleta(1L);
    //     assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());

    //     verify(marcaService, never()).delete(any());
    // }

}