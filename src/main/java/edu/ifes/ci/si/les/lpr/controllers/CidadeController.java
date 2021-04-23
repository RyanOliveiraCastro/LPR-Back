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

import edu.ifes.ci.si.les.lpr.model.Cidade;
import edu.ifes.ci.si.les.lpr.model.Uf;
import edu.ifes.ci.si.les.lpr.repositories.CidadeRepository;
import edu.ifes.ci.si.les.lpr.services.CidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {	

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@GetMapping
	public List<Cidade> listar() {
		
		return cidadeRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

		if (cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/uf/{ufId}")
	public List<Cidade> findbyUf(@PathVariable Long ufId) {
		List<Cidade> cidade = cidadeRepository.findByUf(new Uf(ufId, null, null));

		return cidade;
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@Valid @RequestBody Cidade cidade) {
		
		return cidadeService.salvar(cidade);
		
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@Valid @PathVariable Long cidadeId, @RequestBody Cidade cidade){
		
		if(!cidadeRepository.existsById(cidadeId)) {
			return ResponseEntity.notFound().build();
		}		
		cidade.setId(cidadeId);
		cidade = cidadeService.salvar(cidade);
		return ResponseEntity.ok(cidade);		
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Void> remover(@PathVariable Long cidadeId){		
		if(!cidadeRepository.existsById(cidadeId)) {
			return ResponseEntity.notFound().build();		
			}
		
		cidadeService.excluir(cidadeId);		
		return ResponseEntity.noContent().build();
	}
}
