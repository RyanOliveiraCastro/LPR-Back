package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comissao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "valor deve ser preenchido")
	private Double valor;

	@ManyToOne
	@JoinColumn(name="servico_id")
	@NotNull(message = "Prazo deve ser preenchido")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name="funcionario_id")
	private Funcionario funcionario;
	
	
	

}
