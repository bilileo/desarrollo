package mx.desarrollo.integration;

import jakarta.persistence.EntityManager;
import mx.desarollo.entity.Profesor;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.dao.*;
import mx.desarrollo.persistence.HibernateUtil;


/**
 *
 * @author total
 */
public class ServiceLocator {

    private static UnidadAprendizajeDAO unidadAprendizajeDAO;
    private static ProfesorDAO profesorDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    /**
     * se crea la instancia para UnidadAprendizajeDAO si esta no existe
     */
    public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO(){
        if(unidadAprendizajeDAO == null){
            unidadAprendizajeDAO = new UnidadAprendizajeDAO(getEntityManager());
            return unidadAprendizajeDAO;
        } else{
            return unidadAprendizajeDAO;
        }
    }

    public static void guardarUnidadAprendizaje(Unidadaprendizaje ua) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            getInstanceUnidadAprendizajeDAO().guardar(ua);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex; // relanzar o manejar el error
        }
    }

    // ----- PROFESOR -----

    // obtener instancia
    public static ProfesorDAO getInstanceProfesorDAO(){
        if(profesorDAO == null){
            profesorDAO = new ProfesorDAO(getEntityManager());
        }
        return profesorDAO;
    }
    // guardar el profesor
    public static void guardarProfesor(Profesor profe){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            getInstanceProfesorDAO().guardar(profe);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex; // relanzar o manejar el error
        }
    }
}