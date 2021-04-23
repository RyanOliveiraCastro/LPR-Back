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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.lpr.model.Caixa;
import edu.ifes.ci.si.les.lpr.repositories.CaixaRepository;
import edu.ifes.ci.si.les.lpr.services.CaixaService;

@RestController
@RequestMapping("/caixa")
public class CaixaController {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private CaixaService caixaService;
	
	
	@GetMapping
	public List<Caixa> listar() {
		
		return caixaRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	@GetMapping("/notUsed")
	public List<Caixa> listarNotUsed() {
		
		return caixaRepository.findCaixaNotUsed();
	}


	@GetMapping("/{caixaId}")
	public ResponseEntity<Caixa> buscar(@PathVariable Long caixaId) {
		Optional<Caixa> caixa = caixaRepository.findById(caixaId);

		if (caixa.isPresent()) {
			return ResponseEntity.ok(caixa.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Caixa adicionar(@Valid @RequestBody Caixa caixa) {
		return caixaService.salvar(caixa);
	}
	
	@DeleteMapping("/{caixaId}")
	public ResponseEntity<Void> remover(@PathVariable Long caixaId){		
		if(!caixaRepository.existsById(caixaId)) {
			return ResponseEntity.notFound().build();		}
		
		caixaService.excluir(caixaId);		
		return ResponseEntity.noContent().build();
	}

}
