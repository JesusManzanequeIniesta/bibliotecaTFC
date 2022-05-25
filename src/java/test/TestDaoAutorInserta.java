package test;

import java.sql.SQLException;

import dao.DaoAutor;
import entidades.Autor;

public class TestDaoAutorInserta {

	public static void main(String[] args) {
		DaoAutor daoAutor=new DaoAutor();
		// Lo primero sería buscar Autores con nombres parecidos
		// para no dar de alta dos veces el mismo autor
		// FALLO EN DISEÑO EN BD No guardamos información para garantizar que
		// no se repitan
		Autor a=new Autor();
		a.setNombre("Julio ");
		try {
			daoAutor.inserta(a);
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
