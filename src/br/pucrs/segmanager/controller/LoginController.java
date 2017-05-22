package br.pucrs.segmanager.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.pucrs.segmanager.dao.UsuarioDAO;
import br.pucrs.segmanager.model.Usuario;
import br.pucrs.segmanager.utils.SessionUtils;


@ManagedBean(name="loginController")
@SessionScoped
public class LoginController {

	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private String perfil;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
	}
	
	/**
	 * Método para efetuar login
	 */
	public String logar() {
		Usuario usuarioAux = usuarioDAO.findUsuario(usuario);
		
		if(usuarioAux == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Usuário/senha incorretos!", ""));
			return "login";
		} else {
			if(usuarioAux.getPerfil().equals("corretor")) {
				setPerfil("A");
			} else {
				setPerfil("C");
			}
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("usuario", usuarioAux);
			
			if(perfil.equals("A")) {
				return "segurados";
			} else {
				return "meusseguros";
			}
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
}
