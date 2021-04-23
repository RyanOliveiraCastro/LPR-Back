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

import edu.ifes.ci.si.les.lpr.model.TabelaPreco;
import edu.ifes.ci.si.les.lpr.model.TipoCliente;
import edu.ifes.ci.si.les.lpr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.TabelaPrecoRepository;
import edu.ifes.ci.si.les.lpr.services.TabelaPrecoService;

@RestController
@RequestMapping("/tabelaPreco")
public class TabelaPrecoController {
	
	@Autowired
	private TabelaPrecoRepository tabelaPrecoRepository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private TabelaPrecoService tabelaPrecoService;
	
	@GetMapping
	public List<TabelaPreco> listar() {
		
		return tabelaPrecoRepository.findAll(Sort.by(Sort.Direction.ASC, "descricao"));
	}

	@GetMapping("/{tabelaPrecoId}")
	public ResponseEntity<TabelaPreco> buscar(@PathVariable Long tabelaPrecoId) {
		Optional<TabelaPreco> tabelaPreco = tabelaPrecoRepository.findById(tabelaPrecoId);

		if (tabelaPreco.isPresent()) {
			return ResponseEntity.ok(tabelaPreco.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cliente/{clienteId}")
	public TipoCliente findByCliente(@PathVariable Long clienteId) {
		TipoCliente tipoCliente = clienteRepository.findById(clienteId).get().getTipoCliente();
		return tipoCliente;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TabelaPreco adicionar(@Valid @RequestBody TabelaPreco tabelaPreco) {
		return tabelaPrecoService.salvar(tabelaPreco);
	}

	@PutMapping("/{tabelaPrecoId}")
	public ResponseEntity<TabelaPreco> atualizar(@Valid @PathVariable Long tabelaPrecoId, @RequestBody TabelaPreco tabelaPreco){
		
		if(!tabelaPrecoRepository.existsById(tabelaPrecoId)) {
			return ResponseEntity.notFound().build();
		}		
		tabelaPreco.setId(tabelaPrecoId);
		tabelaPreco = tabelaPrecoService.atualizar(tabelaPreco);
		return ResponseEntity.ok(tabelaPreco);		
	}
	
	@DeleteMapping("/{tabelaPrecoId}")
	public ResponseEntity<Void> remover(@PathVariable Long tabelaPrecoId){		
		if(!tabelaPrecoRepository.existsById(tabelaPrecoId)) {
			return ResponseEntity.notFound().build();		}
		
		tabelaPrecoService.excluir(tabelaPrecoId);		
		return ResponseEntity.noContent().build();
	}

}
