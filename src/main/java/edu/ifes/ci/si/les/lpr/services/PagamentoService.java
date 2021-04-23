package edu.ifes.ci.si.les.lpr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.ItemOs;
import edu.ifes.ci.si.les.lpr.model.OrdemServico;
import edu.ifes.ci.si.les.lpr.model.Pagamento;
import edu.ifes.ci.si.les.lpr.model.PagamentoPK;
import edu.ifes.ci.si.les.lpr.model.Servico;
import edu.ifes.ci.si.les.lpr.repositories.ItemOsRepository;
import edu.ifes.ci.si.les.lpr.repositories.OrdemServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.lpr.repositories.ServicoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Autowired
	private ItemOsRepository itemOsRepository;

	public Pagamento findById(Long ordemServicoId, Long servicoId) {
		OrdemServico ordemServico = new OrdemServico();
		Servico servico = new Servico();
		ItemOs itemOs = new ItemOs();
		PagamentoPK id = new PagamentoPK();

		ordemServico = ordemServicoRepository.findById(ordemServicoId).get();
		servico = servicoRepository.findById(servicoId).get();
		itemOs.getId().setOrdemServico(ordemServico);
		itemOs.getId().setServico(servico);
		itemOs = itemOsRepository.findById(itemOs.getId()).get();
		id.setItemOs(itemOs);

		Pagamento pagamento = pagamentoRepository.findById(id).get();
		return pagamento;
	}

	public Pagamento salvar(Pagamento pagamento) {

		return pagamentoRepository.save(pagamento);
	}

	public void excluir(PagamentoPK pagamentoId) {
		pagamentoRepository.deleteById(pagamentoId);
	}

}
