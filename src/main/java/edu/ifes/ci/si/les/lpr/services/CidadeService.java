package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Cidade;
import edu.ifes.ci.si.les.lpr.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
		
	public Cidade salvar(Cidade cidade) {			

		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		cidadeRepository.deleteById(cidadeId);
	}	
	
}
