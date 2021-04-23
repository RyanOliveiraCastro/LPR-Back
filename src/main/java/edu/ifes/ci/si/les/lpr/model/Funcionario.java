package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionario extends Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull(message ="Email deve ser Preenchido")
	private String email;

	@NotNull(message ="senha deve ser Preenchido")
	private String senha;

	
	@Builder
    public Funcionario(Long id, String nome, String cpf, String rua, String cep, Integer numero,
            Bairro bairro, Telefone telefone, String email, String senha) {
        super(id, nome, cpf, rua, cep, numero, bairro, telefone);
        this.email = email;
        this.senha = senha;
    }
	
}
