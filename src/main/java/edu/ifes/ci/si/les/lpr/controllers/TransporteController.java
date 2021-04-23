package edu.ifes.ci.si.les.lpr.controllers;

import java.util.List;

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

import edu.ifes.ci.si.les.lpr.model.Transporte;
import edu.ifes.ci.si.les.lpr.repositories.TransporteRepository;
import edu.ifes.ci.si.les.lpr.services.TransporteService;

@RestController
@RequestMapping("/transporte")
public class TransporteController {

	@Autowired
	private TransporteRepository transporteRepository;

	@Autowired
	private TransporteService transporteService;

	@GetMapping
	public List<Transporte> listar() {

		return transporteRepository.findAll();
	}

	@GetMapping("/{ordemServicoId}/{servicoId}")
	public ResponseEntity<Transporte> buscar(@PathVariable Long ordemServicoId, @PathVariable Long servicoId) {
		Transporte transporte = transporteService.findById(ordemServicoId, servicoId);
		
			return ResponseEntity.ok().body(transporte);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transporte adicionar(@Valid @RequestBody Transporte transporte) {

		return transporteService.salvar(transporte);

	}

	@PutMapping("/{ordemServicoId}/{servicoId}")
	public ResponseEntity<Transporte> atualizar(@Valid @PathVariable Long ordemServicoId, @PathVariable Long servicoId) {
		Transporte transporte = new Transporte();
		transporte = transporteService.findById(ordemServicoId, servicoId);
		if (!transporteRepository.existsById(transporte.getId())) {
			return ResponseEntity.notFound().build();
		}
		
		transporte = transporteService.salvar(transporte);
		return ResponseEntity.ok(transporte);
	}

	@DeleteMapping("/{ordemServicoId}/{servicoId}")
	public ResponseEntity<Void> remover(@Valid @PathVariable Long ordemServicoId, @PathVariable Long servicoId, @RequestBody Transporte transporte) {
		if (!transporteRepository.existsById(transporte.getId())) {
			return ResponseEntity.notFound().build();
		}

		transporteService.excluir(transporte.getId());
		return ResponseEntity.noContent().build();
	}
}
