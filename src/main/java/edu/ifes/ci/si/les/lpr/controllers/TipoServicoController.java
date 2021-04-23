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

import edu.ifes.ci.si.les.lpr.model.TipoServico;
import edu.ifes.ci.si.les.lpr.repositories.TipoServicoRepository;
import edu.ifes.ci.si.les.lpr.services.TipoServicoService;

@RestController
@RequestMapping("/tipoServico")
public class TipoServicoController {
	
	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	
	@Autowired
	private TipoServicoService tipoServicoService;
	
	
	@GetMapping
	public List<TipoServico> listar() {
		
		return tipoServicoRepository.findAll();
	}

	@GetMapping("/{tipoServicoId}")
	public ResponseEntity<TipoServico> buscar(@PathVariable Long tipoServicoId) {
		Optional<TipoServico> tipoServico = tipoServicoRepository.findById(tipoServicoId);

		if (tipoServico.isPresent()) {
			return ResponseEntity.ok(tipoServico.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoServico adicionar(@Valid @RequestBody TipoServico tipoServico) {
		return tipoServicoService.salvar(tipoServico);
	}

	@PutMapping("/{tipoServicoId}")
	public ResponseEntity<TipoServico> atualizar(@Valid @PathVariable Long tipoServicoId, @RequestBody TipoServico tipoServico){
		
		if(!tipoServicoRepository.existsById(tipoServicoId)) {
			return ResponseEntity.notFound().build();
		}		
		tipoServico.setId(tipoServicoId);
		tipoServico = tipoServicoService.salvar(tipoServico);
		return ResponseEntity.ok(tipoServico);		
	}
	
	@DeleteMapping("/{tipoServicoId}")
	public ResponseEntity<Void> remover(@PathVariable Long tipoServicoId){		
		if(!tipoServicoRepository.existsById(tipoServicoId)) {
			return ResponseEntity.notFound().build();		}
		
		tipoServicoService.excluir(tipoServicoId);		
		return ResponseEntity.noContent().build();
	}

}
