package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Uf;
import edu.ifes.ci.si.les.lpr.repositories.UfRepository;

@Service
public class UfService {

	@Autowired
	private UfRepository ufRepository;
		
	public Uf salvar(Uf uf) {			

		return ufRepository.save(uf);
	}
	
	public void excluir(Long ufId) {
		ufRepository.deleteById(ufId);
	}	
	
}
