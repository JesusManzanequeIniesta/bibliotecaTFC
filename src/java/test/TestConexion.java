package test;

import java.sql.Connection;
import java.sql.SQLException;

import conexiones.Conexion;

public class TestConexion {

	// VERSION sin dao.java
	/*public static void main(String[] args)  {
		Conexion conexion=new Conexion();
		Connection con=null;
		try {
			 con=conexion.getConexion();
			 if (con!=null) {
				 System.out.println("He conectado");
				 con.close();
			 }
		} catch (SQLException e) {
			System.out.println("Tratamiento del error");
			System.out.println(e.toString());
		}
	}*/
	
	// VERSION con dao.java
	public static void main(String[] args)  {
		try {
			Conexion conexion=new Conexion();
			conexion.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" Conexion correcta ");
	}

}
