/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Libro;
import entidades.Peticion;
import java.util.List;

/**
 *
 * @author yisus
 */
public class DaoPeticion {
    Dao dao;
    
    public DaoPeticion(Dao dao){
        this.dao = dao;
    }
    
    public void create(Peticion peticion) throws Exception{
        try {
            dao.getEm().persist(peticion);
            dao.getEm().flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public List listAll() throws Exception{
        return dao.getEm().createNamedQuery("Peticion.findAll", Peticion.class).getResultList();
    }
    public List listByLibro(Libro libro) throws Exception{
        return dao.getEm().createNamedQuery("Peticion.findByLibro", Peticion.class)
                          .setParameter("libro", libro)
                          .getResultList();
    }
}
