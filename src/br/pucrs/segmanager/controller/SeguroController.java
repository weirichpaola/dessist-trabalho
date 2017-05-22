package br.pucrs.segmanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.pucrs.segmanager.dao.SeguradoDAO;
import br.pucrs.segmanager.dao.SeguradoraDAO;
import br.pucrs.segmanager.dao.SeguroDAO;
import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Seguradora;
import br.pucrs.segmanager.model.Seguro;

@SuppressWarnings("rawtypes")
@ManagedBean(name="seguroController")
@ViewScoped
public class SeguroController {

	private Seguro seguro;
	private SeguroDAO seguroDAO;
	private SeguradoDAO seguradoDAO;
	private SeguradoraDAO seguradoraDAO;
	private Map<String, Seguradora> seguradoras; //exibicao - valor
	private List<String> seguradoras2; //exibicao - valor
	private Map<String, Segurado> segurados;
	
	private List<Seguro> listSeguros;
	private boolean stExibeRelatorio = true;
	
	
	@PostConstruct
	public void init() {
		setSeguro(new Seguro());
		seguroDAO = new SeguroDAO();
		seguradoraDAO = new SeguradoraDAO();
		seguradoDAO = new SeguradoDAO();
		
		setSegurados(new HashMap<String, Segurado>());
		setSeguradoras(new HashMap<String, Seguradora>());
		setSeguradoras2(new ArrayList<String>());
		
		setListSeguros(seguroDAO.findAll(new Seguro()));
				
		for(Object s : seguradoDAO.findAll(new Segurado())) {
			Segurado aux = (Segurado) s;
			getSegurados().put(aux.getNome(), aux);
		}
		
		for(Object s : seguradoraDAO.findAll(new Seguradora())) {
			Seguradora aux = (Seguradora) s;
			getSeguradoras().put(aux.getNome(), aux);
		}
	}
	
	/**
	 * Método que persiste um Seguro
	 * @return 
	 */
	public String salvarSeguro() {
		seguroDAO.save(seguro);
		seguro = new Seguro();
		return "seguros";
	}
	
	/**
	 * Seleciona um seguro para edição.
	 * @param seg
	 * @return
	 */
	public String editarSeguro(Seguro seg)	{
		seguro = seg;
		stExibeRelatorio = false;
		return null;
	}
	
	public String adicionarSeguro()	{
		seguro = new Seguro();
		
		stExibeRelatorio = false;
		
		return null;
	}
	
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public List<Seguro> getListSeguros() {
		return listSeguros;
	}

	public void setListSeguros(List<Seguro> listSeguros) {
		this.listSeguros = listSeguros;
	}

	public boolean isStExibeRelatorio() {
		return stExibeRelatorio;
	}

	public void setStExibeRelatorio(boolean stExibeRelatorio) {
		this.stExibeRelatorio = stExibeRelatorio;
	}

	public Map<String, Seguradora> getSeguradoras() {
		return seguradoras;
	}

	public void setSeguradoras(Map<String, Seguradora> seguradoras) {
		this.seguradoras = seguradoras;
	}

	public Map<String, Segurado> getSegurados() {
		return segurados;
	}

	public void setSegurados(Map<String, Segurado> segurados) {
		this.segurados = segurados;
	}

	public List<String> getSeguradoras2() {
		return seguradoras2;
	}

	public void setSeguradoras2(List<String> seguradoras2) {
		this.seguradoras2 = seguradoras2;
	}
	
}
