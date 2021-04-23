package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"tabelaPreco", "tipoServico"})
public class ItemTprecoTservicoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    
	@ManyToOne
	@JoinColumn (name = "tabelaPreco_id")
	private TabelaPreco tabelaPreco;

	
	@ManyToOne
	@JoinColumn (name = "tipoServico_id")
	private TipoServico tipoServico;
}
