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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguradora other = (Seguradora) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}