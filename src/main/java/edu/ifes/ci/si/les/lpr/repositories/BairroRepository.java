package edu.ifes.ci.si.les.lpr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.lpr.model.Bairro;
import edu.ifes.ci.si.les.lpr.model.Cidade;
import edu.ifes.ci.si.les.lpr.model.Uf;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {

	@Transactional(readOnly = true)
	public List<Bairro> findByCidade(Cidade cidade);

	List<Uf> findByNome(String nome);

	List<Uf> findByNomeContaining(String nome);

}
