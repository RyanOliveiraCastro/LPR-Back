package edu.ifes.ci.si.les.lpr.model.enums;

public enum AtualizacaoEtapa {

	RECEBIDA(0, "RECEBIDA", 1),
	REPRODUÇÃO(1, "REPRODUÇÃO",1),
	ESCULPIMENTO(2, "ESCULPIMENTO",3),
	FUNDIÇÃO(3, "FUNDIÇÃO", 1),
	POLIMENTO(4, "POLIMENTO", 1),
	FINALIZADA(5, "FINALIZADA", 0),
	ENVIADO(6, "ENVIADO", 0),
	ENTREGUE(7, "ENTREGUE", 0);
	
	private int cod;
	private String nome;
	private Integer prazo;
	
	private AtualizacaoEtapa(int cod, String nome, Integer prazo) {
		this.cod = cod;
		this.nome = nome;
		this.prazo = prazo;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return nome;
	}
	
	public Integer getPrazo () {
		return prazo;
	}
	
	public static AtualizacaoEtapa toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (AtualizacaoEtapa x : AtualizacaoEtapa.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
