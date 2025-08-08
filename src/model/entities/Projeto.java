package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Date dataInicio;
	private Date dataTermino;
	private Integer tempoEstimado;
	private Double valorEstimado;
	
	private Funcionario responsavel;
	
	public Projeto() {
	}

	public Projeto(String nome, Date dataInicio, Date dataTermino, Integer tempoEstimado, Double valorEstimado,
			Funcionario responsavel) {
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.tempoEstimado = tempoEstimado;
		this.valorEstimado = valorEstimado;
		this.responsavel = responsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(Integer tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public Double getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(Double valorEstimado) {
		this.valorEstimado = valorEstimado;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Projeto [nome=" + nome + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino
				+ ", tempoEstimado=" + tempoEstimado + ", valorEstimado=" + valorEstimado + ", responsavel="
				+ responsavel + "]";
	}
	
	
	
	

}
