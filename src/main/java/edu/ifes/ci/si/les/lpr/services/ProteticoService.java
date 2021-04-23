package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.repositories.ProteticoRepository;

@Service
public class ProteticoService {

	@Autowired
	private ProteticoRepository proteticoRepository;
		
	public Protetico salvar(Protetico protetico) {			

		return proteticoRepository.save(protetico);
	}
	
	public void excluir(Long proteticoId) {
		proteticoRepository.deleteById(proteticoId);
	}	
	
}
