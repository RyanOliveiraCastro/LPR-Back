package edu.ifes.ci.si.les.lpr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Cliente findByEmail(String email);
	
	
	@Transactional
    @Query(value =  "SELECT SUM(ordem_servico.valor) valor" + 
    				" FROM ordem_servico, cliente, pagamento" + 
    				" WHERE ordem_servico.cliente_id = cliente.id" + 
    				" AND ordem_servico.id = pagamento.ordem_servico_id" + 
    				" AND pagamento.pago = false" + 
    				" AND cliente.id = ?1", nativeQuery = true)
    public Double findDebitoCliente(Long clienteId);
	
	
	
}
