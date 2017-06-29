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

	public StringBuilder gerarBackup() {
		StringBuilder sb = new StringBuilder();
		
		Usuario usuario = new Usuario();
		Segurado segurado = new Segurado();
		Seguradora seguradora = new Seguradora();
		Seguro seguro = new Seguro();

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
	
	public void efetuarRestore(List<String> inserts) {
		em.getTransaction().begin();

		for(String insert : inserts) {
			Query nativeQuery = em.createNativeQuery(insert.replace(";", ""));
			nativeQuery.executeUpdate();			
		}
		
		em.getTransaction().commit();
	}
	
}
