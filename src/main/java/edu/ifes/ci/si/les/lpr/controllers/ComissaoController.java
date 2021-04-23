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

import edu.ifes.ci.si.les.lpr.model.Comissao;
import edu.ifes.ci.si.les.lpr.repositories.ComissaoRepository;
import edu.ifes.ci.si.les.lpr.services.ComissaoService;

@RestController
@RequestMapping("/comissao")
public class ComissaoController {
	
	@Autowired
	private ComissaoRepository comissaoRepository;
	
	@Autowired
	private ComissaoService comissaoService;
	
	
	@GetMapping
	public List<Comissao> listar() {
		
		return comissaoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@GetMapping("/{comissaoId}")
	public ResponseEntity<Comissao> buscar(@PathVariable Long comissaoId) {
		Optional<Comissao> comissao = comissaoRepository.findById(comissaoId);

		if (comissao.isPresent()) {
			return ResponseEntity.ok(comissao.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comissao adicionar(@Valid @RequestBody Comissao comissao) {
		return comissaoService.salvar(comissao);
	}

	@PutMapping("/{comissaoId}")
	public ResponseEntity<Comissao> atualizar(@Valid @PathVariable Long comissaoId, @RequestBody Comissao comissao){
		
		if(!comissaoRepository.existsById(comissaoId)) {
			return ResponseEntity.notFound().build();
		}		
		comissao.setId(comissaoId);
		comissao = comissaoService.salvar(comissao);
		return ResponseEntity.ok(comissao);		
	}
	
	@DeleteMapping("/{comissaoId}")
	public ResponseEntity<Void> remover(@PathVariable Long comissaoId){		
		if(!comissaoRepository.existsById(comissaoId)) {
			return ResponseEntity.notFound().build();		}
		
		comissaoService.excluir(comissaoId);		
		return ResponseEntity.noContent().build();
	}

}
