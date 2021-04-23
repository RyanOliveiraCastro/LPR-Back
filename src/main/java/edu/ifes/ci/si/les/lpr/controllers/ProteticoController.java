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

import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.model.Telefone;
import edu.ifes.ci.si.les.lpr.repositories.ProteticoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.lpr.services.ProteticoService;

@RestController
@RequestMapping("/protetico")
public class ProteticoController {

	@Autowired
	private ProteticoRepository proteticoRepository;

	@Autowired
	private ProteticoService proteticoService;
	
	@Autowired
	private TelefoneRepository telefoneRepository;

	@GetMapping
	public List<Protetico> listar() {
		
		return proteticoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
	}

	@GetMapping("/{proteticoId}")
	public ResponseEntity<Protetico> buscar(@PathVariable Long proteticoId) {
		Optional<Protetico> protetico = proteticoRepository.findById(proteticoId);

		if (protetico.isPresent()) {
			return ResponseEntity.ok(protetico.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Protetico adicionar(@Valid @RequestBody Protetico protetico) {

		if(protetico.getTelefone().getId() == null) {
			Telefone telefone = telefoneRepository.save(protetico.getTelefone());
			protetico.setTelefone(telefone);
		}
		
		return proteticoService.salvar(protetico);

	}

	@PutMapping("/{proteticoId}")
	public ResponseEntity<Protetico> atualizar(@Valid @PathVariable Long proteticoId, @RequestBody Protetico protetico) {

		if (!proteticoRepository.existsById(proteticoId)) {
			return ResponseEntity.notFound().build();
		}
		protetico.setId(proteticoId);
		protetico = proteticoService.salvar(protetico);
		return ResponseEntity.ok(protetico);
	}

	@DeleteMapping("/{proteticoId}")
	public ResponseEntity<Void> remover(@PathVariable Long proteticoId) {
		if (!proteticoRepository.existsById(proteticoId)) {
			return ResponseEntity.notFound().build();
		}

		proteticoService.excluir(proteticoId);
		return ResponseEntity.noContent().build();
	}
}
