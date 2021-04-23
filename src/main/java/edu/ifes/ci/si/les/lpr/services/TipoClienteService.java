package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.TipoCliente;
import edu.ifes.ci.si.les.lpr.repositories.TipoClienteRepository;

@Service
public class TipoClienteService {

	@Autowired
	private TipoClienteRepository tipoClienteRepository;


	public TipoCliente salvar(TipoCliente tipoCliente) {			
		

		return tipoClienteRepository.save(tipoCliente);
	}

	public void excluir(Long tipoClienteId) {
		tipoClienteRepository.deleteById(tipoClienteId);
	}

}
