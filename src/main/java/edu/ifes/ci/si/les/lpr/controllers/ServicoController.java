package edu.ifes.ci.si.les.lpr.controllers;

import java.util.Arrays;
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

import edu.ifes.ci.si.les.lpr.model.Servico;
import edu.ifes.ci.si.les.lpr.model.enums.AtualizacaoEtapa;
import edu.ifes.ci.si.les.lpr.model.enums.FormaPagamento;
import edu.ifes.ci.si.les.lpr.model.enums.TipoTransporte;
import edu.ifes.ci.si.les.lpr.repositories.ServicoRepository;
import edu.ifes.ci.si.les.lpr.services.ServicoService;

@RestController
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ServicoService servicoService;

	@GetMapping
	public List<Servico> listar() {

		return servicoRepository.findAll();
	}
	
	@GetMapping("/status")
	public List<AtualizacaoEtapa> listarStatus() {
		return Arrays.asList(AtualizacaoEtapa.values());
	}
	
	@GetMapping("/pagamento")
	public List<FormaPagamento> listarPagamento() {
		return Arrays.asList(FormaPagamento.values());
	}
	
	@GetMapping("/transporte")
	public List<TipoTransporte> listarTransporte() {
		return Arrays.asList(TipoTransporte.values());
	}

	@GetMapping("/{servicoId}")
	public ResponseEntity<Servico> buscar(@PathVariable Long servicoId) {
		Optional<Servico> servico = servicoRepository.findById(servicoId);

		if (servico.isPresent()) {
			return ResponseEntity.ok(servico.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico adicionar(@Valid @RequestBody Servico servico) {

		return servicoService.salvar(servico);

	}

	@PutMapping("/{servicoId}")
	public ResponseEntity<Servico> atualizar(@Valid @PathVariable Long servicoId, @RequestBody Servico servico) {

		if (!servicoRepository.existsById(servicoId)) {
			return ResponseEntity.notFound().build();
		}
		servico.setId(servicoId);
		servico = servicoService.salvar(servico);
		return ResponseEntity.ok(servico);
	}

	@DeleteMapping("/{servicoId}")
	public ResponseEntity<Void> remover(@PathVariable Long servicoId) {
		if (!servicoRepository.existsById(servicoId)) {
			return ResponseEntity.notFound().build();
		}

		servicoService.excluir(servicoId);
		return ResponseEntity.noContent().build();
	}
}
