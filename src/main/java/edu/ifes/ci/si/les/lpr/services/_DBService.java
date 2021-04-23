package edu.ifes.ci.si.les.lpr.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.lpr.model.Bairro;
import edu.ifes.ci.si.les.lpr.model.Caixa;
import edu.ifes.ci.si.les.lpr.model.Cidade;
import edu.ifes.ci.si.les.lpr.model.Cliente;
import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.model.ItemOs;
import edu.ifes.ci.si.les.lpr.model.ItemTprecoTservico;
import edu.ifes.ci.si.les.lpr.model.OrdemServico;
import edu.ifes.ci.si.les.lpr.model.Pagamento;
import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.model.Servico;
import edu.ifes.ci.si.les.lpr.model.TabelaPreco;
import edu.ifes.ci.si.les.lpr.model.Telefone;
import edu.ifes.ci.si.les.lpr.model.TipoCliente;
import edu.ifes.ci.si.les.lpr.model.TipoServico;
import edu.ifes.ci.si.les.lpr.model.Transporte;
import edu.ifes.ci.si.les.lpr.model.Uf;
import edu.ifes.ci.si.les.lpr.model.enums.AtualizacaoEtapa;
import edu.ifes.ci.si.les.lpr.model.enums.FormaPagamento;
import edu.ifes.ci.si.les.lpr.model.enums.TipoTransporte;
import edu.ifes.ci.si.les.lpr.repositories.BairroRepository;
import edu.ifes.ci.si.les.lpr.repositories.CaixaRepository;
import edu.ifes.ci.si.les.lpr.repositories.CidadeRepository;
import edu.ifes.ci.si.les.lpr.repositories.ClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.ComissaoRepository;
import edu.ifes.ci.si.les.lpr.repositories.FuncionarioRepository;
import edu.ifes.ci.si.les.lpr.repositories.ItemOsRepository;
import edu.ifes.ci.si.les.lpr.repositories.ItemTprecoTservicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.OrdemServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.PagamentoRepository;
import edu.ifes.ci.si.les.lpr.repositories.ProteticoRepository;
import edu.ifes.ci.si.les.lpr.repositories.ServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TabelaPrecoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TelefoneRepository;
import edu.ifes.ci.si.les.lpr.repositories.TipoClienteRepository;
import edu.ifes.ci.si.les.lpr.repositories.TipoServicoRepository;
import edu.ifes.ci.si.les.lpr.repositories.TransporteRepository;
import edu.ifes.ci.si.les.lpr.repositories.UfRepository;

@Service
public class _DBService {

