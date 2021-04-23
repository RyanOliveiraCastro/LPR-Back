package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.ifes.ci.si.les.lpr.model.enums.FormaPagamento;
import edu.ifes.ci.si.les.lpr.model.enums.TipoTransporte;
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
public class Transporte implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private TransportePK id = new TransportePK();
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataEntrega;

	@NotNull(message ="Forma de Transporte deve ser Preenchido")
	private TipoTransporte formaTransporte;	

	@NotNull(message ="Valor deve ser Preenchido")
	private Double valor;
	
	@Builder
	public Transporte(ItemOs itemOs, Date dataEntrega, TipoTransporte formaTransporte, Double valor) {
		this.id.setItemOs(itemOs);
		this.dataEntrega = dataEntrega;
		this.formaTransporte = formaTransporte;
		this.valor = valor;
	}
	
	public Long getOrdemServicoId() {
		return id.getItemOs().getOrdemServico().getId();
	}
	
	public Long getServicoId() {
		return id.getItemOs().getServico().getId();
	}
	
	public void setOrdemServicoId(Long id) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setId(id);
		this.id.getItemOs().setOrdemServico(ordemServico);
	}
	
	public void setServicoId(Long id) {
		Servico servico = new Servico();
		servico.setId(id);
		this.id.getItemOs().setServico(servico);
	}

}
