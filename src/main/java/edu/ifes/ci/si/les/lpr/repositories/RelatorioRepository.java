package edu.ifes.ci.si.les.lpr.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Funcionario;

@Repository
public interface RelatorioRepository extends JpaRepository<Funcionario, Long>{

	@Transactional
    @Query(value =  "SELECT  cliente.id id," + 
		    		"		cliente.nome cliente," + 
		    		"		SUM(ordem_servico.valor) valor" + 
		    		"	FROM ordem_servico," + 
		    		"		 cliente," + 
		    		"		 pagamento" + 
		    		"	WHERE ordem_servico.cliente_id = cliente.id" + 
		    		"	  AND ordem_servico.id = pagamento.ordem_servico_id" + 
		    		"	  AND pagamento.pago = false" +
		    		"	GROUP BY cliente.id, cliente.nome" + 
		    		"	having SUM(ordem_servico.valor) > 500" + 
		    		" ORDER BY valor desc", nativeQuery = true)
	public List clienteComDebito();
		
	@Transactional
	@Query(value=   "SELECT	os.id," + 
					"		cliente.nome CLIENTE," + 
					"		os.recebido DT_RECEBIDO," + 
					"		os.entrega DT_ENTREGA," + 
					"		os.valor VALOR," + 
					"		os.atualizacao_etapa ETAPA," + 
					"		tipo_servico.nome SERVICO" + 
					" FROM ordem_servico os, cliente, item_os, servico, tipo_servico" + 
					" WHERE os.cliente_id = cliente.id" + 
					"  AND os.id = item_os.ordem_servico_id" + 
					"  AND item_os.servico_id = servico.id" + 
					"  AND servico.tipo_servico_id = tipo_servico.id" + 
					"  AND os.entrega BETWEEN TO_TIMESTAMP(?1,'YYYY-MM-DD') AND TO_TIMESTAMP(?2,'YYYY-MM-DD')" + 
					"  AND cliente.id = ?3" +
					" ORDER BY os.id ASC", nativeQuery=true)
	public List OSPeriodoCliente(String dt1, String dt2, Long clienteId);
	
	@Transactional
	@Query(value=    "SELECT os.id id, " +
						 " cliente.nome cliente, " +
					 	 " os.recebido dt_recebido, " +
						 " pagamento.pago pago," + 
					 	 "os.valor valor" +
					 " FROM cliente, ordem_servico os , item_os, pagamento" + 
					 " WHERE cliente.id = os.cliente_id" + 
					 "  and os.id = item_os.ordem_servico_id" + 
					 "  and item_os.ordem_servico_id = pagamento.ordem_servico_id" + 
					 "  and pago = FALSE" +
					 " ORDER BY os.recebido ASC", nativeQuery=true)
	public List OsComDebito();

	@Transactional
	@Query(value = "SELECT count(tipo_servico.nome) Quantidade," + 
			"		tipo_servico.nome tipo_servico," + 
			"		cliente.nome cliente" + 
			" FROM SERVICO, TIPO_SERVICO, item_os, ordem_servico os, cliente" + 
			" WHERE servico.tipo_servico_id = tipo_servico.id" + 
			"  AND item_os.servico_id = servico.id" + 
			"  AND os.id = item_os.ordem_servico_id" + 
			"  AND os.cliente_id = cliente.id" + 
			" AND tipo_servico.id = ?1" +
			" GROUP BY tipo_servico.nome, cliente.nome" +
			" ORDER BY count(*) DESC", nativeQuery = true)
	public List ServicoPorCliente(Long tipoServico);

	@Transactional
	@Query(value="SELECT funcionario.id, " +  
				 "		 funcionario.nome Funcionario, " + 
				 "		 COUNT(comissao.funcionario_id) Quantidade, " + 
				 "		 SUM(comissao.valor) Valor" + 
				 " FROM comissao , " + 
				 "	 funcionario " + 
				 "	 WHERE funcionario.id = comissao.funcionario_id " + 
				 " GROUP BY funcionario.nome, funcionario.id" +
				 " ORDER BY SUM(comissao.valor) DESC", nativeQuery = true)
	public List comissaoPorFuncionario();
	
	@Transactional
	@Query(value="SELECT  cliente.id id, " +
						 "cliente.nome Cliente, " +
						 "transporte.forma_transporte Nome, " +
						 "count(transporte.forma_transporte) QUANTIDADE" + 
						 " FROM cliente," + 
						 "	 ordem_servico os," + 
						 "	 item_os, " + 
						 "	 transporte" + 
						 " WHERE cliente.id = os.cliente_id" + 
						 "  and os.id = item_os.ordem_servico_id" + 
						 "  and item_os.ordem_servico_id = transporte.ordem_servico_id" + 
						 "  and cliente.id = ?1" + 
						 "  group by cliente.id, cliente.nome, transporte.forma_transporte" +
						 " ORDER BY count(*) DESC", nativeQuery = true)
	public List transportePorCliente(Long clienteId);
	
	@Transactional
	@Query(value="SELECT count(tipo_servico.nome) Quantidade, " +
						"tipo_servico.nome tipo_Servico" + 
						" FROM SERVICO, TIPO_SERVICO, item_os, ordem_servico os" + 
						" WHERE servico.tipo_servico_id = tipo_servico.id" + 
						"  AND item_os.servico_id = servico.id" + 
						"  AND os.id = item_os.ordem_servico_id" + 
						"  GROUP BY tipo_servico.nome" +
						" ORDER BY count(*) DESC", nativeQuery = true)
	public List servicoRealizado();
	
	@Transactional
	@Query(value="SELECT pagamento.forma_pagamento, " +
						"count(pagamento.forma_pagamento) QUANTIDADE" + 
					" FROM ordem_servico os," + 
					"	 item_os, " + 
					"	 pagamento" + 
					" WHERE os.id = item_os.ordem_servico_id" + 
					"  and item_os.ordem_servico_id = pagamento.ordem_servico_id" + 
					"  group by pagamento.forma_pagamento" +
					" ORDER BY count(*) DESC", nativeQuery = true)
	public List metodosPagamento();
	
}
