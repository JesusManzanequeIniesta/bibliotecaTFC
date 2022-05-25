package test;

import java.sql.SQLException;

import dao.DaoAutor;
// Para ver el uso de cpu y gasto de memoria mientras se ejecutan
// instalar en linux htop y ejecutarlo
// Para ver uso de disco instalar dstat

public class TestDaoAutorRendimiento {

	public static void main(String[] args) {
		DaoAutor daoAutor=new DaoAutor();
		try {
			int pasos=10000;
			System.out.println(" Empiezo con PS");
			daoAutor.rendimientoPS(pasos);
			System.out.println(" Sigo con S");
			daoAutor.rendimientoS(pasos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
