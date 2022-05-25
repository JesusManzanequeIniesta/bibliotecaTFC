/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Grupos;

/**
 *
 * @author yisus
 */
public class DaoGrupos {
    Dao dao;

    public DaoGrupos(Dao dao) {
        this.dao = dao;
    }
    
    public void create(Grupos grupo) throws Exception{
        try {
            dao.getEm().persist(grupo);
            dao.getEm().flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
