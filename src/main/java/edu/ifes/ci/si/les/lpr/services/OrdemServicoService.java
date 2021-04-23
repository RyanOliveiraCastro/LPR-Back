package edu.ifes.ci.si.les.lpr.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.lpr.model.Cliente;
import edu.ifes.ci.si.les.lpr.model.Comissao;
import edu.ifes.ci.si.les.lpr.model.ItemOs;
import edu.ifes.ci.si.les.lpr.model.ItemTprecoTservico;
import edu.ifes.ci.si.les.lpr.model.OrdemServico;
import edu.ifes.ci.si.les.lpr.model.TabelaPreco;
import edu.ifes.ci.si.les.lpr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.ComissaoRepository;
import edu.ifes.ci.si.les.lpr.repositories.ItemOsRepository;
import edu.ifes.ci.si.les.lpr.repositories.OrdemServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.lpr.repositories.ServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TabelaPrecoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TipoServicoRepository;
import edu.ifes.ci.si.les.lpr.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.lpr.services.exceptions.DataIntegrityException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private TipoServicoRepository tipoServicoRepository;

	@Autowired
	private ComissaoRepository comissaoRepository;

	@Autowired
	private ItemOsRepository itemOsRepository;
	
	@Autowired
	private TabelaPrecoRepository tabelaPrecoRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public OrdemServico salvar(OrdemServico ordemServico) {
		try {
			if (verificarRegrasDeNegocio(ordemServico)) {
				ordemServico.setId(null);
				
				if (clienteRepository.findDebitoCliente(ordemServico.getCliente().getId()) != null
						&& clienteRepository.findDebitoCliente(ordemServico.getCliente().getId()) > 500.0) {
					throw new BusinessRuleException("Este Cliente possui debito maior que 500 Reais!");
				}
				
				ArrayList<ItemOs> list = (ArrayList<ItemOs>) ordemServico.getItens();		
				Optional<Cliente> cliente = clienteRepository.findById(ordemServico.getCliente().getId());		
				Optional<TabelaPreco> tabelaPreco = tabelaPrecoRepository.findById(cliente.get().getTipoCliente().getTabelaPreco().getId());
								
				for(Iterator<ItemTprecoTservico> it = tabelaPreco.get().getItens().iterator(); it.hasNext();) {					
					ItemTprecoTservico item = it.next();					
					if(item.getId().getTipoServico().getId() == list.get(0).getId().getServico().getTipoServico().getId()) {			
						ordemServico.setValor(item.getValor());
					}
				}
				
				
				for (ItemOs item : ordemServico.getItens()) {
					item.setOrdemServico(ordemServico);
					item.getServico()
							.setTipoServico(tipoServicoRepository.findById(
									ordemServico.getItens().iterator().next().getServico().getTipoServico().getId())
									.get());
					item.setValor(ordemServico.getValor());
					servicoRepository.save(item.getServico());
				}
				
				return ordemServicoRepository.save(ordemServico);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) de Ordem de Serviço não foi(foram) preenchido(s): Cliente ou Itens");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public OrdemServico atualizar(OrdemServico ordemServico) {
		try {
			if (verificarRegrasDeNegocio(ordemServico)) {
				ordemServico.setId(ordemServicoRepository.findById(ordemServico.getId()).get().getId());
				
				ArrayList<ItemOs> list = (ArrayList<ItemOs>) ordemServico.getItens();		
				Optional<Cliente> cliente = clienteRepository.findById(ordemServico.getCliente().getId());		
				Optional<TabelaPreco> tabelaPreco = tabelaPrecoRepository.findById(cliente.get().getTipoCliente().getTabelaPreco().getId());
								
				for(Iterator<ItemTprecoTservico> it = tabelaPreco.get().getItens().iterator(); it.hasNext();) {					
					ItemTprecoTservico item = it.next();					
					if(item.getId().getTipoServico().getId() == list.get(0).getId().getServico().getTipoServico().getId()) {			
						ordemServico.setValor(item.getValor());
					}
				}				
				
				for (ItemOs item : ordemServico.getItens()) {
					item.setOrdemServico(ordemServico);
					item.getServico()
							.setTipoServico(tipoServicoRepository.findById(
									ordemServico.getItens().iterator().next().getServico().getTipoServico().getId())
									.get());
					item.setValor(ordemServico.getValor());
					servicoRepository.save(item.getServico());
				}
				return ordemServicoRepository.save(ordemServico);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do Empréstimo não foi(foram) preenchido(s): Cliente ou Item de Empréstimo");
		}
		return null;
	}

	public boolean verificarRegrasDeNegocio(OrdemServico ordemServico) {



		if ((ordemServico.getAtualizacaoEtapa().getDescricao().equals("ENVIADO")
				|| ordemServico.getAtualizacaoEtapa().getDescricao().equals("ENTREGUE"))
				&& pagamentoRepository.findByOS(ordemServico.getId()) != null
				&& pagamentoRepository.findByOS(ordemServico.getId()) == false) {

			throw new BusinessRuleException("Ordem de serviço em aberto! ");
		}
		;

		if (ordemServico.getAtualizacaoEtapa().getDescricao().equals("FINALIZADA")) {
			try {
				if(!(ordemServicoRepository.findById(ordemServico.getId()).get().getAtualizacaoEtapa().getDescricao()).equals("FINALIZADA")) {				
				comissaoRepository.save(new Comissao(null, tipoServicoRepository
						.findById(ordemServico.getItens().iterator().next().getServico().getTipoServico().getId()).get()
						.getComissao(), ordemServico.getItens().iterator().next().getServico(),
						ordemServico.getFuncionario()));
				}
			} catch (Exception e) {
				throw new DataIntegrityException("Erro ao inserir Comissão");
			}
		}

		return true;

	}

	public void excluir(Long ordemServicoId) {
		ordemServicoRepository.deleteById(ordemServicoId);
	}

}
