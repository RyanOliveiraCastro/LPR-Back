package edu.ifes.ci.si.les.lpr.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.lpr.model.Cliente;
import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.lpr.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	FuncionarioRepository funcionarioRepository;

	@Autowired
	private LoginService loginService;

	@PostMapping("/logarCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente logarCliente(@Valid @RequestBody Cliente cliente) {

		return loginService.logarCliente(cliente);
	}

	@PostMapping("/logarFuncionario")
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario logarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return loginService.logarFuncionario(funcionario);
	}

	@PostMapping("/logarProtetico")
	@ResponseStatus(HttpStatus.CREATED)
	public Protetico logarProtetico(@Valid @RequestBody Protetico protetico) {
		return loginService.logarProtetico(protetico);
	}

}
