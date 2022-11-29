package control;


import static spark.Spark.*;

import java.text.DecimalFormat;
import java.util.HashMap;

import model.Gastos;
import model.Receitas;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import view.TipoGasto;
import view.TipoReceita;

public class Rotas {
	
	public static long id;
	
	public static void newRotas() {
	VelocityTemplateEngine engine = new VelocityTemplateEngine();
	staticFiles.location("/public");
	
	get("/", Rotas::pageHome, engine);
	get("/home/conta/:id", Rotas::contaLogada, engine);
	get("/home/cadastrar", Rotas::cadastrar, engine);
	
	post("/cadastrar", ContaController::criarConta);
	post("/entrar", ContaController::entrarNaConta);
	post("/adicionarReceita/:id", TransacaoController::adicionarTransacao);
	
	}
	
	private static ModelAndView pageHome(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<>();
		return new ModelAndView(model, "view/home.vm");
	}
	
	private static ModelAndView contaLogada(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<>();
		long id = Long.parseLong(req.params("id"));
		
		model.put("id", id);
		model.put("tiposReceitas", TipoReceita.values());
		model.put("tiposGastos", TipoGasto.values());
		
		DecimalFormat df = new DecimalFormat("#,#00.00");
		Receitas receitas = new Receitas();
		model.put("totalReceitas",df.format(receitas.getTotal(id)));
		Gastos gastos = new Gastos();
		model.put("totalGastos",df.format(gastos.getTotal(id)));
		
		model.put("totalSaldo",df.format(receitas.getTotal(id) - gastos.getTotal(id)));
		return new ModelAndView(model, "view/conta.vm");
	}
	
	private static ModelAndView cadastrar(Request req, Response res) {
		HashMap<String, Object> model = new HashMap<>();
		return new ModelAndView(model, "view/cadastrar.vm");
	}

}
