package br.com.caelum.carangobom.brand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.brand.BrandController;
import br.com.caelum.carangobom.brand.BrandService;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
@ActiveProfiles("test")
class BrandControllerTest {
    @Autowired
    private BrandController brandController;
    private UriComponentsBuilder uriBuilder;
    private BrandService brandService;

    // @Mock
    // private BrandRepository brandRepository;

    @BeforeEach
    public void configuraMock() {
        // openMocks(this);

        // brandController = new BrandController(brandRepository);
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");
    }

    // @Test
    // void deveRetornarListaQuandoHouverResultados() {
    //     List<Brand> brands = List.of(
    //         new Brand(1L, "Audi"),
    //         new Brand(2L, "BMW"),
    //         new Brand(3L, "Fiat")
    //     );

    //     when(brandRepository.findAll())
    //         .thenReturn(brands);

    //     List<Brand> resultado = brandController.get(); 
    //     assertEquals(brands, resultado);
    // }

    // @Test
    // void deveRetornarMarcaPeloId() throws Exception {
    //     Brand audi = new Brand(1L, "Audi");

    //     when(brandRepository.findById(1L))
    //         .thenReturn(Optional.of(audi));

    //     ResponseEntity<BrandMapper> resposta = brandController.getById(1L);
    //     assertEquals(audi.getName(), resposta.getBody().getName());
    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());
    // }

    // @Test
    // void deveRetornarNotFoundExceptionQuandoRecuperarBrandComIdInexistente() throws Exception {
    //     when(brandService.findById(anyLong()))
    //             .thenThrow(NotFoundException.class);

    //     ResponseEntity<BrandMapper> resposta = brandController.getById(1L);
    //     assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    // }

    // @Test
    // void deveResponderCreatedELocationQuandoCadastrarBrand() throws Exception {
    //     Brand nova = new Brand("Ferrari");
    //     BrandForm novaDto = new BrandForm();
    //     novaDto.setName(nova.getName());

        // when(brandRepository.save(nova))
        //     .then(invocation -> {
        //         Brand marcaSalva = invocation.getArgument(0, Brand.class);
        //         marcaSalva.setId(1L);

        //         return marcaSalva;
        //     });
            
    //     ResponseEntity<BrandMapper> resposta = brandController.post(novaDto, uriBuilder); //null pointer
    //     assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
    //     assertEquals("http://localhost:8080/brand/1", resposta.getHeaders().getLocation().toString());
    // }

    // @Test
    // void deveAlterarNomeQuandoMarcaExistir() throws Exception{
    //     Brand audi = new Brand(1L, "Audi");
    //     BrandForm audiDto = new BrandForm();
    //     audiDto.setName("NOVA Audi");

    //     when(brandService.findById(1L))
    //         .thenReturn(audi);

    //     ResponseEntity<BrandMapper> resposta = brandController.put(1L, audiDto);
    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());

    //     BrandMapper brandAlterada = resposta.getBody();
    //     assertEquals("NOVA Audi", brandAlterada.getName());
    // }

    // @Test
    // void naoDeveAlterarMarcaInexistente() throws Exception {
    //     when(brandService.findById(anyLong()))
    //             .thenThrow(NotFoundException.class);

    //     BrandForm audiDto = new BrandForm();
    //     audiDto.setName("NOVA Audi");

    //     ResponseEntity<BrandMapper> resposta = brandController.put(1L, audiDto);
    //     assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    // }

    // @Test
    // void deveDeletarMarcaExistente() throws Exception {
    //     Brand audi = new Brand(1l, "Audi");

    //     when(brandService.findById(1L))
    //         .thenReturn(audi);

    //     ResponseEntity<BrandMapper> resposta = brandController.delete(1L);
    //     assertEquals(HttpStatus.OK, resposta.getStatusCode());
    //     verify(brandService).delete(audi.getId());
    // }

    // @Test
    // void naoDeveDeletarBrandInexistente() throws Exception {
    //     when(brandService.findById(anyLong()))
    //             .thenThrow(NotFoundException.class);

    //     ResponseEntity<BrandMapper> resposta = brandController.delete(1L);
    //     assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());

    //     verify(brandService, never()).delete(any());
    // }
}