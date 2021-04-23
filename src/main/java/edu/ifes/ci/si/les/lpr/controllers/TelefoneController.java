package edu.ifes.ci.si.les.lpr.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import edu.ifes.ci.si.les.lpr.model.Telefone;
import edu.ifes.ci.si.les.lpr.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.lpr.services.TelefoneService;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Autowired
	private TelefoneService telefoneService;
	
	
	@GetMapping
	public List<Telefone> listar() {
		
		return telefoneRepository.findAll();
	}

	@GetMapping("/{telefoneId}")
	public ResponseEntity<Telefone> buscar(@PathVariable Long telefoneId) {
		Optional<Telefone> telefone = telefoneRepository.findById(telefoneId);

		if (telefone.isPresent()) {
			return ResponseEntity.ok(telefone.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Telefone adicionar(@Valid @RequestBody Telefone telefone) {
		return telefoneService.salvar(telefone);
	}

	@PutMapping("/{telefoneId}")
	public ResponseEntity<Telefone> atualizar(@Valid @PathVariable Long telefoneId, @RequestBody Telefone telefone){
		
		if(!telefoneRepository.existsById(telefoneId)) {
			return ResponseEntity.notFound().build();
		}		
		telefone.setId(telefoneId);
		telefone = telefoneService.salvar(telefone);
		return ResponseEntity.ok(telefone);		
	}
	
	@DeleteMapping("/{telefoneId}")
	public ResponseEntity<Void> remover(@PathVariable Long telefoneId){		
		if(!telefoneRepository.existsById(telefoneId)) {
			return ResponseEntity.notFound().build();		}
		
		telefoneService.excluir(telefoneId);		
		return ResponseEntity.noContent().build();
	}

}
