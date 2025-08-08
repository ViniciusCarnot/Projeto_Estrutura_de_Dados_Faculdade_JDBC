package model.entities.dao;

import java.util.List;
import java.util.Set;

import model.entities.Funcionario;

public interface FuncionarioDao {
	
	void inserir(Funcionario funcionario);
	void atualizar(Funcionario funcionario);
	void deletarPorId(Integer id);
	Funcionario recuperarPorId(Integer id);
	List<Funcionario> recuperarTodos();
	List<Funcionario> salarioMaiorQueDezMil();
	Set<Funcionario> responsaveisPorProjetosEmAndamento();
	Set<String> emailsDeFuncionariosResponsaveisPorProjetos();
	
	
}
