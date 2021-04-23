package edu.ifes.ci.si.les.lpr.model.enums;

public enum TipoTransporte {

	MOTOBOY(0, "MOTOBOY"),
	CORREIOS(1, "CORREIOS"),
	LOCAL(2, "ENTREGA LOCAL");
	
	private int cod;
	private String nome;
	
	private TipoTransporte(int cod, String nome) {
		this.cod = cod;
		this.nome = nome;
		
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return nome;
	}	
	
	public static TipoTransporte toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoTransporte x : TipoTransporte.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
