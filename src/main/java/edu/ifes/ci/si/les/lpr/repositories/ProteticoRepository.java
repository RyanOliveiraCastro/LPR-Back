package edu.ifes.ci.si.les.lpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Protetico;

@Repository
public interface ProteticoRepository extends JpaRepository<Protetico, Long>{
	Protetico findByEmail(String email);
}
