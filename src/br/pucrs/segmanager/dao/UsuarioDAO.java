package br.pucrs.segmanager.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.pucrs.segmanager.model.Usuario;

public class UsuarioDAO<E> extends GenericDAO<E>{

	public Usuario findUsuario(Usuario usuario) {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select u ");
		hql.append("  from Usuario u ");
		hql.append(" where u.email = :email ");
		hql.append("   and u.senha = :senha ");
		
		Query query = em.createQuery(hql.toString());
		
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		Usuario usuarioAUx = null;
		
		try {
			usuarioAUx = (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return usuarioAUx;
	}
	
	
}
