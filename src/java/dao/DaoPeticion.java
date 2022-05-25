/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Peticion;

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
}
