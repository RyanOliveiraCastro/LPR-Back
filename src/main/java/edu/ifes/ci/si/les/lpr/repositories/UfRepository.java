package edu.ifes.ci.si.les.lpr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

	List<Uf> findByNome(String nome);

	List<Uf> findByNomeContaining(String nome);

}
