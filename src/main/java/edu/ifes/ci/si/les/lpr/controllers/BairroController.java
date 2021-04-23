package edu.ifes.ci.si.les.lpr.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.lpr.model.Bairro;
import edu.ifes.ci.si.les.lpr.model.Cidade;
import edu.ifes.ci.si.les.lpr.repositories.BairroRepository;
import edu.ifes.ci.si.les.lpr.services.BairroService;

@RestController
@RequestMapping("/bairro")
public class BairroController {

	@Autowired
	private BairroRepository bairroRepository;

	@Autowired
	private BairroService bairroService;

	@GetMapping
	public List<Bairro> listar() {

		return bairroRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	@GetMapping("/{bairroId}")
	public ResponseEntity<Bairro> buscar(@PathVariable Long bairroId) {
		Optional<Bairro> bairro = bairroRepository.findById(bairroId);

		if (bairro.isPresent()) {
			return ResponseEntity.ok(bairro.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cidade/{cidadeId}")
	public List<Bairro> findbyCidade(@PathVariable Long cidadeId) {
		List<Bairro> bairro = bairroRepository.findByCidade(new Cidade(cidadeId, null, null));

		return bairro;
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Bairro adicionar(@Valid @RequestBody Bairro bairro) {
		return bairroService.salvar(bairro);
	}

	@PutMapping("/{bairroId}")
	public ResponseEntity<Bairro> atualizar(@Valid @PathVariable Long bairroId, @RequestBody Bairro bairro) {

		if (!bairroRepository.existsById(bairroId)) {
			return ResponseEntity.notFound().build();
		}
		bairro.setId(bairroId);
		bairro = bairroService.salvar(bairro);
		return ResponseEntity.ok(bairro);
	}

	@DeleteMapping("/{bairroId}")
	public ResponseEntity<Void> remover(@PathVariable Long bairroId) {
		if (!bairroRepository.existsById(bairroId)) {
			return ResponseEntity.notFound().build();
		}

		bairroService.excluir(bairroId);
		return ResponseEntity.noContent().build();
	}
}
