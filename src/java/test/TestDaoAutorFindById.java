package test;

import java.sql.SQLException;

import dao.DaoAutor;
import entidades.Autor;

public class TestDaoAutorFindById {

	public static void main(String[] args) {
		DaoAutor daoAutor=new DaoAutor();
		try {
			Autor a=daoAutor.findById(1);
			if(a!=null)
				System.out.println(a.toString());
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
