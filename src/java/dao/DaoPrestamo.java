/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Prestamo;

/**
 *
 * @author yisus
 */
public class DaoPrestamo {
    Dao dao;

    public DaoPrestamo(Dao dao) {
        this.dao = dao;
    }
    
    public void create(Prestamo prestamo) throws Exception{
        prestamo.setCalculadoActivo(true);
        try {
           dao.getEm().persist(prestamo);
           dao.getEm().flush(); 
        } catch (Exception ex) {
            throw ex;
        }
        
        
    }
}
