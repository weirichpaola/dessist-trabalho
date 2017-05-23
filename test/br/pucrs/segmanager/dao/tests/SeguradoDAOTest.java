package br.pucrs.segmanager.dao.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.RollbackException;

import org.junit.Before;
import org.junit.Test;

import br.pucrs.segmanager.dao.SeguradoDAO;
import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Usuario;

public class SeguradoDAOTest {

	private Segurado segurado;
	private SeguradoDAO dao;
	
	@Before
	public void init() {
		segurado = new Segurado();
		dao = new SeguradoDAO();
	}

	@Test(expected=RollbackException.class)
	public void loginESenhaValidosTest() {
		List<Segurado> segurados = dao.findAll(new Segurado());
		
		segurado.setCpf(segurados.get(0).getCpf());
		dao.save(segurado);
		
	}

}
