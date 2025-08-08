package model.entities.dao;

import java.util.List;

import model.entities.Projeto;

public interface ProjetoDao {
	
	void inserir(Projeto projeto);
	void atualizar(Projeto projeto);
	void deletar(String nome);
	Projeto recuperarPorNome(String nome);
	List<Projeto> recuperarTodos();
	List<Projeto> emAndamentoComValoresEstimadosAcimaDe500Mil();
	
}
