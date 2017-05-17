package br.pucrs.segmanager.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que representa um Segurado
 * @author Nelson
 *
 */

@Entity
@Table(name="SEG_SEGURADO")
public class Segurado {

	@Id
	@Column(name="ID_SEGURADO")
	@GeneratedValue(generator="SEG_SQ_SEGURADO")
    @SequenceGenerator(name="SEG_SQ_SEGURADO",sequenceName="SEG_SQ_SEGURADO", allocationSize=1, initialValue=1)
	private Long id;
	
//	@Column(name = "ID_PESSOA_FISICA")
//	private PessoaFisica pessoaFisica;

	@Column(name = "TX_NOME")
	private String nome;
	
	@Column(name="TX_EMAIL", length=255)
	private String email;
	
	@Column(name="TX_TELEFONE", length=255)
	private String telefone;
	
	@Column(name="VL_BONUS")
	private BigDecimal bonus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public PessoaFisica getPessoaFisica() {
//		return pessoaFisica;
//	}
//
//	public void setPessoaFisica(PessoaFisica pessoaFisica) {
//		this.pessoaFisica = pessoaFisica;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
