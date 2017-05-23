package br.pucrs.segmanager.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Usuario;

public class SeguradoDAO<E> extends GenericDAO<E>{
	
	
	public Segurado findByOneFilter(Segurado example) {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select s ");
		hql.append("  from Segurado s ");
		hql.append(" where s.id = :id ");
		
		Query query = em.createQuery(hql.toString());
		
		query.setParameter("id", example.getId());
		
		Segurado segurado = null;
		
		try {
			segurado = (Segurado) query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return segurado;
	}
	
	public Segurado findByCpf(Segurado example) {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select s ");
		hql.append("  from Segurado s ");
		hql.append(" where s.cpf = :cpf ");
		
		Query query = em.createQuery(hql.toString());
		
		query.setParameter("cpf", example.getCpf());
		
		Segurado segurado = null;
		
		try {
			segurado = (Segurado) query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return segurado;
	}
	
	
	
}
