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

import edu.ifes.ci.si.les.lpr.model.Uf;
import edu.ifes.ci.si.les.lpr.repositories.UfRepository;
import edu.ifes.ci.si.les.lpr.services.UfService;

@RestController
@RequestMapping("/uf")
public class UfController {
	
	@Autowired
	private UfRepository ufRepository;
	
	@Autowired
	private UfService ufService;
	
	
	@GetMapping
	public List<Uf> listar() {		
		return ufRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	@GetMapping("/{ufId}")
	public ResponseEntity<Uf> buscar(@PathVariable Long ufId) {
		Optional<Uf> uf = ufRepository.findById(ufId);

		if (uf.isPresent()) {
			return ResponseEntity.ok(uf.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Uf adicionar(@Valid @RequestBody Uf uf) {
		return ufService.salvar(uf);
	}

	@PutMapping("/{ufId}")
	public ResponseEntity<Uf> atualizar(@Valid @PathVariable Long ufId, @RequestBody Uf uf){
		
		if(!ufRepository.existsById(ufId)) {
			return ResponseEntity.notFound().build();
		}		
		uf.setId(ufId);
		uf = ufService.salvar(uf);
		return ResponseEntity.ok(uf);		
	}
	
	@DeleteMapping("/{ufId}")
	public ResponseEntity<Void> remover(@PathVariable Long ufId){		
		if(!ufRepository.existsById(ufId)) {
			return ResponseEntity.notFound().build();		}
		
		ufService.excluir(ufId);		
		return ResponseEntity.noContent().build();
	}

}
