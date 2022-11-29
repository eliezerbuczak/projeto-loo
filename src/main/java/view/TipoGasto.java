package view;

public enum TipoGasto {
	CASA(1),
	ALIMENTAÇÃO(2),
	VESTIMENTA(3),
	LAZER(4),
	ENTRETENIMENTO(5),
	OUTROS(6);
	
	private int cod;
	
	private TipoGasto(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public static TipoGasto valor(int cod) {
		for(TipoGasto tipo : TipoGasto.values()) {
			if(cod == tipo.getCod()) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Numero do tipo inexistente");
	}

}
