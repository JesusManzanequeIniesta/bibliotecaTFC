package dao;

import daojpa.PersistenceManagerSingleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Dao {
    private EntityManager em=null;
    private boolean soloLectura=false;

    public Dao() {
        EntityManagerFactory emf = PersistenceManagerSingleton.getInstance().getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    // En caso de pasar como parámetro true, entendemos que no se debe iniciar la transacción
    public Dao(boolean soloLectura){
        this.soloLectura=soloLectura;
        EntityManagerFactory emf = PersistenceManagerSingleton.getInstance().getEntityManagerFactory();
        em = emf.createEntityManager();
        if (!soloLectura) em.getTransaction().begin();
    }
    
    // Al devolver em no evitamos el uso de close o transaction
    // lo que podría provocar errores
    public EntityManager getEm(){
        return em;
    }

    public void close() {
        if (!soloLectura) em.getTransaction().commit();
        em.close();
    }
    
    public void closeError() {
        if (!soloLectura) em.getTransaction().rollback();
        em.close();
    }
}