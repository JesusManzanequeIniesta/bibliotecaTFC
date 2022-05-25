/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Socio;
import java.time.LocalDate;
import java.util.List;
import tools.creaCodBarras;

/**
 *
 * @author yisus
 */
public class DaoSocio {
    Dao dao;

    public DaoSocio(Dao dao) {
        this.dao = dao;
    }
    public void create(Socio socio) throws Exception{
        creaCodBarras ccb;
        try {
            ccb = new creaCodBarras();
            socio.setFechaalta(LocalDate.now());
            dao.getEm().persist(socio);
            
            int codigo = socio.getId();
            String codbarrasPos = ccb.generaNif(codigo);
            socio.setCodbarras(codbarrasPos);
            dao.getEm().flush();
        } catch (Exception ex) {
            throw ex;
        }
    }
    public Socio findById(int id) throws Exception{
        List<Socio> listaSocio;
        try{
            listaSocio = dao.getEm().createNamedQuery("Socio.findById", Socio.class).getResultList();
            if(listaSocio.isEmpty()){
                return null;
            }
            else{
                return listaSocio.get(0);
            }
        } catch (Exception ex){
            throw ex;
        }
    }
    public Socio findByEmail(String email) throws Exception{
        List<Socio> listaSocio;
        try{
            listaSocio = dao.getEm().createNamedQuery("Socio.findByEmail", Socio.class)
                                    .setParameter("email", email)
                                    .getResultList();
            if(listaSocio.isEmpty()){
                return null;
            }
            else{
                return listaSocio.get(0);
            }
        } catch (Exception ex){
            throw ex;
        }
    }
    public Socio findByCodBarras(String codbarras) throws Exception{
        List<Socio> listaSocio = null;
        try{
            listaSocio = dao.getEm().createNamedQuery("Socio.findByCodbarras", Socio.class)
                                    .setParameter("codbarras", codbarras)
                                    .getResultList();
            if(listaSocio.isEmpty()){
                return null;
            }
            else{
                return listaSocio.get(0);
            }
        } catch (Exception ex){
            throw ex;
        }
    }
}
