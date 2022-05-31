/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.Libro;
import java.util.List;

/**
 *
 * @author yisus
 */
public class DaoLibro {
    Dao dao;

    public DaoLibro(Dao dao) {
        this.dao = dao;
    }
    
    public void create(Libro libro) throws Exception{
        try{
            dao.getEm().persist(libro);
            dao.getEm().flush();
            
        } catch (Exception ex){
            throw ex;
        }
    }

    public Libro findByISBN(String isbn) throws Exception {
        Libro libro;
        try{
            List<Libro> listLibro = dao.getEm().createNamedQuery("Libro.findByIsbn", Libro.class)
                               .setParameter("isbn", isbn)
                               .getResultList();
            if(listLibro.isEmpty()){
                libro = null;
            }
            else{
                libro = listLibro.get(0);
            }
        } catch (Exception ex){
            throw ex;
        }
        return libro;
    }

    public Libro findByTitulo(String titulo) {
        List<Libro> listaLibro;
        try{
            listaLibro = dao.getEm().createNamedQuery("Libro.findByTitulo", Libro.class)
                                    .setParameter("titulo", titulo)
                                    .getResultList();
            if(listaLibro.isEmpty()){
                return null;
            } else{
                return listaLibro.get(0);
            }
        } catch (Exception ex){
            throw ex;
        }
    }
    public List findByTituloLike(String titulo) {
        List<Libro> listaLibro;
        try{
            listaLibro = dao.getEm().createNamedQuery("Libro.findByTituloLike", Libro.class)
                                    .setParameter("titulo", "%"+titulo+"%")
                                    .getResultList();
        } catch (Exception ex){
            throw ex;
        }
        return listaLibro;
    }
}
