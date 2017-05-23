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
	
	@Column(name = "TX_NOME")
	private String nome;
	
	@Column(name="TX_EMAIL", length=255)
	private String email;
	
	@Column(name="TX_TELEFONE", length=255)
	private String telefone;
	
	@Column(name="VL_BONUS")
	private BigDecimal bonus;
	
	@Column(name="TX_CPF")
	private String cpf;

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
		Segurado other = (Segurado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
