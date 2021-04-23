package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
		
	public Funcionario salvar(Funcionario funcionario) {			

		return funcionarioRepository.save(funcionario);
	}
	
	public void excluir(Long funcionarioId) {
		funcionarioRepository.deleteById(funcionarioId);
	}	
	
}
