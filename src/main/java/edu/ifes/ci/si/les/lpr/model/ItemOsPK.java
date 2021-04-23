package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Embeddable
@Data
@EqualsAndHashCode(of = {"ordemServico", "servico"})
public class ItemOsPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn (name = "ordemServico_id")
	private OrdemServico ordemServico;

	@ManyToOne
	@JoinColumn (name = "servico_id")
	private Servico servico;

}
