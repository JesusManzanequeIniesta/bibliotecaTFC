package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoAutor;
import entidades.Autor;

public class TestDaoAutorListado {

	public TestDaoAutorListado() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		DaoAutor daoAutor=new DaoAutor();
		try {
			ArrayList<Autor> autores=daoAutor.listado();
			for(Autor a:autores) 
				System.out.println(a.toString());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
