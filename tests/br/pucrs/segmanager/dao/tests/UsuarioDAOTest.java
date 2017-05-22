package br.pucrs.segmanager.dao.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.pucrs.segmanager.dao.UsuarioDAO;
import br.pucrs.segmanager.model.Usuario;

public class UsuarioDAOTest {

	private UsuarioDAO daoTest;
	private Usuario usuario;
	
	@Before
	public void init() {
		daoTest = new UsuarioDAO();
		usuario = new Usuario();
	}
	
	@Test
	public void loginESenhaInvalidosTest() {
		usuario.setEmail("email_inexistente");
		usuario.setSenha("senha_ivalidada");
		
		Usuario findUsuario = daoTest.findUsuario(usuario);
		
		assertNull(findUsuario);
	}
	
	@Test
	public void loginESenhaValidosTest() {
		usuario.setEmail("nelson");
		usuario.setSenha("1");
		
		Usuario findUsuario = daoTest.findUsuario(usuario);
		
		assertNotNull(findUsuario);
	}

}
