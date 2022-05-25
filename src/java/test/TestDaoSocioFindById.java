package test;

import java.sql.SQLException;

import dao.Dao;
import dao.DaoSocio;
import entidades.Socio;

public class TestDaoSocioFindById {

	public static void main(String[] args) {
		try {
			Dao dao=new Dao();
			DaoSocio daoSocio=new DaoSocio(dao);
			Socio s=daoSocio.findById(1);
			dao.close();
			if(s!=null)
				System.out.println(s.toString());
			else
				System.out.println("*** No existe un autor con ese código ****");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
