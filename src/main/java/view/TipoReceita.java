package view;

public enum TipoReceita{
	SALARIO(1), 
	RENDA_EXTRA(2);
	
	private int cod;
	
	private TipoReceita(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public static TipoReceita valor(int cod) {
		for(TipoReceita tipo : TipoReceita.values()) {
			if(cod == tipo.getCod()) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Numero do tipo inexistente");
	}
}
