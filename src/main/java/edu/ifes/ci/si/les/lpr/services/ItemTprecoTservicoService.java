package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.ItemTprecoTservico;
import edu.ifes.ci.si.les.lpr.model.ItemTprecoTservicoPK;
import edu.ifes.ci.si.les.lpr.repositories.ItemTprecoTservicoRepository;

@Service
public class ItemTprecoTservicoService {

	@Autowired
	private ItemTprecoTservicoRepository itemTprecoTservicoRepository;
		
	public ItemTprecoTservico salvar(ItemTprecoTservico itemTprecoTservico) {			

		return itemTprecoTservicoRepository.save(itemTprecoTservico);
	}
	
	public void excluir(ItemTprecoTservicoPK itemTprecoTservicoPK) {
		itemTprecoTservicoRepository.deleteById(itemTprecoTservicoPK);
	}	
	
	
	
}
