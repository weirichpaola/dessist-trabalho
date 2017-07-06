package br.pucrs.segmanager.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.pucrs.segmanager.builder.SeguradoBackupBuilder;
import br.pucrs.segmanager.builder.SeguradoraBackupBuilder;
import br.pucrs.segmanager.builder.SeguroBackupBuilder;
import br.pucrs.segmanager.builder.UsuarioBackupBuilder;
import br.pucrs.segmanager.model.Segurado;
import br.pucrs.segmanager.model.Seguradora;
import br.pucrs.segmanager.model.Seguro;
import br.pucrs.segmanager.model.Usuario;

public class AdminDAO<E> extends GenericDAO<E>{

	/**
	 *
	 * @return scripts de insert no banco de dados de acordo com os dados atuais
	 */
	public StringBuilder gerarBackup() {
		StringBuilder sb = new StringBuilder();
		
//		Usuario usuario = new Usuario();
		Segurado segurado = new Segurado();
		Seguradora seguradora = new Seguradora();
		Seguro seguro = new Seguro();
		Usuario usuario = new Usuario();

		// FIXME substituir por algum método usando reflection
		List<Object> entrada = new ArrayList<>();
		entrada.add(usuario);
		entrada.add(segurado);
		entrada.add(seguradora);
		entrada.add(seguro);
		
		for(Object o : entrada) {
			for(Object r : findAll(o)) {
				if(r instanceof Segurado) {
					sb.append((new SeguradoBackupBuilder((Segurado) r).retornaRegistro()));
				} else if(r instanceof Usuario) {
					sb.append((new UsuarioBackupBuilder((Usuario) r).retornaRegistro()));
				} else if(r instanceof Seguradora) {
					sb.append((new SeguradoraBackupBuilder((Seguradora) r).retornaRegistro()));
				} else if(r instanceof Seguro) {
					sb.append((new SeguroBackupBuilder((Seguro) r).retornaRegistro()));
				}
			}
		}
		
		return sb;
	}
	
	/**
	 * Mpetodo que efetua o restore do banco de dados, a partir do backup efetuado anteriormente.
	 * @param inserts
	 */
	public void efetuarRestore(List<String> inserts) {
		try{
			em.getTransaction().begin();
			
			Query query = em.createNativeQuery("delete from seg_seguro");
			query.executeUpdate();
			
			query = em.createNativeQuery("delete from seg_seguradora");
			query.executeUpdate();
			
			query = em.createNativeQuery("delete from seg_segurado");
			query.executeUpdate();
			
			query = em.createNativeQuery("delete from seg_usuario");
			query.executeUpdate();
			
			for(String insert : inserts) {
				if(!insert.contains("INSERT")) {
					continue;
				}
				Query nativeQuery = em.createNativeQuery(insert.replace(";", ""));
				nativeQuery.executeUpdate();			
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
		}
	}
	
}
