package br.pucrs.segmanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.pucrs.segmanager.dao.SeguradoraDAO;
import br.pucrs.segmanager.dao.SeguroDAO;
import br.pucrs.segmanager.model.Seguradora;
import br.pucrs.segmanager.model.Seguro;

@SuppressWarnings("rawtypes")
@ManagedBean(name="seguroController")
@ViewScoped
public class SeguroController {

	private Seguro seguro;
	private SeguroDAO seguroDAO;
	private SeguradoraDAO seguradoraDAO;
	private Map<String, Seguradora> seguradoras;
	
	private List<Seguro> listSeguros;
	private boolean stExibeRelatorio = true;
	
	
	@PostConstruct
	public void init() {
		setSeguro(new Seguro());
		seguroDAO = new SeguroDAO();
		seguradoraDAO = new SeguradoraDAO();
		setSeguradoras(new HashMap<String, Seguradora>());
		
		setListSeguros(seguroDAO.findAll(new Seguro()));
		
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
	
}
