package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.TipoServico;
import edu.ifes.ci.si.les.lpr.repositories.TipoServicoRepository;

@Service
public class TipoServicoService {

	@Autowired
	private TipoServicoRepository tipoServicoRepository;
		
	public TipoServico salvar(TipoServico tipoServico) {			

		return tipoServicoRepository.save(tipoServico);
	}
	
	public void excluir(Long tipoServicoId) {
		tipoServicoRepository.deleteById(tipoServicoId);
	}	
	
}
