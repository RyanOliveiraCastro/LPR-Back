package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Servico;
import edu.ifes.ci.si.les.lpr.repositories.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
		
	public Servico salvar(Servico servico) {			

		return servicoRepository.save(servico);
	}
	
	public void excluir(Long servicoId) {
		servicoRepository.deleteById(servicoId);
	}	
	
}
