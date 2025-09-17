package mx.desarrollo.integration;


import jakarta.persistence.EntityManager;
import mx.desarollo.entity.Profesor;
import mx.desarollo.entity.Unidadaprendizaje;
import mx.desarrollo.dao.*;
import mx.desarrollo.persistence.HibernateUtil;
import java.util.List; //Se agregó libreria para poder manejar la lista de maestros




/**
 *
 * @author total
 */
public class ServiceLocator {

    private static UnidadAprendizajeDAO unidadAprendizajeDAO;
    private static AdministradorDAO administradorDAO;
    private static AsignadoDAO asignadoDAO;
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

    public static ProfesorDAO getInstanceProfesorDAO(){
        if(profesorDAO == null){
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;
        } else{
            return profesorDAO;
        }
    }

    public static AsignadoDAO getInstanceAsignadoDAO(){
        if(asignadoDAO== null){
            asignadoDAO = new AsignadoDAO(getEntityManager());
            return asignadoDAO;
        } else{
            return asignadoDAO;
        }
    }

    public static AdministradorDAO getInstanceAdministradorDAO(){
        if(administradorDAO == null){
            administradorDAO = new AdministradorDAO(getEntityManager());
            return administradorDAO;
        } else{
            return administradorDAO;
        }
    }
}
