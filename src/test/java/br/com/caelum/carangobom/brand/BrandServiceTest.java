package br.com.caelum.carangobom.brand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.Optional;

class BrandServiceTest {

    private BrandService brandService;
    @Mock
    BrandRepository brandRepository;

    @BeforeEach
    public void configuraMock() {
        openMocks(this);

        brandService = new BrandService(brandRepository);
    }

    @Test
    void MustReturnANotFoundExceptionWhenIdNotExists() throws Exception {

        when(brandRepository.findById(anyLong()))
            .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> brandService.findById(1L));
    }

    @Test
    void MustReturnAInternalServerErrorExceptionWhenCannotSaveBrand() throws Exception {
        Brand myBrand = new Brand(2L, null);
        when(brandRepository.save(any()))
            .thenReturn(new Exception("Um erro qualquer no banco"));

        assertThrows(ResponseStatusException.class, () -> brandService.save(myBrand));
    }

    @Test
    void MustReturnABrandWhenItCanBeSaveBrand() throws Exception {
        Brand brandToSave = new Brand(null, "Ferrari");

        when(brandRepository.save(any()))
            .thenAnswer(invocation -> {
                Brand marcaSalva = invocation.getArgument(0, Brand.class);
                marcaSalva.setId(1L);

                return marcaSalva;
            });
        Brand brand = brandService.save(brandToSave);

        assertEquals(1L, brand.getId());
        assertEquals(brandToSave.getName(), brand.getName());  
    }

    @Test
    void MustReturnABrandChanged() throws Exception {
        Brand brandSaved = new Brand(1l, "Ferrari");
        BrandForm novaDto = new BrandForm();
        novaDto.setName("Audi");

        when(brandRepository.findById(anyLong()))
            .thenReturn(Optional.of(brandSaved));
        Brand brand = brandService.change(1l,novaDto);

        assertEquals(1L, brand.getId());
        assertEquals("Audi", brand.getName());  
    }

    @Test
    void MustCallDeleteFunction() throws Exception {
        Brand brandSaved = new Brand(1l, "Ferrari");
        BrandForm novaDto = new BrandForm();
        novaDto.setName("Audi");

        when(brandRepository.findById(anyLong()))
            .thenReturn(Optional.of(brandSaved));
        brandService.delete(1L);
        verify(brandRepository, times(1)).delete(any()); 
    }
}