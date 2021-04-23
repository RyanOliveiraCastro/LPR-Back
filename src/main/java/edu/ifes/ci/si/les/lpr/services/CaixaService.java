package edu.ifes.ci.si.les.lpr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Caixa;
import edu.ifes.ci.si.les.lpr.repositories.CaixaRepository;

@Service
public class CaixaService {

	@Autowired
	private CaixaRepository caixaRepository;
		
	public Caixa salvar(Caixa caixa) {			

		return caixaRepository.save(caixa);
	}
	
	public void excluir(Long caixaRepositoryId) {
		caixaRepository.deleteById(caixaRepositoryId);
	}	
	
	
}
