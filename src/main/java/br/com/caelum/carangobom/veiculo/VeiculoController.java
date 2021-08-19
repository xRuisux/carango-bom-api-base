package br.com.caelum.carangobom.veiculo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.carangobom.marca.MarcaRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private MarcaRepository marcaRepository;

	@PostMapping
	public ResponseEntity<VeiculoDto> cadastrar(@RequestBody VeiculoForm form) {
		Veiculo veiculo = form.converter(marcaRepository);
	}
	
	
}
