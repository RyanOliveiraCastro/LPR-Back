package edu.ifes.ci.si.les.lpr.controllers;

import java.util.List;

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

import edu.ifes.ci.si.les.lpr.model.Pagamento;
import edu.ifes.ci.si.les.lpr.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.lpr.services.PagamentoService;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PagamentoService pagamentoService;

	@GetMapping
	public List<Pagamento> listar() {

		return pagamentoRepository.findAll();
	}

	@GetMapping("/{ordemServicoId}/{servicoId}")
	public ResponseEntity<Pagamento> buscar(@PathVariable Long ordemServicoId, @PathVariable Long servicoId) {
		Pagamento pagamento = pagamentoService.findById(ordemServicoId, servicoId);
		
			return ResponseEntity.ok().body(pagamento);
	}
	
	@GetMapping("/isPago/{ordemServicoId}")
	public Boolean isPago(@PathVariable Long ordemServicoId) {
		return pagamentoRepository.findByOS(ordemServicoId);	
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pagamento adicionar(@Valid @RequestBody Pagamento pagamento) {

		return pagamentoService.salvar(pagamento);

	}

	@PutMapping("/{ordemServicoId}/{servicoId}")
	public ResponseEntity<Pagamento> atualizar(@Valid @PathVariable Long ordemServicoId, @PathVariable Long servicoId, @RequestBody Pagamento pagamento) {
		//pagamento.getValor();
		//pagamento = pagamentoService.findById(ordemServicoId, servicoId);
		if (!pagamentoRepository.existsById(pagamento.getId())) {
			return ResponseEntity.notFound().build();
		}
		
		pagamento = pagamentoService.salvar(pagamento);
		return ResponseEntity.ok(pagamento);
	}

	@DeleteMapping("/{ordemServicoId}/{servicoId}")
	public ResponseEntity<Void> remover(@Valid @PathVariable Long ordemServicoId, @PathVariable Long servicoId) {
		Pagamento pagamento = new Pagamento();
		pagamento = pagamentoService.findById(ordemServicoId, servicoId);
		if (!pagamentoRepository.existsById(pagamento.getId())) {
			return ResponseEntity.notFound().build();
		}

		pagamentoService.excluir(pagamento.getId());
		return ResponseEntity.noContent().build();
	}
}
