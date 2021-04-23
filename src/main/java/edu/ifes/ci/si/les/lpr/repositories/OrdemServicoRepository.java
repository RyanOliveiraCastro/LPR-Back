package edu.ifes.ci.si.les.lpr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
import edu.ifes.ci.si.les.lpr.model.Cliente;
import edu.ifes.ci.si.les.lpr.model.Funcionario;
import edu.ifes.ci.si.les.lpr.model.Protetico;
import edu.ifes.ci.si.les.lpr.model.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{

	List<OrdemServico> findByFuncionario(Funcionario funcionario, Sort sort);	
	List<OrdemServico> findByCliente(Cliente cliente, Sort sort);
	List<OrdemServico> findByProtetico(Protetico protetico, Sort sort);
	
}
