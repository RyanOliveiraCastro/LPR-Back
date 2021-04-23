package edu.ifes.ci.si.les.lpr.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import edu.ifes.ci.si.les.lpr.model.Caixa;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long>{



	@Transactional
    @Query(value = "SELECT caixa.id FROM caixa " +  
    			 	" WHERE caixa.id NOT IN (" + 
		    		" SELECT caixa.id from item_Os, ordem_servico, caixa" + 
		    		" LEFT JOIN servico ON servico.caixa_id = caixa.id" + 
		    		" WHERE servico.id = item_os.servico_id" + 
		    		" AND ordem_servico.id = item_os.ordem_servico_id" + 
		    		" AND atualizacao_etapa < 5)", nativeQuery = true)
    public List<Caixa> findCaixaNotUsed();
	

}

