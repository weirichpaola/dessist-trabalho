package br.pucrs.segmanager.dao.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.pucrs.segmanager.dao.AdminDAO;
import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Seguradora;
import br.pucrs.segmanager.model.Seguro;
import br.pucrs.segmanager.model.Usuario;

public class AdminDAOTest {

	private AdminDAO dao;

	@Before
	public void setUp() throws Exception {
		dao = new AdminDAO();
	}

	@Test
	public void test() {
		
		List<Object> classes = new ArrayList();
		classes.add(new Usuario());
		classes.add(new Segurado());
		classes.add(new Seguro());
		classes.add(new Seguradora());
		
		
		for(Object clazz : classes) {
			if(clazz instanceof Usuario) {
				backupUsuario(clazz);
			}
		}
		
	}

	private void backupUsuario(Object clazz) {
		List findAll = dao.findAll(clazz);
		for (Object o : findAll) {
			Usuario aux = (Usuario) o;
			Long idSegurado = null;
			try {
				idSegurado = aux.getSegurado().getId();
			} catch (Exception e) {
				// TODO: handle exception
			}

			StringBuffer insert = new StringBuffer();
			insert.append("INSERT INTO SEG_USUARIO VALUES (");
			insert.append(aux.getId() + ", " + aux.getEmail() + ", " + aux.getSenha() + ", " + aux.getPerfil() + ", "
					+ idSegurado + ");");
			
			System.out.println(insert.toString());
		}
	}

}
