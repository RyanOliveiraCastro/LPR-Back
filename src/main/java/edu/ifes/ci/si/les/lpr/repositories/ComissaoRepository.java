package edu.ifes.ci.si.les.lpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Comissao;

@Repository
public interface ComissaoRepository extends JpaRepository<Comissao, Long>{

}
