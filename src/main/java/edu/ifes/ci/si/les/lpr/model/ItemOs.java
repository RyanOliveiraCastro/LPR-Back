package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ItemOs implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemOsPK id = new ItemOsPK();
	
	@NotNull(message = "Valor do Item deve ser preenchido")
    @Digits(integer=6, fraction=2, message = "Valor do Itemdeve ser preenchido com dígitos")
    private Double valor;

	@Builder
    public ItemOs(OrdemServico ordemServico, Servico servico, Double valor) {
       this.id.setOrdemServico(ordemServico);
       this.id.setServico(servico);
       this.valor = valor;
    }
	
	@JsonIgnore
	public OrdemServico getOrdemServico() {
		return id.getOrdemServico();
	}
	
	public void setOrdemServico(OrdemServico ordemServico) {
		id.setOrdemServico(ordemServico);
	}
	
	public Servico getServico() {
		return id.getServico();
	}
	
	public void setServico(Servico servico) {
		id.setServico(servico);
	}
	
}
