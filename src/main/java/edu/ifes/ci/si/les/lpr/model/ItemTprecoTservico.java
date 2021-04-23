package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
public class ItemTprecoTservico implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	@JsonIgnore
	@EmbeddedId
	private ItemTprecoTservicoPK id = new ItemTprecoTservicoPK();
		
	@NotNull(message ="Prazo deve ser Preenchido")
	private Integer prazo;
	
	@NotNull(message ="valor deve ser Preenchido")
	private Double valor;
	
	@Builder
    public ItemTprecoTservico(TabelaPreco tabelaPreco , TipoServico tipoServico, Integer prazo, Double valor) {
		this.id.setTabelaPreco(tabelaPreco);
        this.id.setTipoServico(tipoServico);
        this.prazo = prazo;
        this.valor = valor;        
    }
	
	@JsonIgnore
	public TabelaPreco getTabelaPreco() {
		return id.getTabelaPreco();
	}
	
	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		id.setTabelaPreco(tabelaPreco);
	}	
	
	public TipoServico getTipoServico(){
		return id.getTipoServico();
	}
	
	public void setTipoServico(TipoServico tipoServico) {
		id.setTipoServico(tipoServico);
	}
}
