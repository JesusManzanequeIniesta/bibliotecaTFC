/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Ejemplar;
import java.util.List;
import tools.creaCodBarras;

/**
 *
 * @author yisus
 */
public class DaoEjemplar {

    Dao dao;

    public DaoEjemplar(Dao dao) {
        this.dao = dao;
    }

    public void create(Ejemplar ejemplar) throws Exception {
        creaCodBarras ccb;
        try {
            ccb = new creaCodBarras();
            dao.getEm().persist(ejemplar);

            int codigo = ejemplar.getId();
            String codbarrasPos = ccb.generaNif(codigo);
            ejemplar.setCodbarras(codbarrasPos);

            dao.getEm().flush();

        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public Ejemplar findByCodBarras(String codBarras) throws Exception{
        List<Ejemplar> listaEjemplar;
        
        try{
            listaEjemplar = dao.getEm().createNamedQuery("Ejemplar.findByCodbarras", Ejemplar.class)
                                       .setParameter("codbarras", codBarras)
                                       .getResultList();
            if(listaEjemplar.isEmpty()){
                return null;
            }
            else{
                return listaEjemplar.get(0);
            }
        } catch (Exception ex){
            throw ex;
        }
    }
}
