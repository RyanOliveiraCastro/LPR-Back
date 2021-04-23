package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TabelaPreco implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotNull(message ="Nome deve ser Preenchido")
	private String descricao;	
	

	@OneToMany(mappedBy = "id.tabelaPreco", cascade = CascadeType.ALL)
    private Collection<ItemTprecoTservico> itens = new ArrayList<>();

   
   @Builder
   public TabelaPreco(Long id, String descricao) {
       this.id = id;
       this.descricao = descricao;
   }
}
