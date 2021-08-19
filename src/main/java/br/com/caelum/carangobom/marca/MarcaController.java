package br.com.caelum.carangobom.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private MarcaRepository marcaRepository;

    @Autowired
    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @GetMapping
    public List<Marca> lista() {
        return marcaRepository.findAllByOrderByNome();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> id(@PathVariable Long id) {
        Optional<Marca> marca = marcaRepository.findById(id);
        if (marca.isPresent()) {
            return ResponseEntity.ok(marca.get());
        }
            
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MarcaMapper> cadastra(@Valid @RequestBody MarcaForm marcaDto, UriComponentsBuilder uriBuilder) {
        Marca marca = marcaDto.convertToMarca();
        marca = marcaRepository.save(marca);
        URI location = uriBuilder.path("/marcas/{id}").buildAndExpand(marca.getId()).toUri();
        return ResponseEntity.created(location).body(new MarcaMapper(marca));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MarcaMapper> altera(@PathVariable Long id, @Valid @RequestBody MarcaForm marcaDto) {
        Optional<Marca> optional = marcaRepository.findById(id);

        if (optional.isPresent()) {
            Marca marcaConvertido = marcaDto.convertToMarca();
            Marca marca = new Marca();
            
            marca = optional.get();
            marca.setNome(marcaConvertido.getNome());

            return ResponseEntity.ok(new MarcaMapper(marca));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Marca> deleta(@PathVariable Long id) {
        Optional<Marca> optional = marcaRepository.findById(id);
        if (optional.isPresent()) {
            Marca marca = optional.get();
            marcaRepository.delete(marca);
            return ResponseEntity.ok(marca);
        }

        return ResponseEntity.notFound().build();
    }
}
