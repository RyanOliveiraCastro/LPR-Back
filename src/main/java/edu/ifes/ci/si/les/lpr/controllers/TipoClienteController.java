package edu.ifes.ci.si.les.lpr.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import edu.ifes.ci.si.les.lpr.model.TipoCliente;
import edu.ifes.ci.si.les.lpr.repositories.TipoClienteRepository;
import edu.ifes.ci.si.les.lpr.services.TipoClienteService;

@RestController
@RequestMapping("/tipoCliente")
public class TipoClienteController {

	@Autowired
	private TipoClienteRepository tipoClienteRepository;
	@Autowired
	private TipoClienteService tipoClienteService;

	@GetMapping
	public List<TipoCliente> listar() {

		return tipoClienteRepository.findAll();
	}

	@GetMapping("/{tipoClienteId}")
	public ResponseEntity<TipoCliente> buscar(@PathVariable Long tipoClienteId) {
		Optional<TipoCliente> tipoCliente = tipoClienteRepository.findById(tipoClienteId);

		if (tipoCliente.isPresent()) {
			return ResponseEntity.ok(tipoCliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)	
	public TipoCliente adicionar(@Valid @RequestBody TipoCliente tipoCliente) {

		return tipoClienteService.salvar(tipoCliente);

	}

	@PutMapping(value = "/{tipoClienteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipoCliente> atualizar(@Valid @PathVariable Long tipoClienteId, @RequestBody TipoCliente tipoCliente) {

		if (!tipoClienteRepository.existsById(tipoClienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		tipoCliente.setId(tipoClienteId);
		
		tipoCliente = tipoClienteService.salvar(tipoCliente);
		return ResponseEntity.ok(tipoCliente);
	}

	@DeleteMapping("/{tipoClienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long tipoClienteId) {
		if (!tipoClienteRepository.existsById(tipoClienteId)) {
			return ResponseEntity.notFound().build();
		}

		tipoClienteService.excluir(tipoClienteId);
		return ResponseEntity.noContent().build();
	}
}
