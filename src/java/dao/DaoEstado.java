/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Estado;

/**
 *
 * @author yisus
 */
public class DaoEstado {
    Dao dao;

    public DaoEstado(Dao dao) {
        this.dao = dao;
    }
    
    public Estado findById(int id) throws Exception{
        return dao.getEm().find(Estado.class, id);
    }

    public Estado findByDescripcion(String desc) {
        return dao.getEm().createNamedQuery("Estado.findByDescripcion", Estado.class)
                          .setParameter("descripcion", desc)
                          .getSingleResult();
    }
}
