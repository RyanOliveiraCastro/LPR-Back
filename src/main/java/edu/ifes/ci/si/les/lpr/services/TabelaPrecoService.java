package edu.ifes.ci.si.les.lpr.services;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.ItemTprecoTservico;
import edu.ifes.ci.si.les.lpr.model.TabelaPreco;
import edu.ifes.ci.si.les.lpr.repositories.ItemTprecoTservicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TabelaPrecoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TipoServicoRepository;

@Service
public class TabelaPrecoService {

	@Autowired
	private TabelaPrecoRepository tabelaPrecoRepository;
	
	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	
	@Autowired
	private ItemTprecoTservicoRepository itemTprecoTservicoRepository;
	
	@Autowired
	private ItemTprecoTservicoService itemTprecoTservicoService;
		
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public TabelaPreco salvar(TabelaPreco tabelaPreco) {
		
		tabelaPreco.setId(null);
		
		for(ItemTprecoTservico item : tabelaPreco.getItens()) {
			item.setTabelaPreco(tabelaPreco);
			item.setTipoServico(tipoServicoRepository.findById(item.getTipoServico().getId()).get());
			tipoServicoRepository.save(item.getTipoServico());
		}
		
		
		return tabelaPrecoRepository.save(tabelaPreco);
	}
	
	
	public TabelaPreco atualizar (TabelaPreco tabelaPreco) {
		tabelaPrecoRepository.findById(tabelaPreco.getId());
		
		for(ItemTprecoTservico item : tabelaPreco.getItens()) {
			item.setTabelaPreco(tabelaPreco);
			item.setTipoServico(tipoServicoRepository.findById(item.getTipoServico().getId()).get());
			tipoServicoRepository.save(item.getTipoServico());
		}
		
		return tabelaPrecoRepository.save(tabelaPreco);
	}
	 
	
	
	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	public TabelaPreco salvar2(TabelaPreco tabelaPreco) {
		TabelaPreco t2 = new TabelaPreco(tabelaPreco.getId(), tabelaPreco.getDescricao());
		if(t2.getId() == null) {
			t2 = tabelaPrecoRepository.save(t2);
			tabelaPreco.setId(t2.getId());
		}		
		
		for (ItemTprecoTservico item : tabelaPrecoRepository.findById(tabelaPreco.getId()).get().getItens()) {
			itemTprecoTservicoService.excluir(item.getId());
		}
		
		for (ItemTprecoTservico item : tabelaPreco.getItens()) {			
			item.setTabelaPreco(t2);			
			//itemTprecoTservicoRepository.save(item);
		}		
		//t2.setItens(tabelaPrecoRepository.findById(tabelaPreco.getId()).get().getItens());
		return tabelaPrecoRepository.save(tabelaPreco);
	}
	
	public void excluir(Long tabelaPrecoId) {
		tabelaPrecoRepository.deleteById(tabelaPrecoId);
	}	
	
}
