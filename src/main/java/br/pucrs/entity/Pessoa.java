package br.pucrs.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Pessoa {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private String nome;

	@JsonIgnore
	private String senha;
	
	private String email;


	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
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
		this.senha = PASSWORD_ENCODER.encode(senha);
	}
	
}
