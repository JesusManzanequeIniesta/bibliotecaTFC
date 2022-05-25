package test;

import java.sql.SQLException;

import dao.DaoSocio;
import entidades.Socio;

public class TestDaoSocio {
	public static void main(String[] args) {
		try {
		  DaoSocio daoSocio=new DaoSocio();
		  Socio p;
		  System.out.println(" Inserto socio de 1 a 3 ");
		  for (int id=1;id<=3;id++) {
		    p=new Socio();
		    p.setNombre("N"+id);
		    p.setTelefono("T"+id);
		    p.setEmail("e@"+id);
		    daoSocio.inserta(p);
		    System.out.println(p);
		  }
		  System.out.println(" Listo todos los socios ");
		  for (Socio l:daoSocio.listado()){
			  System.out.println(l);
		  }
		 
		} 
		catch (SQLException e) {System.out.println(e.toString());} 
		catch (Exception e) {e.printStackTrace();}
	}

}
