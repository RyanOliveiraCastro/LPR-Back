package edu.ifes.ci.si.les.lpr.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
import edu.ifes.ci.si.les.lpr.model.Comissao;
import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.model.ItemOs;
import edu.ifes.ci.si.les.lpr.model.ItemTprecoTservico;
import edu.ifes.ci.si.les.lpr.model.OrdemServico;
import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.model.TabelaPreco;
import edu.ifes.ci.si.les.lpr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.OrdemServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TabelaPrecoRepository;
import edu.ifes.ci.si.les.lpr.repositories.ComissaoRepository;
import edu.ifes.ci.si.les.lpr.services.OrdemServicoService;

@RestController
@RequestMapping("/ordemServico")
public class OrdemServicoController {
	

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TabelaPrecoRepository tabelaPrecoRepository;
	@Autowired
	private OrdemServicoService ordemServicoService;

		
	@GetMapping
	public List<OrdemServico> listar() {

		return ordemServicoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemServicoId);

		if (ordemServico.isPresent()) {
			return ResponseEntity.ok(ordemServico.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscarPorProtetico/{proteticoId}")
	public List<OrdemServico> buscarPorProtetico(@PathVariable Long proteticoId) {
		Protetico protetico = new Protetico();
		protetico.setId(proteticoId);
		return ordemServicoRepository.findByProtetico(protetico, Sort.by(Sort.Direction.ASC, "id"));
		
	}	
	
	@GetMapping("/buscarPorFuncionario/{funcionarioId}")
	public List<OrdemServico> buscarPorFuncionario(@PathVariable Long funcionarioId) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(funcionarioId);
		return ordemServicoRepository.findByFuncionario(funcionario, Sort.by(Sort.Direction.ASC, "id"));
		
	}
	
	@GetMapping("/buscarPorCliente/{clienteId}")
	public List<OrdemServico> buscarPorCliente(@PathVariable Long clienteId) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteId);
		return ordemServicoRepository.findByCliente(cliente, Sort.by(Sort.Direction.ASC, "id"));
		
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrdemServico> adicionar(@Valid @RequestBody OrdemServico ordemServico) {
		
		ordemServico = ordemServicoService.salvar(ordemServico);
		
		return ResponseEntity.ok(ordemServico);

	}

	@PutMapping("/{ordemServicoId}")
	public ResponseEntity<Serializable> atualizar(@Valid @PathVariable Long ordemServicoId, @RequestBody OrdemServico ordemServico) {

		if (!ordemServicoRepository.existsById(ordemServicoId)) {
			return ResponseEntity.notFound().build();
		}
		ordemServico.setId(ordemServicoId);		
		
		
		
		ordemServico = ordemServicoService.atualizar(ordemServico);
		return ResponseEntity.ok(ordemServico);
	}

	@DeleteMapping("/{ordemServicoId}")
	public ResponseEntity<Void> remover(@PathVariable Long ordemServicoId) {
		if (!ordemServicoRepository.existsById(ordemServicoId)) {
			return ResponseEntity.notFound().build();
		}

		ordemServicoService.excluir(ordemServicoId);
		return ResponseEntity.noContent().build();
	}

}
