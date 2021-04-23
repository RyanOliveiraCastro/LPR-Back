package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Telefone;
import edu.ifes.ci.si.les.lpr.repositories.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;
		
	public Telefone salvar(Telefone telefone) {			

		return telefoneRepository.save(telefone);
	}
	
	public void excluir(Long telefoneId) {
		telefoneRepository.deleteById(telefoneId);
	}	
	
}
