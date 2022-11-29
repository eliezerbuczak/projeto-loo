package control;

import dao.GenericDao;
import model.Conta;
import model.Gastos;
import model.Receitas;
import model.Transacoes;
import spark.Request;
import spark.Response;
import view.TipoGasto;
import view.TipoReceita;

public class TransacaoController {

	public static Object adicionarTransacao(Request req, Response res) {
		
		long idConta = Long.parseLong(req.params("id"));
		
		String transacao = req.queryParams("transacao");
		
		int tipo = Integer.parseInt(req.queryParams("tipo"));
		
		
		String motivo = req.queryParams("motivo");
		double valor = Double.parseDouble(req.queryParams("valor"));
		
		GenericDao<Conta> genericDao = new GenericDao<Conta>();
		Conta conta = new Conta();
        conta = genericDao.getObjectById(conta, idConta);
		
        
        GenericDao<Transacoes> daoTransacao = new GenericDao<Transacoes>();
		if(transacao.equals("gasto")) {
			TipoGasto aux = TipoGasto.valor(tipo);
			Gastos gasto = new Gastos(valor, aux, motivo, conta);
			
			try {
				daoTransacao.save(gasto);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("deu craps na gravação do banco ### GASTO ###");
				return("Internal Server Craps");
			}
		} else {
			TipoReceita aux = TipoReceita.valor(tipo);
			Receitas receita = new Receitas(valor, aux, motivo, conta);
			
			try {
				daoTransacao.save(receita);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("deu craps na gravação do banco ### RECEITA ###");
				return("Internal Server Craps");
			}
		}
		res.redirect("/home/conta/" + req.params("id")); 
        
		return "ok";
		
	}
}
