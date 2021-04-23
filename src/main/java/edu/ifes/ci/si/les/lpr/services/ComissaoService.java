package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Comissao;
import edu.ifes.ci.si.les.lpr.repositories.ComissaoRepository;

@Service
public class ComissaoService {

	@Autowired
	private ComissaoRepository comissaoRepository;
		
	public Comissao salvar(Comissao comissao) {			

		return comissaoRepository.save(comissao);
	}
	
	public void excluir(Long comissaoId) {
		comissaoRepository.deleteById(comissaoId);
	}	
	
}
