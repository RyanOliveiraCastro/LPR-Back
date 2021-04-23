package edu.ifes.ci.si.les.lpr.controllers;

import java.util.ConcurrentModificationException;
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

import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.model.Telefone;
import edu.ifes.ci.si.les.lpr.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.lpr.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.lpr.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	@GetMapping
	public List<Funcionario> listar() {

		return funcionarioRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}
	
	
	@GetMapping("/countOrdemServico/{funcionarioId}")
	public Integer countOrdemServico(@PathVariable Long funcionarioId) {

		return funcionarioRepository.countOrdemServico(funcionarioId);
	}
	
	@GetMapping("/funcionariosAtivos")
	public List<Funcionario> listarFuncionariosAtivos() {

		List<Funcionario> listFuncionario = funcionarioRepository.findAll();
		try {
			for (Funcionario funcionario : listFuncionario) {
				if(funcionarioRepository.countOrdemServico(funcionario.getId()) >= 9) {
					listFuncionario.remove(funcionario);
				}
			}	
			return listFuncionario;
		} catch (ConcurrentModificationException e) {
			return listFuncionario;
		}		
	}	

	@GetMapping("/{funcionarioId}")
	public ResponseEntity<Funcionario> buscar(@PathVariable Long funcionarioId) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioId);

		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario adicionar(@Valid @RequestBody Funcionario funcionario) {

		if(funcionario.getTelefone().getId() == null) {
			Telefone telefone = telefoneRepository.save(funcionario.getTelefone());
			funcionario.setTelefone(telefone);
		}
		
		return funcionarioService.salvar(funcionario);

	}

	@PutMapping("/{funcionarioId}")
	public ResponseEntity<Funcionario> atualizar(@Valid @PathVariable Long funcionarioId, @RequestBody Funcionario funcionario) {

		if (!funcionarioRepository.existsById(funcionarioId)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(funcionarioId);
		funcionario = funcionarioService.salvar(funcionario);
		return ResponseEntity.ok(funcionario);
	}

	@DeleteMapping("/{funcionarioId}")
	public ResponseEntity<Void> remover(@PathVariable Long funcionarioId) {
		if (!funcionarioRepository.existsById(funcionarioId)) {
			return ResponseEntity.notFound().build();
		}

		funcionarioService.excluir(funcionarioId);
		return ResponseEntity.noContent().build();
	}
}
