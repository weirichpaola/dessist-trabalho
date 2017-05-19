package br.pucrs.segmanager.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que representa um Bem
 * @author Nelson
 *
 */

@Entity
@Table(name="SEG_BEM")
public class Bem {

	@Id
	@Column(name="ID_BEM")
	@GeneratedValue(generator="SEG_SQ_BEM")
    @SequenceGenerator(name="SEG_SQ_BEM",sequenceName="SEG_SQ_BEM", allocationSize=1, initialValue=1)
	private Long id;
	
	@Column(name = "TX_DESCRICAO")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}