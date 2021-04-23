package edu.ifes.ci.si.les.lpr.model.enums;

public enum FormaPagamento {

	DINHEIRO(0, "DINHEIRO"),	
	BOLETO(1, "BOLETO"),
	CHEQUE(2, "CHEQUE");
	
	private int cod;
	private String nome;
	
	private FormaPagamento(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
		
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return nome;
	}	
	
	public static FormaPagamento toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (FormaPagamento x : FormaPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
