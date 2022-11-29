package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dao.GenericDao;
import view.TipoGasto;

@Entity
@DiscriminatorValue("G")
public class Gastos extends Transacoes{
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	@Column(name = "tipo_gasto")
	private Integer tipoGasto;
	
	public Gastos() {}
	
	public Gastos(double valorEntrada, TipoGasto tipoGasto, String motivo, Conta conta) {
		super(valorEntrada, motivo);
		setConta(conta);
		setTipoReceita(tipoGasto);
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public TipoGasto getTipoReceita() {
		return TipoGasto.valor(tipoGasto);
	}

	public void setTipoReceita(TipoGasto tipoGasto) {
		if(tipoGasto != null) {
		this.tipoGasto = tipoGasto.getCod();
		}
	}
	
	public double getTotal(long idConta) {
		double totalGastos = 0;
		try {
			GenericDao<Gastos> daoGastos = new GenericDao<Gastos>();
			Gastos gasto = new Gastos();

			List<Gastos> gastos = daoGastos.listAll(gasto);
			System.out.print("################################ " + gastos);
			Iterator<Gastos> it = gastos.iterator();
			while(it.hasNext()) {
				Gastos i = it.next();
				if(i.getConta().getId() == idConta)
					totalGastos += i.getValorEntrada();
				System.out.print("################################ " + totalGastos);
			}
		} catch (Exception e) {
				System.out.println("Tentou mostrar as receitas mas falhou");
		}
		return totalGastos;
	}
	
}