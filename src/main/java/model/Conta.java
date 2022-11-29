package model;

import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Table(name = "conta")
public class Conta implements BaseEntity{
	public String toString() {
		return "Conta [id=" + id + ", usuario=" + usuario + ", saldo=" + saldo + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "saldo")
	private double saldo;	
	
	public Conta(Usuario usuario) {
		setUsuario(usuario);
		setSaldo(0);
		/*this.receitas = new ArrayList<Integer>();
		this.gastos = new ArrayList<Integer>();*/
	}
	
	public Conta() {}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
/*	public void adicionarGasto(int id) {
		this.gastos.add(id);
	}
	public void adicionarReceita(int id) {
		this.receitas.add(id);
	}
	public double receitaTotal() {
		double receitaTotal = 0;
		Iterator<Receitas> it = receitas.iterator();
		while(it.hasNext()) {
			Receitas i = it.next();
			receitaTotal += i.getValorEntrada();
		}
		return receitaTotal;
	}
	
	public double gastosTotal() {
		double gastosTotal = 0;
		Iterator<Gastos> it = gastos.iterator();
		while(it.hasNext()) {
			Gastos i = it.next();
			gastosTotal += i.getValorEntrada();
		}
		return gastosTotal;
	}

	public double saldoTotal() {
		this.saldo = receitaTotal() - gastosTotal();
		return saldo;
	}*/

	public long getId() {
		return id;
	}
}
