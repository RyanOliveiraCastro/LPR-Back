package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import edu.ifes.ci.si.les.lpr.model.enums.AtualizacaoEtapa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Paciente deve ser Preenchido")
	private String paciente;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date recebido;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date entrega;

	@NotNull(message = "Observacao deve ser Preenchido")
	private String observacao;

	@NotNull(message = "Valor deve ser Preenchido")
	private Double valor;

	@OneToMany(mappedBy = "id.ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ItemOs> itens = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "protetico_id")
	private Protetico protetico;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private AtualizacaoEtapa atualizacaoEtapa;

	@Builder
	public OrdemServico(Long id, Date recebido, Date entrega, String paciente, String observacao, Double valor, Cliente cliente,
			Funcionario funcionario, Protetico protetico, AtualizacaoEtapa atualizacaoEtapa) {
		this.id = id;
		this.recebido = recebido;
		this.entrega = entrega;
		this.paciente = paciente;
		this.observacao = observacao;
		this.valor = valor;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.protetico = protetico;
		this.atualizacaoEtapa = atualizacaoEtapa;
	}

}
