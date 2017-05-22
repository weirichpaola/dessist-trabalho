package br.pucrs.segmanager.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.pucrs.segmanager.dao.SeguradoDAO;
import br.pucrs.segmanager.model.Segurado;

@FacesConverter(forClass = Segurado.class)
public class SeguradoConverter implements Converter {
	    
	@Override
	    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
	        if (value != null && !value.isEmpty()) {
	        	SeguradoDAO dao = new SeguradoDAO();
	        	Segurado segurado = new Segurado();
	        	segurado.setId(Long.valueOf(value));
	        	Segurado aux = dao.findByOneFilter(segurado);
	        	
	            return aux;
	        }
	        return null;
	    }

	    @Override
	    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
	        if (value instanceof Segurado) {
	        	Segurado entity = (Segurado) value;
	            if (entity != null && entity instanceof Segurado && entity.getId() != null) {
	                uiComponent.getAttributes().put( entity.getId().toString(), entity);
	                return entity.getId().toString();
	            }
	        }
	        return "";
	    }
}