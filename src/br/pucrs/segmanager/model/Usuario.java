package br.pucrs.segmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="SEG_USUARIO")
public class Usuario {

	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(generator="SQ_USUARIO")
    @SequenceGenerator(name="SQ_USUARIO",sequenceName="SQ_USUARIO", allocationSize=3, initialValue=3)
	private Long id;
	
	@Column(name="TX_EMAIL", length=255)
	private String email;
	
	@Column(name="TX_SENHA", length=100)
	private String senha;
	
	@Column(name="TX_PERFIL")
	private String perfil;
	
	@JoinColumn(name="ID_SEGURADO")
	private Segurado segurado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public Segurado getSegurado() {
		return segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}
	
	
}
