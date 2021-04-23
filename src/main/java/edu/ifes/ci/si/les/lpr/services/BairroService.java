package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Bairro;
import edu.ifes.ci.si.les.lpr.repositories.BairroRepository;

@Service
public class BairroService {

	@Autowired
	private BairroRepository bairroRepository;
		
	public Bairro salvar(Bairro bairro) {			

		return bairroRepository.save(bairro);
	}
	
	public void excluir(Long bairroId) {
		bairroRepository.deleteById(bairroId);
	}	
	
}
