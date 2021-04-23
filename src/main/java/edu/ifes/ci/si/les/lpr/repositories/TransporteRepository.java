package edu.ifes.ci.si.les.lpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.Transporte;
import edu.ifes.ci.si.les.lpr.model.TransportePK;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, TransportePK>{

}
