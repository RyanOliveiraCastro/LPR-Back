package edu.ifes.ci.si.les.lpr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Pagamento;
import edu.ifes.ci.si.les.lpr.model.PagamentoPK;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, PagamentoPK>{


	@Transactional
    @Query(value = "SELECT pago FROM pagamento WHERE ordem_servico_id = ?1", nativeQuery = true)
    public Boolean findByOS(Long ordemServicoId);
	
}
