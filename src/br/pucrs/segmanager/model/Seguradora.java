package br.pucrs.segmanager.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que representa um Seguradora
 * @author Nelson
 *
 */

@Entity
@Table(name="SEG_SEGURADORA")
public class Seguradora {

	@Id
	@Column(name="ID_SEGURADORA")
	@GeneratedValue(generator="SEG_SQ_SEGURADORA")
    @SequenceGenerator(name="SEG_SQ_SEGURADORA",sequenceName="SEG_SQ_SEGURADORA", allocationSize=1, initialValue=1)
	private Long id;
	
	@Column(name = "TX_SEGURADORA")
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}