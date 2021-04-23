package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Uf implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 2)
	@NotNull(message = "Sigla deve ser Preenchida")
	@Size(min = 2, max = 2, message = "Sigla da UF deve ter 2 letras")    
	private String sigla;
			
	@Column(length = 50)
	@NotNull(message = "Nome deve ser Preenchido")
	@Size(min = 2, max = 50, message = "Nome da UF deve ter entre 2 e 50 letras")    
	private String nome;

}
