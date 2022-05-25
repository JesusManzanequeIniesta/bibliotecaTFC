/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Usuarios;

/**
 *
 * @author yisus
 */
public class DaoUsuarios {
    Dao dao;

    public DaoUsuarios(Dao dao) {
        this.dao = dao;
    }
    
    public void create(Usuarios usuario) throws Exception{
        try {
            dao.getEm().persist(usuario);
            dao.getEm().flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
