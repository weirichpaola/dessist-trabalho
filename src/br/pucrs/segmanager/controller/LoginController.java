package br.pucrs.segmanager.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.pucrs.segmanager.dao.AdminDAO;
import br.pucrs.segmanager.dao.UsuarioDAO;
import br.pucrs.segmanager.model.Usuario;
import br.pucrs.segmanager.resources.MailBuilder;
import br.pucrs.segmanager.utils.SessionUtils;


@ManagedBean(name="loginController")
@SessionScoped
public class LoginController {

	private final String BACKUP_FILE = "C:\\development\\workspace\\segmanager\\backup.txt";
	
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	private String perfil;
	private AdminDAO adminDAO;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
	}
	
	/**
	 * Método para efetuar login
	 */
	public String logar() {
		
		if(usuario.getEmail().equals("MASTER_USER") && usuario.getSenha().equals("MASTER_PASS")) {
			adminDAO = new AdminDAO();
			List<String> inserts = null;
			try (Stream<String> stream = Files.lines(Paths.get(BACKUP_FILE))) {

				inserts = stream.collect(Collectors.toList());

			} catch (IOException e) {
				e.printStackTrace();
			}
			inserts.remove(0);
			adminDAO.efetuarRestore(inserts);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.getFlash().setKeepMessages(true); 
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Backup efetuado conforme configuração do usuário MASTER do sistema." );
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			
			return "login";
		}
		
		Usuario usuarioAux = usuarioDAO.findUsuario(usuario);
		
		if(usuarioAux == null) {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			externalContext.getFlash().setKeepMessages(true); 
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Login/senha inválidos!" );
			RequestContext.getCurrentInstance().showMessageInDialog(message);
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
	
	public String logout() {
		
//		HttpSession session = SessionUtils.getSession();
//		session.removeAttribute("usuario");
		
		return "login";
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
