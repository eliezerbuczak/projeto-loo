package control;

import java.util.Iterator;
import java.util.List;

import dao.GenericDao;
import model.Conta;
import model.Usuario;
import spark.Request;
import spark.Response;

public class ContaController {
	public static Object criarConta(Request req, Response res) {
		
		String nome = req.queryParams("nome");
		String email = req.queryParams("email");
		String senha = req.queryParams("senha");
		Usuario usuario = new Usuario(nome, email,senha);
		Conta conta = new Conta(usuario);
		GenericDao<Usuario> daoUsuario = new GenericDao<Usuario>();
		GenericDao<Conta> daoConta = new GenericDao<Conta>();
		
		
        try {
        	daoUsuario.save(usuario);
        	daoConta.save(conta);
		} catch (Exception e) {
			System.out.println("deu craps na gravação do banco");
			return("Internal Server Craps");
		}
		
		res.redirect("/");
		
		return "ok";
		
	}
	
	public static Object entrarNaConta(Request req, Response res) {
		
		String email = req.queryParams("email");
		String senha = req.queryParams("password");
		
		GenericDao<Conta> daoConta = new GenericDao<Conta>();
		Conta conta = new Conta();
		try {
			List<Conta> contas = daoConta.listAll(conta);
			
			Iterator<Conta> it = contas.iterator();
			while(it.hasNext()) {
				Conta i = it.next();
				if(email.equals(i.getUsuario().getEmail()) && senha.equals(i.getUsuario().getSenha()))
					res.redirect("/home/conta/"+i.getId());
			}
		} catch (Exception e) {
				System.out.println("Tentou entrar na conta mas falhou");
		}
        
		
		return "ok";
		
	}
	
}
