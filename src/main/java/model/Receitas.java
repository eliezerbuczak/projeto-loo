package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import dao.GenericDao;
import view.TipoReceita;

@Entity
@DiscriminatorValue("R")
public class Receitas extends Transacoes{
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	@Column(name = "tipo_receita")
	private Integer tipoReceita;
	
	public Receitas() {
	}
	public Receitas(double valorEntrada, TipoReceita tipoReceita, String motivo, Conta conta) {
		super(valorEntrada, motivo);
		setConta(conta);
		setTipoReceita(tipoReceita);
	}
	
	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public TipoReceita getTipoReceita() {
		return TipoReceita.valor(tipoReceita);
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		if(tipoReceita != null) {
		this.tipoReceita = tipoReceita.getCod();
		}
	}
	
	public double getTotal(long idConta) {
		double totalReceitas = 0;
		try {
			GenericDao<Receitas> daoReceita = new GenericDao<Receitas>();
			Receitas receita = new Receitas();

			List<Receitas> receitas = daoReceita.listAll(receita);
			Iterator<Receitas> it = receitas.iterator();
			while(it.hasNext()) {
				Receitas i = it.next();
				if(i.getConta().getId() == idConta)
				totalReceitas += i.getValorEntrada();
			}
		} catch (Exception e) {
				System.out.println("Tentou mostrar as receitas mas falhou");
		}
		
		return totalReceitas;
	}
	
}
