/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Autor;
import java.util.List;

/**
 *
 * @author yisus
 */
public class DaoAutor {
    Dao dao;

    public DaoAutor(Dao dao) {
        this.dao = dao;
    }
    
    public void create(Autor autor) throws Exception{
        try{
            dao.getEm().persist(autor);
            dao.getEm().flush();
        } catch (Exception ex){
            throw ex;
        }
    }
    
    public void edit(Autor autor) throws Exception{
        try{
            dao.getEm().persist(autor);
            dao.getEm().flush();
        } catch (Exception ex){
            throw ex;
        }
    }
    
    public List<Autor> listAllAuthors() throws Exception{
        return dao.getEm().createNamedQuery("Autor.findAll", Autor.class).getResultList();
    }
    
    public Autor findByNombre(String nombre) throws Exception{
        Autor autor;
        try{
            List<Autor> listAutor = dao.getEm().createNamedQuery("Autor.findByNombre", Autor.class)
                               .setParameter("nombre", nombre)
                               .getResultList();
            if(listAutor.isEmpty()){
                autor = null;
            } else{
                autor = listAutor.get(0);
            }
            
        } catch (Exception ex){
            throw ex;
        }
        return autor;
    }
    public Autor findById(int id) throws Exception{
        Autor autor;
        try{
            autor = dao.getEm().find(Autor.class, id);
        } catch (Exception ex){
            throw ex;
        }
        return autor;
    }
    public List findByNombreLike(String nombre) throws Exception{
        List<Autor> listAutor = null;
        try{
            System.out.println(dao.getEm());
            listAutor = dao.getEm().createNamedQuery("Autor.findByNombreLike", Autor.class)
                               .setParameter("nombre", nombre+"%")
                               .getResultList();
        } catch (Exception ex){
            throw ex;
        }
        return listAutor;
    }
    
}
