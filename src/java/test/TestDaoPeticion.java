package test;

import java.sql.SQLException;

import dao.DaoPeticion;
import entidades.Peticion;

public class TestDaoPeticion {
	public static void main(String[] args) {
		try {
		  DaoPeticion daoPeticion=new DaoPeticion();
		  Peticion p;
		  System.out.println(" Inserto peticion de socio 1 libro de 1 a 3 ");
		  for (int libro=1;libro<=3;libro++) {
		    p=new Peticion();
		    p.setSocio_id(1);
		    p.setLibro_id(libro);
		    daoPeticion.inserta(p);
		    System.out.println(p);
		  }
		  System.out.println(" Busco las tres peticiones ");
		  for (int peticion=1;peticion<=3;peticion++) {
			  p=daoPeticion.findById(peticion);
			  System.out.println(p);
		  }
		  System.out.println(" Listo todas las peticiones ");
		  for (Peticion l:daoPeticion.listado()){
			  System.out.println(l);
		  }
		 
		} 
		catch (SQLException e) {System.out.println(e.toString());} 
		catch (Exception e) {e.printStackTrace();}
	}

}
