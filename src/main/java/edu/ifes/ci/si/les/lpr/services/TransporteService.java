package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.ItemOs;
import edu.ifes.ci.si.les.lpr.model.OrdemServico;
import edu.ifes.ci.si.les.lpr.model.Transporte;
import edu.ifes.ci.si.les.lpr.model.TransportePK;
import edu.ifes.ci.si.les.lpr.model.Servico;
import edu.ifes.ci.si.les.lpr.repositories.ItemOsRepository;
import edu.ifes.ci.si.les.lpr.repositories.OrdemServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TransporteRepository;
import edu.ifes.ci.si.les.lpr.repositories.ServicoRepository;

@Service
public class TransporteService {

	@Autowired
	private TransporteRepository transporteRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ItemOsRepository itemOsRepository;
		
		
	public Transporte findById(Long ordemServicoId, Long servicoId) {
		OrdemServico ordemServico = new OrdemServico();
		Servico servico = new Servico();
		ItemOs itemOs = new ItemOs();
		TransportePK id = new TransportePK();
		
		ordemServico = ordemServicoRepository.findById(ordemServicoId).get();
		servico = servicoRepository.findById(servicoId).get();
		itemOs.getId().setOrdemServico(ordemServico);
		itemOs.getId().setServico(servico);
		itemOs = itemOsRepository.findById(itemOs.getId()).get();
		id.setItemOs(itemOs);
				
		
		Transporte transporte = transporteRepository.findById(id).get();
		return transporte;
	}
	
	
	public Transporte salvar(Transporte transporte) {			

		return transporteRepository.save(transporte);
	}
	
	public void excluir(TransportePK transporteId) {
		transporteRepository.deleteById(transporteId);
	}	
	
}
