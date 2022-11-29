package teste;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Usuario;

public class UsuarioTeste {
  Usuario us, usu;

  @BeforeEach
  public void setUp() {
    us = new Usuario("Eliezer", "eliezerbuczak@gmail.com", "eliezer123");
  }

  @Test
  public void deveRetornarONomeDoUsuario() {

    assertEquals("Eliezer", us.getName());
  }

  @Test
  public void deveRetornarOEmailDoUsuario() {

    assertEquals("eliezerbuczak@gmail.com", us.getEmail());
  }

  @Test
  public void deveRetornarASenhaDoUsuario() {
    assertEquals("eliezer123", us.getSenha());
  }
}
