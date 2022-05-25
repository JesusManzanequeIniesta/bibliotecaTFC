package test;

import java.sql.SQLException;

import dao.DaoAutor;
import entidades.Autor;
import excepciones.BibliotecaException;

public class TestDaoAutorModifica {

	public static void main(String[] args) {
		DaoAutor daoAutor=new DaoAutor();
		Autor a=new Autor();
		a.setId(7);
		a.setNombre("Julio ");
		try {
			daoAutor.modifica(a);
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (BibliotecaException e) {
			// TODO Auto-generated catch block
			System.out.println(" Esta es mi excepción");
			e.printStackTrace();
		}

	}

}
