package edu.ifes.ci.si.les.lpr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.lpr.model.ItemOs;
import edu.ifes.ci.si.les.lpr.model.ItemOsPK;

@Repository
public interface ItemOsRepository extends JpaRepository<ItemOs, ItemOsPK>{

}