	@Autowired
	private UfRepository ufRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private CaixaRepository caixaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ComissaoRepository comissaoRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private ItemOsRepository itemOsRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProteticoRepository proteticoRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private TabelaPrecoRepository tabelaPrecoRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
	@Autowired
	private TipoClienteRepository tipoClienteRepository;
	@Autowired
	private TipoServicoRepository tipoServicoRepository;
	@Autowired
	private TransporteRepository transporteRepository;
	@Autowired
	private ItemTprecoTservicoRepository itemTprecoTservicoRepository;
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public void instantiateTestDatabase() throws ParseException, IOException {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Uf uf1 = new Uf(null, "ES", "Espírito Santo");
		Uf uf2 = new Uf(null, "MG", "Minas Gerais");

		Cidade cidade1 = new Cidade(null, "Alegre", uf1);
		Cidade cidade2 = new Cidade(null, "Cachoeiro de Itapemirim", uf1);
		Cidade cidade3 = new Cidade(null, "Belo Horizonte", uf2);
		Cidade cidade4 = new Cidade(null, "Lavras", uf2);

		Bairro bairro1 = new Bairro(null, "Vila do Sul", cidade1);
		Bairro bairro2 = new Bairro(null, "Guararema", cidade1);
		Bairro bairro3 = new Bairro(null, "Maria Ortiz", cidade2);
		Bairro bairro4 = new Bairro(null, "Centro", cidade2);
		Bairro bairro5 = new Bairro(null, "Barro Preto", cidade3);
		Bairro bairro6 = new Bairro(null, "Cidade Jardim", cidade3);
		Bairro bairro7 = new Bairro(null, "Vale do Sol", cidade4);
		Bairro bairro8 = new Bairro(null, "Nova Lavras", cidade4);

		Telefone telefone1 = new Telefone(null, "29 999720729");
		Telefone telefone2 = new Telefone(null, "28 999720728");
		Telefone telefone3 = new Telefone(null, "27 999720727");
		Telefone telefone4 = new Telefone(null, "26 999720726");

		TipoServico tipoServico1 = new TipoServico(null, "PPR", "Cromo Cobalto", 3.00);
		TipoServico tipoServico2 = new TipoServico(null, "Pino", "Prata", 3.00);
		TipoServico tipoServico3 = new TipoServico(null, "Coroa", "Goldent", 3.00);

		TabelaPreco tabelaPreco1 = new TabelaPreco(null, "Dentista");
		TabelaPreco tabelaPreco2 = new TabelaPreco(null, "Clinica");

		ItemTprecoTservico itemTprecoTservico = new ItemTprecoTservico(tabelaPreco1, tipoServico1, 5, 180.00);
		ItemTprecoTservico itemTprecoTservico1 = new ItemTprecoTservico(tabelaPreco1, tipoServico2, 2, 60.00);
		ItemTprecoTservico itemTprecoTservico2 = new ItemTprecoTservico(tabelaPreco1, tipoServico3, 2, 100.00);

		ItemTprecoTservico itemTprecoTservico3 = new ItemTprecoTservico(tabelaPreco2, tipoServico1, 5, 200.00);
		ItemTprecoTservico itemTprecoTservico4 = new ItemTprecoTservico(tabelaPreco2, tipoServico2, 2, 80.00);
		ItemTprecoTservico itemTprecoTservico5 = new ItemTprecoTservico(tabelaPreco2, tipoServico3, 2, 120.00);

		tabelaPreco1.setItens(Arrays.asList(itemTprecoTservico, itemTprecoTservico1, itemTprecoTservico2));
		tabelaPreco2.setItens(Arrays.asList(itemTprecoTservico3, itemTprecoTservico4, itemTprecoTservico5));

		TipoCliente tipoCliente = new TipoCliente(null, "Dentista", tabelaPreco1);

		Cliente cliente = new Cliente(null, "Joao a", "22222222222", "Rafael Dias", null, 30, bairro1, telefone2,
				"joaoA@hotmail.com", "123456", "12345-ES", tipoCliente);

		Protetico protetico = new Protetico(null, "Protetico_01", "33333333333", "Rua A", null, 32, bairro2, telefone1,
				"ProteticoA@hotmail.com", "123456", "12345-ES");

		Funcionario funcionario = new Funcionario(null, "Funcionario_01", "44444444444", "Rua B", "null", 34, bairro3,
				telefone3, "FuncionarioA@hotmail.com", "123456");

		Caixa caixa = new Caixa(null);
		Servico servico = new Servico(null, caixa, tipoServico1);

		OrdemServico ordemServico = new OrdemServico(null, sdf.parse("2020-06-18"), sdf.parse("2020-06-25"),
				"Paciente_01", "Peça Entregue", 200.00, cliente, funcionario, protetico, AtualizacaoEtapa.RECEBIDA);
		ItemOs itemOs = new ItemOs(ordemServico, servico, 200.00);

		ordemServico.setItens(Arrays.asList(itemOs));

		Pagamento pagamento = new Pagamento(itemOs, sdf.parse("2020-06-18"), FormaPagamento.BOLETO, true);

		Transporte transporte = new Transporte(itemOs, sdf.parse("2020-06-18"), TipoTransporte.MOTOBOY, 5.00);

		ufRepository.saveAll(Arrays.asList(uf1, uf2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3, cidade4));
		bairroRepository.saveAll(Arrays.asList(bairro1, bairro2, bairro3, bairro4, bairro5, bairro6, bairro7, bairro8));
		telefoneRepository.saveAll(Arrays.asList(telefone1, telefone2, telefone3, telefone4));
		tipoServicoRepository.saveAll(Arrays.asList(tipoServico1, tipoServico2, tipoServico3));
		tabelaPrecoRepository.saveAll(Arrays.asList(tabelaPreco1, tabelaPreco2));
		tipoClienteRepository.saveAll(Arrays.asList(tipoCliente));
		clienteRepository.saveAll(Arrays.asList(cliente));
		proteticoRepository.saveAll(Arrays.asList(protetico));
		funcionarioRepository.saveAll(Arrays.asList(funcionario));
		caixaRepository.saveAll(Arrays.asList(caixa));

		// Adicionar 10 Caixas
		for (int i = 0; i < 10; i++) {
			caixaRepository.saveAll(Arrays.asList(new Caixa(null)));
		}
		servicoRepository.saveAll(Arrays.asList(servico));
		ordemServicoRepository.saveAll(Arrays.asList(ordemServico));
		pagamentoRepository.saveAll(Arrays.asList(pagamento));
		transporteRepository.saveAll(Arrays.asList(transporte));
*/
	}

}
