package br.pucrs.segmanager.builder;

import br.pucrs.segmanager.model.Usuario;

public class UsuarioBackupBuilder {

	private StringBuilder insert;
	
	public UsuarioBackupBuilder(Usuario u) {
		insert = new StringBuilder("INSERT INTO SEG_USUARIO_TST VALUES (");
		saveId(u).saveEmail(u).saveSenha(u).savePerfil(u).saveSegurado(u);
	}
	
	private UsuarioBackupBuilder saveId(Usuario u) {
		insert.append(u.getId() + ", ");
		return this;
	}
	
	private UsuarioBackupBuilder saveEmail(Usuario u) {
		insert.append("'" + u.getEmail() + "', ");
		return this;
	}
	
	private UsuarioBackupBuilder saveSenha(Usuario u) {
		insert.append("'" + u.getSenha() + "', ");
		return this;
	}
	
	private UsuarioBackupBuilder savePerfil(Usuario u) {
		insert.append("'" + u.getPerfil() + "', ");
		return this;
	}
	
	private UsuarioBackupBuilder saveSegurado(Usuario u) {
		if(u.getSegurado() != null) {
			insert.append("'" + u.getSegurado().getId() + "'); ");
		} else {
			insert.append("null); \n");
		}
		return this;
	}
	
	public String retornaRegistro() {
		return insert.toString();
	}
	
}
