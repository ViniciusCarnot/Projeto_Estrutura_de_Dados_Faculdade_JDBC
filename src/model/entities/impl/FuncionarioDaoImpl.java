package model.entities.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import db.DB;
import db.DbException;
import model.entities.Funcionario;
import model.entities.dao.FuncionarioDao;

public class FuncionarioDaoImpl implements FuncionarioDao {
	
	private Connection conn;
	
	public FuncionarioDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	public FuncionarioDaoImpl() {
		this.conn = DB.getConnection();
	}

	@Override
	public void inserir(Funcionario funcionario) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("insert into funcionarios "
					+ "(nome, salario, email) "
					+ "values "
					+ "(?, ?, ?)" ,
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, funcionario.getNome());
			ps.setDouble(2, funcionario.getSalario());
			ps.setString(3, funcionario.getEmail());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					
					int id = rs.getInt(1);
					funcionario.setId(id);
				}
				
				DB.closeResultSet(rs);
				
			}
			
		} catch (SQLException e) {
			throw new DbException("Erro inesperado! Nenhuma linha afetada. ");
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void atualizar(Funcionario funcionario) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("update funcionarios "
					+ "set nome = ?, "
					+ "salario = ?, "
					+ "email = ? "
					+ "where id = ?");
			
			ps.setString(1, funcionario.getNome());
			ps.setDouble(2, funcionario.getSalario());
			ps.setString(3, funcionario.getEmail());
			ps.setInt(4, funcionario.getId());
			
			ps.executeUpdate();
		
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deletarPorId(Integer id) {
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("delete from funcionarios "
					+ "where id = ? "
					+ "limit 1");
			
			ps.setInt(1, id);
			
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
	public Funcionario recuperarPorId(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from funcionarios where id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				return funcionario;
			} else {
				System.out.println("Esse funcionário não existe");
				return null;
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Funcionario> recuperarTodos() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from funcionarios");
			
			rs = ps.executeQuery();
			
			List<Funcionario> lista = new ArrayList<Funcionario>();
			
			while(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				lista.add(funcionario);
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
	
	private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(rs.getInt("id"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setSalario(rs.getDouble("salario"));
		funcionario.setEmail(rs.getString("email"));
		return funcionario;
	}

	@Override
	public List<Funcionario> salarioMaiorQueDezMil() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select * from funcionarios as f "
					+ "where f.salario > 10000 "
					+ "order by salario ");
			
			rs = ps.executeQuery();
			
			List<Funcionario> lista = new ArrayList<>();
			
			while(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				lista.add(funcionario);
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

	@Override
	public Set<Funcionario> responsaveisPorProjetosEmAndamento() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select f.* from projetos as p "
					+ "inner join funcionarios as f "
					+ "on p.idFuncionario = f.id "
					+ "where p.dataTermino is null "
					+ "order by f.nome");
			
			rs = ps.executeQuery();
			
			Set<Funcionario> set = new HashSet<>();
			
			while(rs.next()) {
				Funcionario funcionario = instantiateFuncionario(rs);
				set.add(funcionario);
			}
			
			return set;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public Set<String> emailsDeFuncionariosResponsaveisPorProjetos() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("select f.email from projetos as p "
					+ "inner join funcionarios as f "
					+ "on p.idFuncionario = f.id "
					+ "order by f.email");
			
			rs = ps.executeQuery();
			
			Set<String> set = new LinkedHashSet<>();
			
			while(rs.next()) {
				set.add(rs.getString("email"));
			}
			
			return set;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

}
