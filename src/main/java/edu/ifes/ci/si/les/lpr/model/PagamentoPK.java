package edu.ifes.ci.si.les.lpr.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@EqualsAndHashCode(of = {"itemOs"})
public class PagamentoPK implements Serializable {

	private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "ordemServico_id", referencedColumnName = "ordemServico_id"),
        @JoinColumn(name = "servico_id", referencedColumnName = "servico_id")
    })
    
    private ItemOs itemOs = new ItemOs();
}
