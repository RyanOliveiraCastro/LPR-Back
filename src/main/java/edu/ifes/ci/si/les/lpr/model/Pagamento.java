package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.ifes.ci.si.les.lpr.model.enums.FormaPagamento;
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
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private PagamentoPK id = new PagamentoPK();

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataPagamento;

	@NotNull(message = "Forma de Pagamento deve ser Preenchido")
	private FormaPagamento formaPagamento;

	@NotNull(message = "Boolean deve ser Preenchido")
	private Boolean pago;
	
	
	@Builder
	public Pagamento(ItemOs itemOs, Date dataPagamento, FormaPagamento formaPagamento, Boolean pago) {
		this.id.setItemOs(itemOs);
		this.dataPagamento = dataPagamento;
		this.formaPagamento = formaPagamento;
		this.pago = pago;
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
