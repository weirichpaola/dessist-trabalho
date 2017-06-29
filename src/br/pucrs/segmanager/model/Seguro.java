package br.pucrs.segmanager.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe que representa um Seguro
 * @author Nelson
 *
 */

@Entity
@Table(name="SEG_SEGURO")
public class Seguro {

	@Id
	@Column(name="ID_SEGURO")
	@GeneratedValue(generator="SEG_SQ_SEGURO")
    @SequenceGenerator(name="SEG_SQ_SEGURO",sequenceName="SEG_SQ_SEGURO", allocationSize=1, initialValue=1)
	private Long id;
	
	@JoinColumn(name = "ID_SEGURADO")
	private Segurado segurado;
	
	//TODO: Para a próxima versão, será criados objetos para as Seguradoras
	@JoinColumn(name="ID_SEGURADORA")
	private Seguradora seguradora;
	
	//TODO: Para a próxima versão, será criado objetos para os Bens
	@Column(name="TX_BEM")
	private String bem;
	
	@Column(name="VL_TOTAL")
	private BigDecimal vlTotal;
	
	@Column(name="TX_APOLICE")
	private String txApolice;
	
	@Column(name="DT_INICIO_VIGENCIA")
	@Temporal(TemporalType.DATE)
	private Date dtInicioVigencia;
	
	@Column(name="DT_FIM_VIGENCIA")
	@Temporal(TemporalType.DATE)
	private Date dtFimVigencia;
	
	@Column(name="TX_ESTADO")
	private String txEstado;
	
	@Column(name="DT_EMISSAO")
	@Temporal(TemporalType.DATE)
	private Date dtEmissao;
	
	@Column(name="VL_COMISSAO")
	private BigDecimal vlComissao;
	
	@Column(name="VL_FRANQUIA")
	private BigDecimal vlFranquia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Segurado getSegurado() {
		return segurado;
	}

	public void setSegurado(Segurado segurado) {
		this.segurado = segurado;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public String getBem() {
		return bem;
	}

	public void setBem(String bem) {
		this.bem = bem;
	}

	public BigDecimal getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}

	public String getTxApolice() {
		return txApolice;
	}

	public void setTxApolice(String txApolice) {
		this.txApolice = txApolice;
	}

	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public String getTxEstado() {
		return txEstado;
	}

	public void setTxEstado(String txEstado) {
		this.txEstado = txEstado;
	}

	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public BigDecimal getVlComissao() {
		return vlComissao;
	}

	public void setVlComissao(BigDecimal vlComissao) {
		this.vlComissao = vlComissao;
	}

	public BigDecimal getVlFranquia() {
		return vlFranquia;
	}

	public void setVlFranquia(BigDecimal vlFranquia) {
		this.vlFranquia = vlFranquia;
	}

}
