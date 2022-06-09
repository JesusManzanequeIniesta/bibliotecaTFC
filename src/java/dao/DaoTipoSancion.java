/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.Dao;
import entidades.Tiposancion;

/**
 *
 * @author yisus
 */
public class DaoTipoSancion {

    Dao dao;

    public DaoTipoSancion(Dao dao) {
        this.dao = dao;
    }

    public void create(Tiposancion ts) {
        try {
            dao.getEm().persist(ts);
            dao.getEm().flush();
        } catch (Exception ex) {
            throw ex;
        }
    }

}
