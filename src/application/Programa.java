package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import model.entities.Funcionario;
import model.entities.Projeto;
import model.entities.dao.FuncionarioDao;
import model.entities.dao.ProjetoDao;
import model.entities.impl.FuncionarioDaoImpl;
import model.entities.impl.ProjetoDaoImpl;

public class Programa {

	public static void main(String[] args) throws ParseException {
		
		/* INSERIR FUNCIONARIO
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		funcionarioDao.inserir(new Funcionario(null, "daniel santos", 18000.35, "danielsantos@email.com"));
		
		System.out.println("Funcionário inserido com sucesso. ");
		
		/* RECUPERAR FUNCIONARIO POR ID E ATUALIZAR FUNCIONARIO POR ID
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		Funcionario funcionario = funcionarioDao.recuperarPorId(7);
		
		funcionario.setNome("Akon");
		funcionario.setEmail("akon@email.com");
		
		funcionarioDao.atualizar(funcionario);
		
		System.out.println("Funcionário atualizado com sucesso. ");
		*/
		
		
		/* DELETAR FUNCIONARIO POR ID
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		funcionarioDao.deletarPorId(2);
		
		System.out.println("Funcionário deletado com sucesso. ");
		*/
		
		
		/* RECUPERAR TODOS OS FUNCINARIOS
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		List<Funcionario> lista = funcionarioDao.recuperarTodos();
		
		for(Funcionario f : lista) {
			System.out.println(f);
		}
		*/
		
		
		/* INSERIR PROJETO
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date d1 = sdf.parse("2025-06-06");
		Date d2 = sdf.parse("2025-08-08");
		
		Funcionario funcionario = new Funcionario(1, "Alicia Keys", 12000.00, "aliciakeys@email.com");
		
		Projeto projeto = new Projeto("Natural Sounds", d1, d2, 2, 120000.50, funcionario);
		
		ProjetoDao projetoDao = new ProjetoDaoImpl();
		
		projetoDao.inserir(projeto);
		
		System.out.println("Projeto inserido com sucesso. ");
		*/
		
		
		/* RECUPERAR PROJETO POR NOME E ATUALIZAR PROJETO POR NOME
		ProjetoDao projetoDao = new ProjetoDaoImpl();
		
		Projeto projeto = projetoDao.recuperarPorId("TSMC");
		
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		Funcionario funcionario = funcionarioDao.recuperarPorId(9);
		
		projeto.setResponsavel(funcionario);
		
		projetoDao.atualizar(projeto);
		
		System.out.println("Projeto atualizado com sucesso. ");
		*/
		
		
		/* DELETAR PROJETO POR NOME
		ProjetoDao projetoDao = new ProjetoDaoImpl();
		
		projetoDao.deletar("Tencent");
		
		System.out.println("Projeto deletado com sucesso. ");
		*/
		
		
		/* RECUPERAR TODOS OS PROJETOS
		ProjetoDao projetoDao = new ProjetoDaoImpl();
		
		List<Projeto> lista = projetoDao.recuperarTodos();
		
		for(Projeto p : lista) {
			System.out.println(p);
		}
		*/
		
		
		/* RECUPERAR FUNCIONARIOS COM SALARIOS MAIORES QUE 10 MIL
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		List<Funcionario> lista = funcionarioDao.salarioMaiorQueDezMil();
		
		for(Funcionario f : lista) {
			System.out.println(f);
		}
		*/
		
		
		/* RECUPERAR PROJETOS EM ANDAMENTO COM VALORES ACIMA DE 500 MIL
		ProjetoDao projetoDao = new ProjetoDaoImpl();
		
		List<Projeto> lista = projetoDao.emAndamentoComValoresEstimadosAcimaDe500Mil();
		
		for(Projeto p : lista) {
			System.out.println(p);
		}
		*/
		
		
		/* RECUPERAR FUNCIONARIOS RESPONSAVEIS POR PROJETOS EM ANDAMENTO
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		Set<Funcionario> set = funcionarioDao.responsaveisPorProjetosEmAndamento();
		
		for(Funcionario f : set) {
			System.out.println(f);
		}
		*/
		
		
		/* RECUPERAR EMAILS DE FUNCIONARIOS RESPONSAVEIS POR PROJETOS
		FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();
		
		Set<String> set = funcionarioDao.emailsDeFuncionariosResponsaveisPorProjetos();
		
		for(String email : set) {
			System.out.println(email);
		}
		*/
		
	}

}
