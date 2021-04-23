package edu.ifes.ci.si.les.lpr.controllers;

import java.math.BigInteger;
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

import edu.ifes.ci.si.les.lpr.model.Cliente;
import edu.ifes.ci.si.les.lpr.model.Telefone;
import edu.ifes.ci.si.les.lpr.model.TipoCliente;
import edu.ifes.ci.si.les.lpr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.lpr.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {

		return clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	@GetMapping("/debitoCliente/{clienteId}")
	public Double findDebitoCliente(@PathVariable Long clienteId) {
		return clienteRepository.findDebitoCliente(clienteId);
		
	}
	

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		
		if(cliente.getTelefone().getId() == null) {
			Telefone telefone = telefoneRepository.save(cliente.getTelefone());
			cliente.setTelefone(telefone);
		}
		

		return clienteService.salvar(cliente);

	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setTipoCliente(new TipoCliente(1L, null, null));
		
		cliente.setId(clienteId);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		clienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
}
