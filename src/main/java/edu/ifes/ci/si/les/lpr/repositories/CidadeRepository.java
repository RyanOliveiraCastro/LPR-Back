package edu.ifes.ci.si.les.lpr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.lpr.model.Cidade;
import edu.ifes.ci.si.les.lpr.model.Uf;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Transactional(readOnly = true)
	public List<Cidade> findByUf(Uf uf);

	List<Cidade> findByNome(String nome);

	List<Cidade> findByNomeContaining(String nome);

}
