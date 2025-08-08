package model.entities.dao;

import db.DB;
import model.entities.impl.FuncionarioDaoImpl;

public class DaoFactory {
	
	public static FuncionarioDao criaFuncionarioDao() {
		return new FuncionarioDaoImpl(DB.getConnection());
	}
	
	
	
}
