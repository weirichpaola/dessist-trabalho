package br.pucrs.segmanager.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.pucrs.segmanager.dao.SeguradoDAO;
import br.pucrs.segmanager.model.Segurado;

@SuppressWarnings("rawtypes")
@ManagedBean(name="seguradoController")
@ViewScoped
public class SeguradoController {

	private Segurado segurado;
	private SeguradoDAO seguradoDAO;
	
	private List<Segurado> listSegurados;
	
	
	@PostConstruct
	public void init() {
		setSegurado(new Segurado());
		seguradoDAO = new SeguradoDAO();
		setListSegurados(seguradoDAO.findAll(new Segurado()));
	}
	
	/**
	 * Método que persiste um Segurado
	 * @return 
	 */
	public String salvarSegurado() {
		seguradoDAO.save(segurado);
		return "segurados";
	}

	public Segurado getSegurado() {
		return segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}

	public List<Segurado> getListSegurados() {
		return listSegurados;
	}

	public void setListSegurados(List<Segurado> listSegurados) {
		this.listSegurados = listSegurados;
	}
	
}
