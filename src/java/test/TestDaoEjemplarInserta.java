package test;

import java.sql.SQLException;

import dao.DaoEjemplar;
import entidades.Ejemplar;

public class TestDaoEjemplarInserta {

	public static void main(String[] args) {
		DaoEjemplar daoEjemplar=new DaoEjemplar();
		Ejemplar a=new Ejemplar();
		a.setLibro_id(1);
		try {
			daoEjemplar.inserta(a);
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
