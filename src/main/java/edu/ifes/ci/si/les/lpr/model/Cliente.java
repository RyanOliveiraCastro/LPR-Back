package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Email deve ser Preenchido")
	private String email;
	
	@NotNull(message = "Senha deve ser Preenchido")
	private String senha;

	@NotNull(message ="cro deve ser Preenchido")
	private String cro;
	
	
	@ManyToOne
	@JoinColumn(name = "tipoCliente_id")
	private TipoCliente tipoCliente;

	@Builder
    public Cliente(Long id, String nome, String cpf, String rua, String cep, Integer numero,
            Bairro bairro, Telefone telefone, String email, String senha, String cro, TipoCliente tipoCliente) {
        super(id, nome, cpf, rua, cep, numero, bairro, telefone);
        this.email = email;
        this.senha = senha;
        this.cro = cro;
        this.tipoCliente = tipoCliente;
    }
	
}
