package edu.ifes.ci.si.les.lpr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Funcionario findByEmail(String email);
	
	
	
	@Transactional
    @Query(value = "SELECT count(*)" + 
	    		   "  FROM ordem_servico, funcionario" + 
	    		   " WHERE ordem_servico.funcionario_id = funcionario.id" + 
	    		   "   AND ordem_servico.atualizacao_etapa < 5" +
	    		   "   AND funcionario.id = ?1", nativeQuery = true)
    public Integer countOrdemServico(Long funcionarioId);
	
	
	
}
