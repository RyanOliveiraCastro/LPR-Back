package edu.ifes.ci.si.les.lpr.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.lpr.model.Pagamento;
import edu.ifes.ci.si.les.lpr.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.lpr.repositories.RelatorioRepository;
import edu.ifes.ci.si.les.lpr.services.PagamentoService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	RelatorioRepository relatorioRepositotory;
	
	@GetMapping("/clienteComDebito")
	public List clienteComDebito() {
		return relatorioRepositotory.clienteComDebito();
	}
	@GetMapping("/OSPeriodoCliente/{dt1}/{dt2}/{clienteId}")
	public List OSPeriodoCliente(@PathVariable String dt1, @PathVariable String dt2, @PathVariable Long clienteId) {
		
			return relatorioRepositotory.OSPeriodoCliente(dt1, dt2, clienteId);	
	}
	
	@GetMapping("/OsComDebito")
	public List OsComDebito() {		
			return relatorioRepositotory.OsComDebito();	
	}
	
	@GetMapping("/ServicoPorCliente/{tipoServicoId}")
	public List ServicoPorCliente(@PathVariable Long tipoServicoId) {		
			return relatorioRepositotory.ServicoPorCliente(tipoServicoId);	
	}
	
	@GetMapping("/comissaoPorFuncionario")
	public List comissaoPorFuncionario() {		
			return relatorioRepositotory.comissaoPorFuncionario();	
	}
	
	@GetMapping("/transportePorCliente/{clienteId}")
	public List transportePorCliente(@PathVariable Long clienteId) {		
			return relatorioRepositotory.transportePorCliente(clienteId);	
	}
	
	@GetMapping("/servicoRealizado")
	public List servicoRealizado() {		
			return relatorioRepositotory.servicoRealizado();	
	}
	
	@GetMapping("/metodoPagamento")
	public List metodoPagamento() {		
			return relatorioRepositotory.metodosPagamento();	
	}
	
	
	
}
