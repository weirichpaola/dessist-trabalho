package br.pucrs.segmanager.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.pucrs.segmanager.model.Seguradora;

public class SeguradoraDAO extends GenericDAO {

	public Seguradora findByOneFilter(Seguradora example) {
		StringBuilder hql = new StringBuilder();
		
		hql.append("select s ");
		hql.append("  from Seguradora s ");
		hql.append(" where s.id = :id ");
		
		Query query = em.createQuery(hql.toString());
		
		query.setParameter("id", example.getId());
		
		Seguradora seguradora = null;
		
		try {
			seguradora = (Seguradora) query.getSingleResult();
		} catch (NoResultException e) {
		}
		
		return seguradora;
	}
	
}
