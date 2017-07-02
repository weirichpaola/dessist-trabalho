package br.pucrs.segmanager.dao;

import java.util.List;

import javax.persistence.Query;

import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Seguro;

public class SeguroDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public List<Seguro> findSegurosBySegurado(Segurado segurado) {

		StringBuilder hql = new StringBuilder();

		hql.append("select s ");
		hql.append("  from Seguro s ");
		hql.append(" where s.segurado = :segurado ");

		Query query = em.createQuery(hql.toString());
		
		query.setParameter("segurado", segurado);
		
		return query.getResultList();

	}
}
