package model.entities.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Funcionario;
import model.entities.Projeto;
import model.entities.dao.ProjetoDao;

public class ProjetoDaoImpl implements ProjetoDao {
	
	private Connection conn;
	
	public ProjetoDaoImpl() {
		this.conn = DB.getConnection();
	}

	@Override
	public void inserir(Projeto projeto) {
		
		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement("insert into projetos "
					+ "(nome, dataInicio, dataTermino, tempoEstimado, valorEstimado, idFuncionario) "
					+ "values "
					+ "(?, ?, ?, ?, ?, ?) ");
			
			ps.setString(1, projeto.getNome());
			ps.setDate(2, new java.sql.Date(projeto.getDataInicio().getTime()));
			ps.setDate(3, new java.sql.Date(projeto.getDataTermino().getTime()));
			ps.setInt(4, projeto.getTempoEstimado());
			ps.setDouble(5, projeto.getValorEstimado());
			ps.setInt(6, projeto.getResponsavel().getId());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				System.out.println("Linhas modificadas : " + rowsAffected);
			} else {
				throw new DbException("Erro inesperado! Nenhum projeto inserido. ");
			}
			
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void atualizar(Projeto projeto) {
	
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("update projetos "
					+ "set dataInicio = ?, "
					+ "dataTermino = ?, "
					+ "tempoEstimado = ?, "
					+ "valorEstimado = ?, "
					+ "idFuncionario = ? "
					+ "where nome = ?");
			
			if(projeto.getDataTermino() == null) {
				ps.setDate(2, null);
			} else {
				ps.setDate(2, new java.sql.Date(projeto.getDataTermino().getTime()));
			}
			
			ps.setDate(1, new java.sql.Date(projeto.getDataInicio().getTime()));
			ps.setInt(3, projeto.getTempoEstimado());
			ps.setDouble(4, projeto.getValorEstimado());
			ps.setInt(5, projeto.getResponsavel().getId());
			ps.setString(6, projeto.getNome());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deletar(String nome) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("delete from projetos as p "
					+ "where p.nome = ? "
					+ "limit 1");
			
			ps.setString(1, nome);
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				System.out.println("Linhas modificadas: " + rowsAffected);
			} else {
				throw new DbException("Erro inesperado! Nenhuma linha modificada. ");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Projeto recuperarPorNome(String nome) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from projetos as p "
					+ "inner join funcionarios as f "
					+ "on p.idFuncionario = f.id "
					+ "where p.nome = ?");
			
			ps.setString(1, nome);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				Projeto projeto = instantiateProjeto(rs, funcionario);
				return projeto;
			}
			System.out.println("Nenhum projeto encontrado. ");
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public List<Projeto> recuperarTodos() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select p.*, f.nome, f.salario, f.email from projetos as p "
					+ "inner join funcionarios as f "
					+ "on p.idFuncionario = f.id");
			
			rs = ps.executeQuery();
			
			List<Projeto> lista = new ArrayList<Projeto>();
			
			while(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				Projeto projeto = instantiateProjeto(rs, funcionario);
				lista.add(projeto);
			}
			
			return lista;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}
	
	private static Projeto instantiateProjeto(ResultSet rs, Funcionario funcionario) throws SQLException {
		Projeto projeto = new Projeto();
		projeto.setNome(rs.getString("p.nome"));
		projeto.setDataInicio(rs.getDate("dataInicio"));
		projeto.setDataTermino(rs.getDate("dataTermino"));
		projeto.setTempoEstimado(rs.getInt("tempoEstimado"));
		projeto.setValorEstimado(rs.getDouble("valorEstimado"));
		projeto.setResponsavel(funcionario);
		return projeto;
	}
	
	private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(rs.getInt("p.idFuncionario"));
		funcionario.setNome(rs.getString("f.nome"));
		funcionario.setSalario(rs.getDouble("f.salario"));
		funcionario.setEmail(rs.getString("f.email"));
		return funcionario;
	}

	@Override
	public List<Projeto> emAndamentoComValoresEstimadosAcimaDe500Mil() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select p.*, f.nome, f.salario, f.email from projetos as p "
					+ "inner join funcionarios as f "
					+ "on p.idFuncionario = f.id "
					+ "where p.dataTermino is null and p.valorEstimado > 500000 "
					+ "order by p.valorEstimado");
			
			rs = ps.executeQuery();
			
			List<Projeto> lista = new ArrayList<>();
			
			while(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				Projeto projeto = instantiateProjeto(rs, funcionario);
				lista.add(projeto);
			}
			
			return lista;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

}
