package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonEncoding;

import edu.ifes.ci.si.les.lpr.model.Cliente;
import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.lpr.repositories.ProteticoRepository;

@Service
public class LoginService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private ProteticoRepository proteticoRepository;


	public Cliente logarCliente(Cliente cliente) {
		Cliente cliente1 = clienteRepository.findByEmail(cliente.getEmail());
		if (cliente1.getSenha().equals(cliente.getSenha())) {
			return cliente1;
		} else {
			return null;
		}
	}	
	
	public Protetico logarProtetico(Protetico protetico) {
		Protetico protetico1 = proteticoRepository.findByEmail(protetico.getEmail());
		if (protetico1.getSenha().equals(protetico.getSenha())) {
			return protetico1;
		} else {
			return null;
		}
	}

	public Funcionario logarFuncionario(Funcionario funcionario) {
		Funcionario funcionario1 = funcionarioRepository.findByEmail(funcionario.getEmail());
		if (funcionario1.getSenha().equals(funcionario.getSenha())) {
			return funcionario1;
		} else {
			return null;
		}
	}
}
