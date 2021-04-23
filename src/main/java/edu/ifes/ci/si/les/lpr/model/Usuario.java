package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message ="nome deve ser Preenchido")
	private String nome;

	@NotNull(message ="cpf deve ser Preenchido")
	private String cpf;
	
	@Column(length = 50)
    @NotBlank(message = "Rua da Pessoa deve ser preenchido")
    @Size(min = 2, max = 50, message = "Rua da Pessoa deve ter entre 2 e 50 letras")
    private String rua;
	
	@NotNull(message ="cpf deve ser Preenchido")
	private String cep;

    @Digits(integer=5, fraction=0, message = "Número da Casa da Pessoa deve ser preenchido com um valor inteiro")
    private Integer numero;

	@ManyToOne
    @JoinColumn(name = "bairro_id")
	private Bairro bairro;
	
	@ManyToOne
    @JoinColumn(name = "telefone_id")
	private Telefone telefone;

	
}
