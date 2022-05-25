/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daojpa;

/**
 *
 * @author julio
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BaseJPADao {

    public BaseJPADao() {
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = PersistenceManagerSingleton.getInstance().getEntityManagerFactory();
        return emf.createEntityManager();
    }
}